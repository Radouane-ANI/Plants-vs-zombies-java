import java.util.ArrayList;
import java.util.Iterator;

public class Plateau {

    private Herbe[][] jardin;
    private static ArrayList<Balle> balles = new ArrayList<>();
    private int[] zombieLane;
    private boolean[] tondeuse;
    private boolean perdu;
    private int largeur;
    private ArrayList<Zombies> zombiesList;
    private ArrayList<Plantes> plantesList;

    public Plateau(int hauteur, int largeur) {
        jardin = new Herbe[hauteur][largeur];
        for (int i = 0; i < jardin.length; i++) {
            for (int j = 0; j < jardin[0].length; j++) {
                jardin[i][j] = new Herbe(i);
            }
        }
        balles.clear();
        zombieLane = new int[largeur];
        tondeuse = new boolean[largeur];
        for (int i = 0; i < tondeuse.length; i++) {
            tondeuse[i] = true;
            zombieLane[i] = -1;
        }
        this.largeur = largeur;
        this.zombiesList = new ArrayList<>();
        this.plantesList = new ArrayList<>();
    }

    public int getLargeur() {
        return largeur;
    }

    public int getNbZombies() {
        return zombiesList.size();
    }

    public boolean isPerdu() {
        return perdu;
    }

    public static void addBalle(Balle balle) {
        balles.add(balle);
    }

    public static ArrayList<Balle> getBalles() {
        return balles;
    }

    public void etatZombieLane() {
        Iterator<Zombies> iterator = zombiesList.iterator();
        while (iterator.hasNext()) {
            Zombies zombie = iterator.next();

            int y = (int) zombie.getY();
            int x = (int) zombie.getX();
            jardin[y][x].avancentZombies();
            jardin[y][x].zombiesMorts();
            jardin[y][x].attaqueZombie();

            if (zombieLane[x] < y) {
                zombieLane[x] = y;
            }

            if (y == 0) {
                ArrayList<Zombies> changeHerbes = jardin[y][x].deplacementZombies();
                if (changeHerbes.size() > 0) {
                    declencheTondeuse(x);
                    zombieLane[x] = -1;
                }
            } else {
                ArrayList<Zombies> changeHerbes = jardin[y][x].deplacementZombies();
                jardin[y - 1][x].addAllZombies(changeHerbes);
                if (changeHerbes.size() > 0) {
                    zombieLane[x] = y - 1;
                }
            }
            if (!zombie.enVie() || zombie.getY() < 0) {
                if (zombieLane[x] == (int) zombie.getY()) {
                    zombieLane[x] = -1;
                }
                iterator.remove();
            }
        }

    }

    public ArrayList<Zombies> getZombieslList() {
        return zombiesList;
    }

    public ArrayList<Plantes> getPlanteslList() {
        return plantesList;
    }

    public void etatBalles() {
        Iterator<Balle> iterator = balles.iterator();
        while (iterator.hasNext()) {
            Balle balle = iterator.next();
            balle.avance();
            if (balle.getY() > jardin.length) {
                iterator.remove();
            } else if (jardin[(int) balle.getY()][(int) balle.getX()].contientZombie()) {
                if (balle.toucher(jardin[(int) balle.getY()][(int) balle.getX()].getFirstZombies())) {
                    iterator.remove();
                }
            }

        }
    }

    public void etatPlante() {
        Iterator<Plantes> iterator = plantesList.iterator();
        while (iterator.hasNext()) {
            Plantes plante = iterator.next();

            int y = plante.getY();
            int x = plante.getX();
            jardin[y][x].attaquePlante(zombieLane[x]);
            if (jardin[y][x].planteMorte()) {
                iterator.remove();
            }
        }
    }

    public boolean addPlante(Plantes plantes) {
        if (jardin[plantes.getY()][plantes.getX()].addplante(plantes)) {
            plantesList.add(plantes);
            return true;
        }
        return false;
    }

    public void addZombie(Zombies zombies) {
        jardin[(int) zombies.getY()][(int) zombies.getX()].addZombie(zombies);
        zombiesList.add(zombies);
    }

    public String toString() {
        String plateau = "";
        for (int i = 0; i < jardin.length; i++) {
            for (int j = 0; j < jardin[0].length; j++) {
                plateau += "|" + jardin[i][j];
            }
            plateau += "|\n";
        }
        return plateau;
    }

    public void declencheTondeuse(int i) {
        if (tondeuse[i]) {
            for (int j = 0; j < jardin.length; j++) {
                jardin[j][i].killAllZombies();
            }
            tondeuse[i] = false;
        } else {
            perdu = true;
        }
    }

    public void miseAJour() {
        this.etatZombieLane();
        this.etatPlante();
        this.etatBalles();
    }

}
