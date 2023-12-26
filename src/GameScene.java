import java.awt.Graphics;
import java.awt.Image;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameScene extends JPanel {

    private Image jardin;
    private Plateau plateau;
    private int[] tailleHerbeX = { 240, 338, 411, 496, 572, 662, 738, 816, 893, 980 };
    private int[] tailleHerbeY = { 60, 176, 275, 383, 476, 573 };

    private Game game;

    public GameScene(Game game) {
        jardin = new ImageIcon(getClass().getResource("/Images/jardin1.jpg")).getImage();
        this.plateau = game.getPlateau();
        this.game = game;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String[] img = { "/Images/peashooter1.png", "/Images/peashooter2.png" };
                plateau.addPlante(new PlantesAttaquantes(150, 20, 'a', mettreEchelleTableauX(e.getY()),
                        mettreEchelleTableauY(e.getX()), 100, img));
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(jardin, 0, 0, null);
        for (Zombies z : plateau.getZombieslList()) {
            g.drawImage(z.getImage(),
                    mettreEchelleJardinX(z.getY()) - 40, (int) z.getX() * 108, null);
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
