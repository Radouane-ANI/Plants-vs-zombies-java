package controleur;

import java.util.Scanner;

import model.GestionnaireNiveaux;
import model.Plateau;
import model.Soleil;
import model.plante.BombeCerise;
import model.plante.Chomper;
import model.plante.PLanteMine;
import model.plante.PlanteGelee;
import model.plante.PlanteMuraille;
import model.plante.Plantes;
import model.plante.PlantesNormale;
import model.plante.Tournesol;

import java.util.List;

public class GestionPlantes {

    private Plateau plateau;
    private Soleil soleil;
    private GestionnaireNiveaux niveaux;
    private Scanner sc = new Scanner(System.in);

    public GestionPlantes(Plateau plateau, Soleil soleil, GestionnaireNiveaux niveaux) {
        this.plateau = plateau;
        this.soleil = soleil;
        this.niveaux = niveaux;
    }

    public void ajouterPlante(int x, int y, int type) {
        Plantes plantes = null;
        switch (type) {
            case 1:
                plantes = new PlantesNormale(x, y);
                break;
            case 2:
                plantes = new Tournesol(x, y);
                break;
            case 3:
                plantes = new PlanteMuraille(x, y);
                break;
            case 4:
                plantes = new BombeCerise(x, y);
                break;
            case 5:
                plantes = new PlanteGelee(x, y);
                break;
            case 6:
                plantes = new PLanteMine(x, y);
                break;
            case 7:
                plantes = new Chomper(x, y);
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
        if (plantesDispo()) {
            System.out.println("pour poser une plante vous devez d'abord donner sa coordonnee en x puis y");
            System.out.println("x compris entre 0 et " + (plateau.getLargeur() - 1) + " et y compris entre 0 et 8");
            System.out.println("si vous ne voulez rien poser entrez -1");
            System.out.println();
            System.out.println("vous placez une plante");
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
            if (x == -1) {
                System.exit(0);
            }
        }
    }

    public void placerArrosoir() {
        System.out.println("vous placez un arrosoir");
        System.out.println("x :");
        int x = sc.nextInt();
        if (x != -1) {
            System.out.println("y :");
            int y = sc.nextInt();
            if (!plateau.arrose(x, y)) {
                plateau.addArrosoir();
            }
        }
        if (x == -1) {
            System.exit(0);
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
            System.out.println(paire.getType() + " cout :" + paire.getCouts());
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
