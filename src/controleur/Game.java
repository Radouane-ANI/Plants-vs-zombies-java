package controleur;
import java.util.List;

import gui.App;
import model.GestionnaireNiveaux;
import model.Plateau;
import model.Soleil;
import model.zombie.Zombies;

public class Game implements Runnable {

    private Plateau plateau;
    private GestionnaireNiveaux niveau;
    private long temps, tempsTextuel;
    private Soleil soleil;
    private GestionPlantes gestionPlantes;
    private boolean graphique;

    public Plateau getPlateau() {
        return plateau;
    }

    public GestionnaireNiveaux getNiveau() {
        return niveau;
    }

    public Soleil getSoleil() {
        return soleil;
    }

    public GestionPlantes getGestionPlantes() {
        return gestionPlantes;
    }

    public Game(GestionnaireNiveaux niveau, boolean graphique) {
        this.niveau = niveau;
        this.soleil = new Soleil();
        this.plateau = new Plateau(9, niveau.getLargeur());
        this.gestionPlantes = new GestionPlantes(plateau, soleil, niveau);
        this.graphique = graphique;
    }

    @Override
    public void run() {
        double intervalle = System.nanoTime() + 1000000000 / 60;
        temps = System.currentTimeMillis();
        tempsTextuel = System.currentTimeMillis();
        while (jeuxEncours()) {
            this.ajouteZombie();
            soleil.generesSoleil();
            plateau.miseAJour();
            if (graphique) {
                App.repaint();
            } else {
                affichageTerminal();
            }
            try {
                double tempsAffichage = intervalle - System.nanoTime();
                tempsAffichage = tempsAffichage / 1000000;
                if (tempsAffichage < 0) {
                    tempsAffichage = 0;
                }
                Thread.sleep((long) tempsAffichage);
                intervalle += 1000000000 / 60;
            } catch (Exception e) {
            }
        }
        niveau.resetNiveau();
        App.showMenu();
    }

    public void affichageTerminal() {
        System.out.println("vous avez " + soleil + " soleil");
        System.out.println(plateau);
        if (System.currentTimeMillis() - tempsTextuel > 7000) {
            this.gestionPlantes.placerPlante();
            if (getArrosoir() > 0) {
                this.gestionPlantes.placerArrosoir();
            }
            tempsTextuel = System.currentTimeMillis();
        }
    }

    public boolean isLoose() {
        if (plateau.isPerdu()) {
            System.out.println("Perdu");
            return true;
        }return false;
    }

    public boolean isWin() {
        boolean win = niveau.tousApparus() && plateau.getNbZombies() == 0;
        if (win) {
            niveau.debloqueNiveau();
            System.out.println("Bravo! Vous avez gagné");
            return win;
        }
        return false;
    }

    public void ajouteZombie() {
        Zombies z = niveau.nextZombie(System.currentTimeMillis() - temps);
        if (z != null) {
            plateau.addZombie(z);
        }
    }

    public boolean jeuxEncours() {
        return !isLoose() && !isWin();
    }

    public void placerPlante(int x, int y, int c) {
        gestionPlantes.placerPlante(x, y, c);
    }

    public List<GestionnaireNiveaux.Paire> plantesDisponibles() {
        return gestionPlantes.plantesDisponibles();
    }

    public double pourcentageDispo(int type) {
        return gestionPlantes.pourcentageDispo(type);
    }

    public boolean arrose(int x, int y) {
        return plateau.arrose(x, y);
    }

    public int getArrosoir() {
        return plateau.getArrosoir();
    }
}
