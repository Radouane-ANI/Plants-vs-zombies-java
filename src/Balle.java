import java.awt.Image;

import javax.swing.ImageIcon;

public class Balle {

    private int degat;
    private double x, y;
    private long timer;
    private Image image = new ImageIcon(getClass().getResource("/Images/balle.png")).getImage();

    public Image getImage() {
        return image;
    }

    public Balle(int degat, double x, double y) {
        this.degat = degat;
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

    public boolean toucher(Zombies zombies) {
        if (zombies.getY() - y < 0.1) {
            zombies.subir(degat);
            return true;
        }
        return false;
    }

    public void avance() {
        timer = System.nanoTime() - timer;
        y = y + 1 * (2 * 1E-9 * timer);
        timer = System.nanoTime();
    }
}