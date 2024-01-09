import java.util.List;

public class Chomper extends Plantes {
    private long recharge;

    public Chomper(int x, int y) {
        super(300, 800, 'c', x, y, 150, new String[] { "/Images/Chomper1.png", "/Images/Chomper0.png" });
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

}
