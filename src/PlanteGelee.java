//import java.util.ArrayList;


public class PlanteGelee extends PlantesAttaquantes {

    // private ArrayList<Zombies> zombiesList;
    private long recharge;
    // private int degat;
    private int x, y;
    
    public PlanteGelee(int vie, int degat, char nom, int x, int y, int couts, String[] path){
        super(vie, nom, x, y, couts, path);
       // zombiesList = new ArrayList<>();
    }


    

    @Override
    public void agir(boolean zombieLane) {
        if (zombieLane) {
            if (System.currentTimeMillis() - recharge > 1500) {
                Plateau.addBalleGelee(new BalleGelee(x, y + 0.75));
                recharge = System.currentTimeMillis();
            }
        }
    }

    // public void agir(boolean zombieLane) {
    //     if (this.degat>0 && zombieLane) {
    //         if (System.currentTimeMillis() - recharge > 1500) {
    //             Plateau.addBalle(new Balle(degat, x, y + 0.75));
    //             recharge = System.currentTimeMillis();
    //         }
    //     }
    // }

    // @Override
    // public void agir(boolean zombieLane) {
    //     super.agir(zombieLane);
    //     if (zombieLane) {
    //         Zombies[] zombiesDansLane = getZombiesDansLane();
    //         for (Zombies zombie : zombiesDansLane){
    //             zombie.ralentirDeplacement();
               
    //         }
    //     }
    // }

    // public Zombies[] getZombiesDansLane() {
    //     ArrayList<Zombies> zombiesDansLane = new ArrayList<>();
    //     double yPlante = getY(); // Coordonnée Y de la plante gelée
        
    //     for (Zombies zombie : zombiesList) {
    //         double yZombie = zombie.getY(); // Coordonnée Y du zombie
    //         if (yZombie == yPlante) {
    //             zombiesDansLane.add(zombie);
    //         }
    //     }
        
    //     return zombiesDansLane.toArray(new Zombies[zombiesDansLane.size()]); // convertit la liste zombiesDansLane en un tableau d'objets Zombies
    // }

    // public void setZombiesList(ArrayList<Zombies> zombieslList) {
    //     this.zombiesList = zombieslList;
    // }
}
