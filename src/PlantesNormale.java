import java.util.List;

public class PlantesNormale extends Plantes {

    public PlantesNormale(int vie, int degat, char nom, int x, int y, int couts, String[] path) {
        super(vie, degat, nom, x, y, couts, path);
    }

    public static PlantesNormale generesPlantesAttaquante(int x, int y) {
        String[] img = { "/Images/peashooter1.png", "/Images/peashooter2.png" };
        return new PlantesNormale(130, 20, 'a', x, y, 100, img);
    }

    public static PlantesNormale generesPlantesMuraille(int x, int y) {
        String[] img = { "/Images/noix1.png", "/Images/noix2.png" };
        return new PlantesNormale(350, 0, 'm', x, y, 50, img);
    }

    @Override
    public void agir(boolean zombieLane, List<Zombies> zombiesAdjacents){
        if (zombieLane && degat > 0) {
            if (System.currentTimeMillis() - recharge > 1500) {
                Plateau.addBalle(new Balle(degat, x, y + 0.75));
                recharge = System.currentTimeMillis();
            }
        }
    }

}
