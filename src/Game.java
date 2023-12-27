import java.awt.Toolkit;

public class Game implements Runnable {

    private Plateau plateau;
    private GestionnaireNiveaux niveau;
    private Long temps;
    private Soleil soleil;
    private GestionPlantes gestionPlantes;

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

    public Game(GestionnaireNiveaux niveau) {
        this.niveau = niveau;
        this.soleil = new Soleil();
        this.plateau = new Plateau(9, niveau.getLargeur());
        this.gestionPlantes = new GestionPlantes(plateau, soleil, niveau);
    }

    @Override
    public void run() {
        double intervalle = System.nanoTime() + 1000000000 / 60;
        temps = System.currentTimeMillis();
        System.out.println("pour poser une plante vous devez d'abord donner sa coordonnee en x puis y");
        System.out.println("x compris entre 0 et " + (niveau.getLargeur() - 1) + " et y compris entre 0 et 9");
        System.out.println("si vous ne voulez rien poser taper entrer -1 pour x");
        while (jeuxEncours()) {
            this.ajouteZombie();
            soleil.generesSoleil();
            plateau.miseAJour();
            System.out.println(plateau);
            System.out.println("vous avez " + soleil + " soleil");
            // this.gestionPlantes.placerPlante();
            App.repaint();
            Toolkit.getDefaultToolkit().sync();
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

    public void placerPlante(int x, int y, char c) {
        gestionPlantes.placerPlante(x, y, c);
    }
}
