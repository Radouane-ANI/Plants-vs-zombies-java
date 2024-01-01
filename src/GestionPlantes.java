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
                plantes = PlantesNormale.generesPlantesAttaquante(x, y);
                break;
            // case 2:
            // plantes = 
            // break;
            case 3:
                plantes = PlantesNormale.generesPlantesMuraille(x, y);
                break;
            case 4:
                plantes = new BombeCerise(x, y);
                break;
            case 5:
                plantes = new PlanteGelee(x, y);
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
