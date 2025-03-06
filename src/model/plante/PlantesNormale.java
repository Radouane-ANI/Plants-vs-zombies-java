package model.plante;

import java.util.List;

import model.Plateau;
import model.balle.Balle;
import model.zombie.Zombies;

public class PlantesNormale extends Plantes {
    private long recharge;

    protected PlantesNormale(int vie, int degat, char nom, int x, int y, int couts, String[] path) {
        super(vie, degat, nom, x, y, couts, path);
    }

    public PlantesNormale(int x, int y) {
        super(130, 20, 'N', x, y, 100, new String[] { "/Images/peashooter1.png", "/Images/peashooter2.png" });
    }

    @Override
    public void agir(boolean zombieLane, List<Zombies> zombiesList) {
        if (zombieLane) {
            if (System.currentTimeMillis() - recharge > 1500) {
                Plateau.addBalle(new Balle(degat, x, y + 0.75));
                recharge = System.currentTimeMillis();
            }
        }
    }

    @Override
    public void arrose() {
        degat *= 1.5;
    }

}
