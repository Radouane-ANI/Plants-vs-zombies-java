import java.awt.Image;

import javax.swing.ImageIcon;

public class Tondeuse {

    private double x, y;
    private long timer;
    private Image image = new ImageIcon(getClass().getResource("/Images/tondeuse.png")).getImage();

    public Image getImage() {
        return image;
    }

    public Tondeuse(double x, double y) {
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

    public void avance() {
        timer = System.nanoTime() - timer;
        y = y + 1 * (1.5 * 1E-9 * timer);
        timer = System.nanoTime();
    }
}