package model.plante;

import java.util.List;

import model.Soleil;
import model.zombie.Zombies;

public class Tournesol extends Plantes {
    private long tempsCharge;
    private int recharge = 15000;

    public Tournesol(int x, int y) {
        super(200, 0, 'T', x, y, 50, new String[] { "/Images/sunflower1.png", "/Images/sunflower2.png" });
        tempsCharge = System.currentTimeMillis();
    }

    @Override
    public void agir(boolean zombieLane, List<Zombies> zombiesList) {
        if (System.currentTimeMillis() - tempsCharge > recharge) {
            Soleil.ajouteSoleil();
            tempsCharge = System.currentTimeMillis();
        }
    }

    @Override
    public void arrose() {
        if (recharge < 5000) {
            recharge -= 150;
        } else {
            recharge *= 0.75;
        }
    }

}
