package model;

import gui.App;

public class Soleil {

    private static int nbSoleil;
    private long timer;
    private static int afficheSoleil;

    public Soleil() {
        nbSoleil = 25;
        timer = System.currentTimeMillis();
    }

    public static int getNbSoleil() {
        return nbSoleil;
    }

    public void setNbSoleil(int nb) {
        nbSoleil = nb;
    }

    public void generesSoleil() {
        if (System.currentTimeMillis() - timer > 5000) {
            addSoleil();
            timer = System.currentTimeMillis();
        }
    }

    public void soustraitSoleil(int nb) {
        nbSoleil -= nb;
    }

    public static void ajouteSoleil() {
        if (App.isVisible()) {
            addAfficheSoleil();
        } else {
            addSoleil();
        }
    }

    public static void addSoleil() {
        nbSoleil += 25;
    }

    public static int getAfficheSoleil() {
        if (afficheSoleil > 0) {
            return afficheSoleil--;
        }
        return afficheSoleil;
    }

    public static void addAfficheSoleil() {
        afficheSoleil++;
    }

    public String toString() {
        return nbSoleil + "";
    }
}
