import java.util.List;

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
            System.out.println("pour poser une plante vous devez d'abord donner sa coordonnee en x puis y");
            System.out.println("x compris entre 0 et " + (niveau.getLargeur() - 1) + " et y compris entre 0 et 8");
            System.out.println("si vous ne voulez rien poser entrez -1");
            System.out.println();
            this.gestionPlantes.placerPlante();
            if (getArrosoir() > 0) {
                this.gestionPlantes.placerArrosoir();
            }
            tempsTextuel = System.currentTimeMillis();
        }
    }

    public boolean isLoose() {
        return plateau.isPerdu();
    }

    public boolean isWin() {
        boolean win = niveau.tousApparus() && plateau.getNbZombies() == 0;
        if (win) {
            niveau.debloqueNiveau();
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
