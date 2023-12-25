import java.awt.Image;

public interface Plantes {

    boolean enVie();

    void agir(boolean zombieLane);

    void subir(int degat);

    int getX();

    int getY();

    int getCouts();

    Image getImage();

    int getVie();
}
