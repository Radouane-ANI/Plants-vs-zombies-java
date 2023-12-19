import java.util.Scanner;

public class MainMenu {

    private GestionnaireNiveaux niveau;

    public MainMenu() {
        niveau = new GestionnaireNiveaux();
    }

    public void presentationMenu() {
        niveau.niveauxPossible();
        Scanner sc = new Scanner(System.in);
        int choixNiveau = sc.nextInt();
        while (!niveau.choixNiveau(choixNiveau)) {
            if (choixNiveau == -1) {
                System.exit(0);
            }
            choixNiveau = sc.nextInt();
        }
    }

    public void startGame() {
        Thread threadGame = new Thread(new Game(niveau));
        threadGame.start();
    }

}
