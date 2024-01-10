package model.plante;
import java.util.List;

import model.zombie.Zombies;

public class PlanteMuraille extends Plantes {

    public PlanteMuraille(int x, int y) {
        super(350, 0, 'P', x, y, 50, new String[] { "/Images/noix1.png", "/Images/noix2.png" });
    }

    @Override
    public void agir(boolean zombieLane, List<Zombies> zombiesList) {
    }

    @Override
    public void arrose() {
        vie += 175;
    }

}
