import java.awt.Image;
import javax.swing.ImageIcon;
import java.util.List;

public class Chomper extends Plantes {
    private long recharge;
    private int indiceImage;
    private Image[] imageRecharge;

    public Chomper(int x, int y) {
        super(300, 800, 'c', x, y, 150, new String[] { "/Images/Chomper1.png", "/Images/Chomper0.png" });
        String[] path = { "/Images/ChomperDigest.png", "/Images/ChomperDigest1.png" };
        imageRecharge = new Image[path.length];
        for (int i = 0; i < path.length; i++) {
            imageRecharge[i] = new ImageIcon(getClass().getResource(path[i])).getImage();
        }
    }

    @Override
    public void agir(boolean zombieLane, List<Zombies> zombiesList) {
        if (System.currentTimeMillis() - recharge > 30000) {
            Zombies face = getZombiesEnFace(zombiesList);
            if (face != null) {
                face.subir(degat);
                recharge = System.currentTimeMillis();
            }
        }
    }

    private Zombies getZombiesEnFace(List<Zombies> zombiesList) {
        for (Zombies zombies : zombiesList) {
            int x = (int) zombies.getX();
            int y = (int) zombies.getY();

            if (x == super.x && (y == super.y + 1 || y == super.y)) {
                return zombies;
            }
        }
        return null;
    }

    @Override
    public void arrose() {
        degat *= 1.5;
    }

    @Override
    public Image getImage() {
        if (System.currentTimeMillis() - recharge > 30000) {
            return super.getImage();
        } else {
            if (System.currentTimeMillis() - recharge < 500) {
                Image i =new ImageIcon(getClass().getResource("/Images/ChomperAttack.png")).getImage();
                return i;
            }
            if (indiceImage == imageRecharge.length) {
                indiceImage = 0;
            }
            if (System.currentTimeMillis() - changeImage > 500) {
                changeImage = System.currentTimeMillis();
                return imageRecharge[indiceImage++];
            } else {
                return imageRecharge[indiceImage];
            }

        }

    }

}
