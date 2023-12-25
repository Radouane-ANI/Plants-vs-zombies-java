import java.awt.Image;

import javax.swing.ImageIcon;

public class PlantesAttaquantes implements Plantes {
    private int vie, degat;
    private char nom;
    private long recharge, changeImage;
    private int x, y;
    private int couts, indiceImage;
    private Image[] image;

    public PlantesAttaquantes(int vie, int degat, char nom, int x, int y, int couts, String[] path) {
        this.vie = vie;
        this.degat = degat;
        this.nom = nom;
        this.recharge = System.currentTimeMillis();
        this.x = x;
        this.y = y;
        this.couts = couts;
        this.image = new Image[path.length];
        for (int i = 0; i < path.length; i++) {
            image[i] = new ImageIcon(getClass().getResource(path[i])).getImage();
        }
        this.changeImage = System.currentTimeMillis();

    }

    public PlantesAttaquantes(int vie, char nom, int x, int y, int couts, String[] path) {
        this(vie, 0, nom, x, y, couts, path);
    }

    @Override
    public void agir(boolean zombieLane) {
        if (this.degat>0 && zombieLane) {
            if (System.currentTimeMillis() - recharge > 1500) {
                Plateau.addBalle(new Balle(degat, x, y + 0.75));
                recharge = System.currentTimeMillis();
            }
        }
    }

    @Override
    public boolean enVie() {
        return vie > 0;
    }

    @Override
    public int getCouts() {
        return this.couts;
    }

    @Override
    public void subir(int degat) {
        this.vie -= degat;
    }

    public String toString() {
        return nom + "";
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public int getVie() {
        return vie;
    }

    

    @Override
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

}
