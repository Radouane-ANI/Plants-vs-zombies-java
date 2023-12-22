import java.awt.Image;

import javax.swing.ImageIcon;

public class Zombies {

    private int vie, degat;
    private Image[] image;
    private long recharge, timer, changeImage;
    private double x, y;
    private double vitesse;
    private int indiceImage;

    public Zombies(int vie, int degat, String[] path, double x, double y, double vitesse) {
        this.vie = vie;
        this.degat = degat;
        this.x = x;
        this.y = y;
        this.recharge = System.currentTimeMillis();
        this.vitesse = vitesse;
        this.timer = System.nanoTime();
        this.image = new Image[path.length];
        for (int i = 0; i < path.length; i++) {
            image[i] = new ImageIcon(getClass().getResource(path[i])).getImage();
        }
        this.changeImage = System.currentTimeMillis();
    }

    public static Zombies generesZombieNormale(int x) {
        String[] images = { "/Images/zombie1.png", "/Images/zombie2.png" };
        return new Zombies(190, 30, images, x, 8.99, 1.25);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getVitesse() {
        return vitesse;
    }

    public void attaquer(Plantes plantes) {
        if (System.currentTimeMillis() - recharge > 1000) {
            plantes.subir(degat);
            recharge = System.currentTimeMillis();
        }
    }

    public boolean enVie() {
        return vie > 0;
    }

    public void subir(int degat) {
        this.vie -= degat;
    }

    public void avancePas(){
        timer = System.nanoTime();
    }

    public void avance() {
        timer = System.nanoTime() - timer;
        y = y - 1 * (vitesse * 1E-10 * timer);
        timer = System.nanoTime();
    }

    public Image getImage() {
        if (indiceImage == image.length) {
            indiceImage = 0;
        }
        if (System.currentTimeMillis() - changeImage > 500) {
            changeImage = System.currentTimeMillis();
            return image[indiceImage++];
        } else {
            return image[indiceImage];
        }
    }

    public void kill() {
        this.vie = 0;
    }
}
