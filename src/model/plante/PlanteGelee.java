package model.plante;
import java.util.List;

import model.Plateau;
import model.balle.BalleGelee;
import model.zombie.Zombies;

public class PlanteGelee extends PlantesNormale {
    private long recharge;

    public PlanteGelee(int x, int y) {
        super(130, 10, 'G', x, y, 175, new String[] { "/Images/peag1.png", "/Images/peag2.png" });
    }

    @Override
    public void agir(boolean zombieLane, List<Zombies> zombiesList) {
        if (zombieLane) {
            if (System.currentTimeMillis() - recharge > 2250) {
                Plateau.addBalle(new BalleGelee(degat, x, y + 0.75));
                recharge = System.currentTimeMillis();
            }
        }
    }
}
