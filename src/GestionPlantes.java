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

    public void ajouterPlante(int x, int y, int type) {
        Plantes plantes = null;
        switch (type) {
            case 1:
                String[] img = { "/Images/peashooter1.png", "/Images/peashooter2.png" };
                plantes = new PlantesAttaquantes(130, 20, 'a', x, y, 100, img);
                break;
        }
        if (plantes != null && plantes.getCouts() <= Soleil.getNbSoleil()) {
            if (plateau.addPlante(plantes)) {
                soleil.soustraitSoleil(plantes.getCouts());
                niveaux.plantePoser(type);
            }
        }
    }

    public void placerPlante() {
        Scanner sc = new Scanner(System.in);
        if (Soleil.getNbSoleil() >= 100) {
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

    public void placerPlante(int x, int y, int type) {
        if (x >= 0 && y >= 0 && x < plateau.getLargeur() && y < 9 && niveaux.pourcentageDispo(type) >= 1) {
            this.ajouterPlante(x, y, type);
        }
    }

    public List<GestionnaireNiveaux.Paire> plantesDisponibles() {
        List<GestionnaireNiveaux.Paire> dispo = niveaux.plantesDisponibles();
        for (GestionnaireNiveaux.Paire paire : dispo) {
            System.out.println(paire.getType());
        }
        return dispo;
    }

    public double pourcentageDispo(int type) {
        return niveaux.pourcentageDispo(type);
    }
}
