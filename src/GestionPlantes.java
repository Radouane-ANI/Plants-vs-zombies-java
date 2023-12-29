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
        if (plantesDispo()) {
            System.out.println("x :");
            int x = sc.nextInt();
            if (x != -1) {
                System.out.println("y :");
                int y = sc.nextInt();
                System.out.println("type :");
                this.plantesDisponibles();
                int t = sc.nextInt();
                this.placerPlante(x, y, t);
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

    public boolean plantesDispo() {
        List<GestionnaireNiveaux.Paire> dispo = niveaux.plantesDisponibles();
        for (GestionnaireNiveaux.Paire paire : dispo) {
            double pourcentage = niveaux.pourcentageDispo(paire.getType());
            if ((pourcentage >= 1 || pourcentage < 0)
                    && niveaux.getCoutsPlante(paire.getType()) <= Soleil.getNbSoleil()) {
                return true;
            }
        }
        return false;
    }
}
