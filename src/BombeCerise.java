import java.util.List;
import java.util.ArrayList;

public class BombeCerise extends Plantes {

    public BombeCerise(int x, int y) {
        super(100, 1800, 'b', x, y, 150,
                new String[] { "/Images/cherrybomb1.png", "/Images/cherrybomb2.png", "/Images/boom.png" });
        recharge = System.currentTimeMillis();
    }

    @Override
    public void agir(boolean zombieLane, List<Zombies> zombiesList) {
        if (System.currentTimeMillis() - recharge > 1500) {
            for (Zombies zombies : getZombiesAdjacents(zombiesList)) {
                zombies.subir(degat);
            }
            vie = 0;
        }
    }

    private List<Zombies> getZombiesAdjacents(List<Zombies> zombiesList) {
        List<Zombies> zombiesAdjacents = new ArrayList<>();
        for (Zombies zombies : zombiesList) {
            int x = (int) zombies.getX();
            int y = (int) zombies.getY();

            if (Math.abs(x - super.x) <= 1 && Math.abs(y - super.y) <= 1) {
                zombiesAdjacents.add(zombies);
            }
        }
        return zombiesAdjacents;
    }

    @Override
    public void subir(int degat) {
    }

    @Override
    public void arrose() {
        degat *= 1.5;
    }

}
