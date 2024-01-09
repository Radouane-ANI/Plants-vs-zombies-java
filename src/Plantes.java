import java.awt.Image;
import java.util.List;
import javax.swing.ImageIcon;

public abstract class Plantes {
    protected int vie, degat;
    private char nom;
    protected long changeImage;
    protected int x, y;
    private int couts, indiceImage;
    private Image[] image;

    public Plantes(int vie, int degat, char nom, int x, int y, int couts, String[] path) {
        this.vie = vie;
        this.degat = degat;
        this.nom = nom;
        this.x = x;
        this.y = y;
        this.couts = couts;
        this.image = new Image[path.length];
        for (int i = 0; i < path.length; i++) {
            image[i] = new ImageIcon(getClass().getResource(path[i])).getImage();
        }
        this.changeImage = System.currentTimeMillis();
    }

    public abstract void agir(boolean zombieLane, List<Zombies> zombiesList);

    public abstract void arrose();

    public boolean enVie() {
        return vie > 0;
    }

    public int getCouts() {
        return this.couts;
    }

    public void subir(int degat) {
        this.vie -= degat;
    }

    public String toString() {
        return nom + "";
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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
}
