import java.util.Scanner;

public class GestionPlantes {

    private Plateau plateau;
    private Soleil soleil;

    public GestionPlantes(Plateau plateau, Soleil soleil) {
        this.plateau = plateau;
        this.soleil = soleil;
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
                if (x >= 0 && y >= 0 && x < plateau.getLargeur() && y < 9) {
                    this.ajouterPlante(new PlantesAttaquantes(130, 20, 'a', x, y, 100));
                }
            }
        }
    }
}
