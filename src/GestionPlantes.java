import java.util.Scanner;
import java.util.List;

public class GestionPlantes {

    private Plateau plateau;
    private Soleil soleil;
    private GestionnaireNiveaux niveaux;

    public GestionPlantes(Plateau plateau, Soleil soleil, GestionnaireNiveaux niveaux) {
        this.plateau = plateau;
        this.soleil = soleil;
        this.niveaux = niveaux;
    }

    public void ajouterPlante(Plantes plantes) {
        if (plantes.getCouts() <= soleil.getNbSoleil()) {
            if (plateau.addPlante(plantes)) {
                soleil.soustraitSoleil(plantes.getCouts());
            }
        }
    }

    public void placerPlante() {
        Scanner sc = new Scanner(System.in);
        if (soleil.getNbSoleil() >= 100) {
            System.out.println("x :");
            int x = sc.nextInt();
            if (x != -1) {
                System.out.println("y :");
                int y = sc.nextInt();
                this.plantesDisponibles();
                char c = sc.next().charAt(0);
                this.placerPlante(x, y, c);
            }
        }
    }

    public void placerPlante(int x, int y, char c) {
        if (x >= 0 && y >= 0 && x < plateau.getLargeur() && y < 9) {
            switch (c) {
                case 'a':
                    String[] img = { "/Images/peashooter1.png", "/Images/peashooter2.png" };
                    this.ajouterPlante(new PlantesAttaquantes(130, 20, 'a', x, y, 100, img));
                    break;
            }
        }
    }

    public List<Character> plantesDisponibles() {
        List<Character> dispo = niveaux.plantesDisponibles();
        for (Character character : dispo) {
            System.out.println(character);
        }
        return dispo;
    }
}
