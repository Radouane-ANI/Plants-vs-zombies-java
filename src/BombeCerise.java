import java.util.List;

public class BombeCerise extends Plantes {

    public BombeCerise(int x, int y) {
        super(100, 1800, 'b', x, y, 150,
                new String[] { "/Images/cherrybomb1.png", "/Images/cherrybomb2.png", "/Images/boom.png" });
        recharge = System.currentTimeMillis();
    }

    @Override
    public void agir(boolean zombieLane, List<Zombies> zombiesAdjacents) {
        if (System.currentTimeMillis() - recharge > 1500) {
            for (Zombies zombies : zombiesAdjacents) {
                zombies.subir(degat);
            }
            vie = 0;

        }
    }

    @Override
    public void subir(int degat) {
    }

}
