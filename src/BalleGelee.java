import java.awt.Image;
import javax.swing.ImageIcon;

public class BalleGelee {
    private double x, y;
    private long timer;
    private Image image = new ImageIcon(getClass().getResource("/Images/balle.png")).getImage();

    public Image getImage() {
        return image;
    }

    public BalleGelee(double x, double y) {
        this.x = x;
        this.y = y;
        this.timer = System.nanoTime();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    // public void geler(Zombies zombie) {
    //     // Logique de gel du zombie pendant 5 secondes
    //     zombie.setDeplacementRalenti(true);
    //     zombie.setTempsRalenti(System.currentTimeMillis() + 5000);
    // }

    public boolean gelee(Zombies zombie) {
        // Logique de gel du zombie pendant 5 secondes
        if (zombie.getY() - y < 0.1) {
        zombie.ralentir();
        zombie.setTempsRalenti(System.currentTimeMillis() + 5000);
        }
        return false;
    }

    public void avance() {
        timer = System.nanoTime() - timer;
        y = y + 1 * (2 * 1E-9 * timer);
        timer = System.nanoTime();
    }
}