import java.util.Scanner;

public class MainMenu {

    private GestionnaireNiveaux niveau;
    private boolean graphique = true;

    public MainMenu(boolean graphique) {
        niveau = new GestionnaireNiveaux();
        this.graphique = graphique;
    }

    public void setGraphique(boolean graphique) {
        this.graphique = graphique;
        App.setVisible(graphique);
    }

    public int presentationMenu() {
        niveau.niveauxPossible();
        return niveau.getNiveauDebloque();
    }

    public void choixNiveau(int n) {
        niveau.choixNiveau(n);
    }

    public void choixNiveau() {
        Scanner sc = new Scanner(System.in);
        int choixNiveau = sc.nextInt();
        while (true) {
           if  (choixNiveau == -1) {
                System.exit(0);
            } else if (choixNiveau == -2) {
                setGraphique(true);
                App.showMenu();
                break;
            }
            if (niveau.choixNiveau(choixNiveau)) {
                startGame();
                break;
            } else {
                choixNiveau = sc.nextInt();

            }
        }
    }

    public Game startGame() {
        Game g = new Game(niveau, graphique);
        Thread threadGame = new Thread(g);
        threadGame.start();
        return g;
    }

}
