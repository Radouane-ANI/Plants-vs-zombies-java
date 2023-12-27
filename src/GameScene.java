import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameScene extends JPanel {

    private Image jardin;
    private Image planche;
    private Plateau plateau;
    private int[] tailleHerbeX = { 240, 338, 411, 496, 572, 662, 738, 816, 893, 980 };
    private int[] tailleHerbeY = { 60, 176, 275, 383, 476, 573 };
    private JLabel nbsoleil;
    private Game game;

    public GameScene(Game game) {
        this.setLayout(null);
        this.plateau = game.getPlateau();
        this.jardin = new ImageIcon(getClass().getResource("/Images/jardin" + plateau.getLargeur() + ".jpg"))
                .getImage();
        this.planche = new ImageIcon(getClass().getResource("/Images/planche.jpg")).getImage();
        this.game = game;
        int decalage = 100;
        for (Character c : game.plantesDisponibles()) {
            Carte carte = new Carte(decalage, 8, c);
            add(carte);
            Listener l = new Listener(carte);
            addMouseMotionListener(l);
            addMouseListener(l);
            decalage += 55;
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
                    mettreEchelleJardinX(z.getY()) - 90, mettreEchelleJardinY(z.getX() + 0.3) - 85, null);
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
        nbsoleil.setText(Soleil.getNbSoleil() + "");
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

    public class Carte extends JLabel {
        private int x, y;
        private boolean mouvement;
        private char type;

        public Carte(int x, int y, char type) {
            setIcon(new ImageIcon(getClass().getResource("/Images/peashooter1.png")));
            setBounds(x, y, 50, 60);
            this.x = x;
            this.y = y;
            this.type = type;
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

        public void pose(int x, int y) {
            game.placerPlante(mettreEchelleTableauX(y), mettreEchelleTableauY(x), type);
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
            if (e.getX() > x && e.getX() - x < 50 && e.getY() > y && e.getY() - y < 60) {
                carte.setMouvement(true);
            }
        }

        public void mouseReleased(MouseEvent e) {
            carte.retourPos();
            carte.pose(e.getX(), e.getY());
            carte.setMouvement(false);
        }

        public void mouseDragged(MouseEvent e) {
            if (carte.isMouvement()) {
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
