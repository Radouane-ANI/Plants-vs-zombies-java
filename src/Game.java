public class Game implements Runnable {

    private Plateau plateau;
    private GestionnaireNiveaux niveau;
    private Long temps;
    private Soleil soleil;
    private GestionPlantes gestionPlantes;

    public Game(GestionnaireNiveaux niveau) {
        this.niveau = niveau;
        this.soleil = new Soleil();
        this.plateau = new Plateau(9, niveau.getLargeur());
        this.gestionPlantes = new GestionPlantes(plateau, soleil);
    }

    @Override
    public void run() {
        temps = System.currentTimeMillis();
        System.out.println("pour poser une plante vous devez d'abord donner sa coordonnee en x puis y");
        System.out.println("x compris entre 0 et " + (niveau.getLargeur() - 1) + " et y compris entre 0 et 9");
        System.out.println("si vous ne voulez rien poser taper entrer -1 pour x");
        while (jeuxEncours()) {
            this.ajouteZombie();
            this.gestionPlantes.placerPlante();
            soleil.generesSoleil();
            plateau.miseAJour();
            System.out.println(plateau);
            System.out.println("vous avez " + soleil + " soleil");
            try {
                Thread.sleep(10);
            } catch (Exception e) {
            }
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

    public boolean jeuxEncours(){
        return !isLoose() && !isWin();
    }
}
