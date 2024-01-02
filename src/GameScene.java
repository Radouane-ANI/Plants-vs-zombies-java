import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GameScene extends JPanel {

    private Image jardin;
    private Image planche;
    private Plateau plateau;
    private int[] tailleHerbeX = { 240, 338, 411, 496, 572, 662, 738, 816, 893, 980 };
    private int[] tailleHerbeY = { 60, 176, 275, 383, 476, 573 };
    private JLabel nbsoleil;
    private Game game;
    private ArrayList<Carte> listCarte;

    public GameScene(Game game) {
        this.setLayout(null);
        this.plateau = game.getPlateau();
        this.jardin = new ImageIcon(getClass().getResource("/Images/jardin" + plateau.getLargeur() + ".jpg"))
                .getImage();
        this.planche = new ImageIcon(getClass().getResource("/Images/planche.jpg")).getImage();
        this.game = game;
        listCarte = new ArrayList<>();
        int decalage = 100;
        for (GestionnaireNiveaux.Paire c : game.plantesDisponibles()) {
            Carte carte = new Carte(decalage, 8, c.getType());
            add(carte);
            Listener l = new Listener(carte);
            addMouseMotionListener(l);
            addMouseListener(l);
            decalage += 55;
            listCarte.add(carte);
        }
        nbsoleil = new JLabel("25");
        nbsoleil.setBounds(48, 36, 50, 50);
        add(nbsoleil);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(jardin, 0, 0, null);
        g.drawImage(planche, 20, 0, null);
        for (Zombies z : plateau.getZombieslList()) {
            g.drawImage(z.getImage(),
                    mettreEchelleJardinX(z.getY()) - 90, mettreEchelleJardinY(z.getX() + 0.35) - 85, null);
        }

        for (Balle b : Plateau.getBalles()) {
            g.drawImage(b.getImage(),
                    mettreEchelleJardinX(b.getY()), mettreEchelleJardinY(b.getX() + 0.35), null);
        }

        for (Plantes p : plateau.getPlanteslList()) {
            g.drawImage(p.getImage(),
                    mettreEchelleJardinX(p.getY() + 0.2), mettreEchelleJardinY(p.getX() + 0.25), null);
        }

        for (Tondeuse t : plateau.getTondeusesList()) {
            g.drawImage(t.getImage(),
                    mettreEchelleJardinX(t.getY()) - 85, mettreEchelleJardinY(t.getX()), null);
        }
        Image tondeuse = new ImageIcon(getClass().getResource("/Images/tondeuse.png")).getImage();
        for (int i = 0; i < plateau.getTondeuse().length; i++) {
            if (plateau.getTondeuse()[i]) {
                g.drawImage(tondeuse, 140, mettreEchelleJardinY(i), null);
            }
        }

        for (Carte carte : listCarte) {
            carte.charge();
        }
        nbsoleil.setText(Soleil.getNbSoleil() + "");
        if (game.getArrosoir() > 0) {
            Arrosoir arrosoir = new Arrosoir(this);
            add(arrosoir);
            Listener l = new Listener(arrosoir);
            addMouseMotionListener(l);
            addMouseListener(l);
        }
    }

    private int mettreEchelleJardinX(double y) {
        return (int) (tailleHerbeX[(int) y]
                + (y - (int) y) * (tailleHerbeX[(int) y + 1] - tailleHerbeX[(int) y]));
    }

    private int mettreEchelleJardinY(double x) {
        return (int) (tailleHerbeY[(int) x]
                + (x - (int) x) * (tailleHerbeY[(int) x + 1] - tailleHerbeY[(int) x]));
    }

    private int mettreEchelleTableauY(int xJardin) {
        for (int i = 0; i < tailleHerbeX.length - 1; i++) {
            if (xJardin >= tailleHerbeX[i] && xJardin < tailleHerbeX[i + 1]) {
                return i;
            }
        }
        return tailleHerbeX.length;
    }

    private int mettreEchelleTableauX(int yJardin) {
        for (int i = 0; i < tailleHerbeY.length - 1; i++) {
            if (yJardin >= tailleHerbeY[i] && yJardin < tailleHerbeY[i + 1]) {
                return i;
            }
        }
        return tailleHerbeY.length;
    }

    public boolean arrose(int x, int y) {
        return game.arrose(mettreEchelleTableauX(y), mettreEchelleTableauY(x));
    }

    public class Carte extends JLabel {
        private int x, y;
        private boolean mouvement, charge;
        private int type;

        public Carte(int x, int y, String path) {
            setIcon(new ImageIcon(getClass().getResource(path)));
            setBounds(x, y, 50, 60);
            this.x = x;
            this.y = y;

        }

        public Carte(int x, int y, int type) {
            setIcon(new ImageIcon(getClass().getResource("/Images/carte" + type + ".jpeg")));
            setBounds(x, y, 50, 60);
            this.x = x;
            this.y = y;
            this.type = type;
        }

        public void setCharge(boolean charge) {
            this.charge = charge;
        }

        public boolean isCharge() {
            return charge;
        }

        public boolean isMouvement() {
            return mouvement;
        }

        public void setMouvement(boolean mouvement) {
            this.mouvement = mouvement;
        }

        public void retourPos() {
            setBounds(x, y, 50, 60);
        }

        public void posePlante(int x, int y) {
            game.placerPlante(mettreEchelleTableauX(y), mettreEchelleTableauY(x), type);
        }

        public void charge() {
            if (!mouvement) {
                charge = true;
                double ratio = game.pourcentageDispo(type);
                if (ratio > 1 || ratio < 0) {
                    ratio = 1;
                    charge = false;
                }
                setBounds(x, y, 50, (int) (60 * ratio));
            }
        }

    }

    public class Listener extends MouseAdapter {
        private Carte carte;

        public Listener(Carte carte) {
            this.carte = carte;
        }

        public void mousePressed(MouseEvent e) {
            Point point = carte.getLocation();
            int x = point.x;
            int y = point.y;
            if (e.getX() > x && e.getX() - x < 50 && e.getY() > y && e.getY() - y < 60 && !carte.isCharge()) {
                carte.setMouvement(true);
            }
        }

        public void mouseReleased(MouseEvent e) {
            if (carte.isMouvement()) {
                carte.retourPos();
                carte.posePlante(e.getX(), e.getY());
                carte.setMouvement(false);
            }
        }

        public void mouseDragged(MouseEvent e) {
            if (carte.isMouvement() && !carte.isCharge()) {
                int x = e.getX() - 25;
                int y = e.getY() - 30;
                carte.setBounds(x, y, 50, 60);
            }
        }
    }
}

// addMouseListener(new MouseAdapter() {
// @Override
// public void mouseClicked(MouseEvent e) {
// System.out.println(e.getX());
// System.out.println(e.getY());
// }
// });
