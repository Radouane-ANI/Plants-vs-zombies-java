import java.awt.Image;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

public class Zombies {
    protected int vie;
    private int degat;
    protected Image[] image;
    private long recharge, distance, changeImage;
    private double x, y;
    protected double vitesse;
    private int indiceImage;
    private boolean ralentie;
    private static Timer timer = new Timer();
    private char nom;

    public Zombies(int vie, int degat, String[] path, double x, double y, double vitesse, char nom) {
        this.vie = vie;
        this.degat = degat;
        this.x = x;
        this.y = y;
        this.recharge = System.currentTimeMillis();
        this.vitesse = vitesse;
        this.distance = System.nanoTime();
        this.image = new Image[path.length];
        for (int i = 0; i < path.length; i++) {
            image[i] = new ImageIcon(getClass().getResource(path[i])).getImage();
        }
        this.changeImage = System.currentTimeMillis();
        this.nom = nom;
    }

    public static Zombies generesZombie(int x, int type) {
        Zombies z = null;
        switch (type) {
            case 1:
                String[] images = { "/Images/zombie1.png", "/Images/zombie2.png" };
                z = new Zombies(181, 30, images, x, 8.99, 1.25, 'n');
                break;
            case -1:
                String[] images2 = { "/Images/flagZombie1.png", "/Images/flagZombie2.png" };
                z = new Zombies(181, 25, images2, x, 8.99, 1.5, 'd');
                break;
            case 2:
                String[] images3 = { "/Images/conezombie1.png", "/Images/conezombie2.png" };
                z = new Zombies(551, 35, images3, x, 8.99, 1.45, 'c');
                break;
            case 3:
                String[] images4 = { "/Images/seauzombie1.png", "/Images/seauzombie2.png" };
                z = new Zombies(1281, 40, images4, x, 8.99, 1.1, 's');
                break;
            case 4:
                z = new Zombiejournal(x);
                break;
        }
        return z;
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

    public void avancePas() {
        distance = System.nanoTime();
    }

    public void avance() {
        distance = System.nanoTime() - distance;
        y = y - 1 * (vitesse * 1E-10 * distance);
        distance = System.nanoTime();
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

    public void ralentir() {
        if (!ralentie) {
            vitesse = vitesse / 1.5;
            ralentie = true;
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    vitesse = vitesse * 1.5;
                    ralentie = false;
                }
            }, 3000);
        }
    }

    @Override
    public String toString() {
        return nom + "";
    }
}
