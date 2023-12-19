import java.util.ArrayList;
import java.util.Iterator;

public class Plateau {

    private Herbe[][] jardin;
    private static ArrayList<Balle> balles = new ArrayList<>();
    private boolean[] zombieLane;
    private boolean[] tondeuse;
    private boolean perdu;
    private int nbZombies, largeur;

    public Plateau(int hauteur, int largeur) {
        jardin = new Herbe[hauteur][largeur];
        for (int i = 0; i < jardin.length; i++) {
            for (int j = 0; j < jardin[0].length; j++) {
                jardin[i][j] = new Herbe();
            }
        }
        zombieLane = new boolean[largeur];
        balles.clear();
        tondeuse = new boolean[largeur];
        for (int i = 0; i < tondeuse.length; i++) {
            tondeuse[i] = true;
        }
        this.largeur = largeur;
    }

    public int getLargeur() {
        return largeur;
    }

    public int getNbZombies() {
        return nbZombies;
    }

    public boolean isPerdu() {
        return perdu;
    }

    public static void addBalle(Balle balle) {
        balles.add(balle);
    }

    public void etatZombieLane() {
        nbZombies = 0;
        for (int i = 0; i < zombieLane.length; i++) {
            boolean contient = false;
            for (int j = 0; j < jardin.length; j++) {
                if (jardin[j][i].contientZombie()) {
                    nbZombies++;
                    contient = true;
                    jardin[j][i].avancentZombies();
                    jardin[j][i].zombiesMorts();
                    jardin[j][i].attaqueZombie();
                    if (j > 0) {
                        ArrayList<Zombies> changeHerbes = jardin[j][i].deplacementZombies(j);
                        jardin[j - 1][i].addAllZombies(changeHerbes);
                    }
                    if (j == 0) {
                        ArrayList<Zombies> changeHerbes = jardin[j][i].deplacementZombies(j);
                        if (changeHerbes.size() > 0) {
                            declencheTondeuse(i);
                        }
                    }
                }
            }
            zombieLane[i] = contient;
        }
    }

    public void etatBalles() {
        Iterator<Balle> iterator = balles.iterator();
        while (iterator.hasNext()) {
            Balle balle = iterator.next();
            balle.avance();
            if (balle.getY() > jardin.length) {
                iterator.remove();
            } else if (jardin[(int) balle.getY()][(int) balle.getX()].contientZombie()) {
                balle.toucher(jardin[(int) balle.getY()][(int) balle.getX()].getFirstZombies());
                iterator.remove();
            }

        }
    }

    public void etatPlante() {
        for (int i = 0; i < jardin.length; i++) {
            for (int j = 0; j < jardin[0].length; j++) {
                if (jardin[i][j].contientPlante() && zombieLane[j] == true) {
                    jardin[i][j].attaquePlante();
                    jardin[i][j].planteMorte();
                }
            }
        }
    }

    public boolean addPlante(Plantes plantes) {
        return jardin[plantes.getY()][plantes.getX()].addplante(plantes);
    }

    public void addZombie(Zombies zombies) {
        jardin[(int) zombies.getY()][(int) zombies.getX()].addZombie(zombies);
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
