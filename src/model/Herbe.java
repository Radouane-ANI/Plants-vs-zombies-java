package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.plante.Plantes;
import model.zombie.Zombies;

public class Herbe {

    private Plantes plante;
    private ArrayList<Zombies> zombiesList = new ArrayList<Zombies>();
    private int y;

    public Herbe() {

    }

    public Herbe(int y) {
        this.y = y;
    }

    public Plantes getplante() {
        return plante;
    }

    public boolean addplante(Plantes plante) {
        if (this.plante == null) {
            this.plante = plante;
            return true;
        }
        return false;
    }

    public boolean planteMorte() {
        if (!plante.enVie()) {
            plante = null;
            return true;
        }
        return false;
    }

    public void addZombie(Zombies zombies) {
        zombiesList.add(zombies);
    }

    public void zombiesMorts() {
        Iterator<Zombies> iterator = zombiesList.iterator();
        while (iterator.hasNext()) {
            Zombies zombies = iterator.next();
            if (!zombies.enVie()) {
                iterator.remove();
            }
        }
    }

    public List<Zombies> getZombiesList() {
        return zombiesList;
    }

    public Zombies getFirstZombies() {
        if (zombiesList.size() > 0) {
            return zombiesList.get(0);
        }
        return null;
    }

    public boolean contientPlante() {
        return plante != null;
    }

    public boolean contientZombie() {
        return zombiesList.size() > 0;
    }

    public void avancentZombies() {
        if (!contientPlante()) {
            for (Zombies zombies : zombiesList) {
                zombies.avance();
            }
        }
    }

    public ArrayList<Zombies> deplacementZombies(int y) {
        ArrayList<Zombies> sortieHerbes = new ArrayList<>();
        Iterator<Zombies> iterator = zombiesList.iterator();
        while (iterator.hasNext()) {
            Zombies zombies = iterator.next();
            if ((int) zombies.getY() != y || zombies.getY() < 0) {
                sortieHerbes.add(zombies);
                iterator.remove();
            }
        }
        return sortieHerbes;
    }

    public ArrayList<Zombies> deplacementZombies() {
        ArrayList<Zombies> sortieHerbes = new ArrayList<>();
        Iterator<Zombies> iterator = zombiesList.iterator();
        while (iterator.hasNext()) {
            Zombies zombies = iterator.next();
            if ((int) zombies.getY() != y || zombies.getY() < 0) {
                sortieHerbes.add(zombies);
                iterator.remove();
            }
        }
        return sortieHerbes;
    }

    public void attaqueZombie() {
        if (contientPlante()) {
            for (Zombies zombies : zombiesList) {
                zombies.attaquer(plante);
                zombies.avancePas();
            }
        }
    }

    public void attaquePlante(int posZombie, List<Zombies> zombiesList) {
        if (y <= posZombie) {
            plante.agir(true, zombiesList);
        } else {
            plante.agir(false, zombiesList);
        }
    }

    public String toString() {
        String herbe = "";
        if (contientPlante() && contientZombie()) {
            herbe += plante + "" + zombiesList.size();
        } else if (contientPlante()) {
            herbe += plante + " ";
        } else if (contientZombie()) {
            if (zombiesList.size() > 1) {
                herbe += zombiesList.get(0) + "" + zombiesList.size();
            }
            if (zombiesList.size() == 1) {
                herbe += zombiesList.get(0) + " ";
            }
        } else {
            herbe += "  ";
        }
        return herbe;
    }

    public void addAllZombies(ArrayList<Zombies> listZombies) {
        zombiesList.addAll(listZombies);
    }

    public void killAllZombies() {
        for (Zombies zombies : zombiesList) {
            zombies.kill();
        }
    }

    public boolean arrose() {
        if (contientPlante()) {
            plante.arrose();
            return true;
        }
        return false;
    }
}
