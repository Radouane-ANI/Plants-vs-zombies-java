import java.awt.Graphics;
import java.awt.Image;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameScene extends JPanel {

    private Image jardin;
    private Image planche;
    private Plateau plateau;
    private int[] tailleHerbeX = { 240, 338, 411, 496, 572, 662, 738, 816, 893, 980 };
    private int[] tailleHerbeY = { 60, 176, 275, 383, 476, 573 };

    private Game game;

    public GameScene(Game game) {
        this.jardin = new ImageIcon(getClass().getResource("/Images/jardin" + game.getNiveau().getLargeur() + ".jpg"))
                .getImage();
        this.planche = new ImageIcon(getClass().getResource("/Images/planche.png")).getImage();
        this.plateau = game.getPlateau();
        this.game = game;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                game.placerPlante(mettreEchelleTableauX(e.getY()),
                        mettreEchelleTableauY(e.getX()), 'a');
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(jardin, 0, 0, null);
        g.drawImage(planche, 20, 0, null);
        for (Zombies z : plateau.getZombieslList()) {
            g.drawImage(z.getImage(),
                    mettreEchelleJardinX(z.getY()) - 90, mettreEchelleJardinY(z.getX()) - 80, null);
        }

        for (Balle b : Plateau.getBalles()) {
            g.drawImage(b.getImage(),
                    mettreEchelleJardinX(b.getY()), 53 + (int) b.getX() * 108, null);
        }

        for (Plantes p : plateau.getPlanteslList()) {
            g.drawImage(p.getImage(),
                    mettreEchelleJardinX(p.getY() + 0.2), 50 + (int) p.getX() * 108, null);
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
}
