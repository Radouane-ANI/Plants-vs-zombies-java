import java.util.List;

public class PLanteMine extends Plantes {
    private long recharge;

    public PLanteMine(int x, int y) {
        super(300, 1800, 'b', x, y, 50, new String[] { "/Images/PotatoMine.png", "/Images/PotatoMine1.png" });
        recharge = System.currentTimeMillis();
    }

    @Override
    public void agir(boolean zombieLane, List<Zombies> zombiesList) {
        boolean zombiePresent = false;
        if (zombieLane && System.currentTimeMillis() - recharge > 15000) {
            for (Zombies zombies : zombiesList) {
                if ((int) (zombies.getX()) == x && (int) (zombies.getY()) == y) {
                    zombies.subir(degat);
                    zombiePresent = true;
                }
            }
        }
        if (zombiePresent) {
            vie = 0;
        }
    }

    @Override
    public void arrose() {
        degat *= 1.5;
    }

}
