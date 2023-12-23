public class MainMenu {

    private GestionnaireNiveaux niveau;

    public MainMenu() {
        niveau = new GestionnaireNiveaux();
    }

    public int presentationMenu() {
        niveau.niveauxPossible();
        return niveau.getNiveauDebloque();
    }

    public void choixNiveau(int n) {
        niveau.choixNiveau(n);
    }

    public Game startGame() {
        Game g = new Game(niveau);
        Thread threadGame = new Thread(g);
        threadGame.start();
        return g;
    }

}
