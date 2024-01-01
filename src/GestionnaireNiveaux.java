import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class GestionnaireNiveaux {
    private static final Map<Integer, List<Paire>> zombiesParNiveaux = new HashMap<>();
    private static final Map<Integer, List<Paire>> plantesParNiveaux = new HashMap<>();
    private static int niveauDebloque = 1;
    private int niveauEnCours;

    static {
        Paire[] z1 = { new Paire(1, 4500L), new Paire(1, 17500L),
                new Paire(1, 29000L), new Paire(2, 49000L), new Paire(1, 54000L) };
        Paire[] z2 = { new Paire(1, 11500L), new Paire(1, 21500L),
                new Paire(1, 31500L), new Paire(1, 40500L), new Paire(1, 53000L),
                new Paire(1, 62500L), new Paire(1, 75000L), new Paire(1, 83000L),
                new Paire(2, 90000L), new Paire(1, 96500L), new Paire(1, 99500L),
                new Paire(1, 102500L), new Paire(1, 109500L), new Paire(1, 115000L),
                new Paire(1, 119500L) };
        Paire[] z3 = {
                new Paire(1, 12500L), new Paire(1, 23500L),
                new Paire(1, 35500L), new Paire(1, 48500L), new Paire(1, 60000L),
                new Paire(3, 70500L), new Paire(1, 84000L), new Paire(3, 95000L),
                new Paire(2, 106000L), new Paire(3, 117500L), new Paire(1, 122500L),
                new Paire(1, 130000L), new Paire(4, 138000L), new Paire(1, 144500L),
                new Paire(1, 150000L), new Paire(3, 156500L), new Paire(1, 162500L) };
        Paire[] z4 = {
                new Paire(1, 12000L), new Paire(1, 21500L), new Paire(1, 32500L), new Paire(3, 36000L),
                new Paire(1, 46000L), new Paire(1, 56000L), new Paire(1, 67000L), new Paire(4, 78000L),
                new Paire(1, 89000L), new Paire(1, 91000L), new Paire(1, 103000L), new Paire(2, 114000L),
                new Paire(3, 119000L), new Paire(1, 124000L), new Paire(3, 129000L), new Paire(4, 134000L),
                new Paire(3, 139000L), new Paire(1, 144000L), new Paire(1, 149000L), new Paire(3, 154000L),
                new Paire(4, 159000L), new Paire(1, 165000L), new Paire(4, 169000L), new Paire(1, 172000L) };
        zombiesParNiveaux.put(1, List.of(z1));
        zombiesParNiveaux.put(2, List.of(z2));
        zombiesParNiveaux.put(3, List.of(z3));
        zombiesParNiveaux.put(4, List.of(z4));

        Paire[] pairePlante = { new Paire(1, 5000L), new Paire(2, 8000L), new Paire(3, 10000L), new Paire(4, 20000L),
                new Paire(5, 10000L) };
        plantesParNiveaux.put(1, List.of(pairePlante[0]));
        plantesParNiveaux.put(2, List.of(pairePlante[0], pairePlante[1]));
        plantesParNiveaux.put(3, List.of(pairePlante[0], pairePlante[1], pairePlante[3]));
        plantesParNiveaux.put(4, List.of(pairePlante[0], pairePlante[1], pairePlante[2], pairePlante[4], pairePlante[3]));
    }

    public int getNiveauDebloque() {
        return niveauDebloque;
    }

    public int getNiveauEnCours() {
        return niveauEnCours;
    }

    public int getLargeur() {
        if (niveauEnCours == 1)
            return 1;
        if (niveauEnCours < 4)
            return 3;
        return 5;
    }

    public Zombies nextZombie(long temps) {
        Random rd = new Random();
        for (Paire zombie : zombiesParNiveaux.get(niveauEnCours)) {
            if (zombie.getApparition() < temps && !zombie.estApparu()) {
                zombie.setEstApparu(true);
                return Zombies.generesZombie(rd.nextInt(getLargeur()), zombie.type);
            }
        }
        return null;
    }

    public List<Paire> plantesDisponibles() {
        return plantesParNiveaux.get(niveauEnCours);
    }

    public boolean tousApparus() {
        for (Paire zombie : zombiesParNiveaux.get(niveauEnCours)) {
            if (!zombie.estApparu()) {
                return false;
            }
        }
        return true;
    }

    public void resetNiveau() {
        for (Paire zombie : zombiesParNiveaux.get(niveauEnCours)) {
            zombie.setEstApparu(false);
        }
    }

    public void debloqueNiveau() {
        if (zombiesParNiveaux.size() > niveauDebloque) {
            niveauDebloque++;
        }
    }

    public boolean choixNiveau(int niveau) {
        if (niveau > 0 && niveau <= niveauDebloque) {
            this.niveauEnCours = niveau;
            return true;
        }
        return false;
    }

    public void niveauxPossible() {
        System.out.println("Vous pouvez choisir parmis ces niveaux :");
        for (int i = 1; i <= niveauDebloque; i++) {
            System.out.println("Niveaux : " + i);
        }
    }

    public double pourcentageDispo(int type) {
        for (Paire plante : plantesParNiveaux.get(niveauEnCours)) {
            if (plante.getType() == type) {
                return plante.pourcentageDispo();
            }
        }
        return 0;
    }

    public void plantePoser(int type) {
        for (Paire plante : plantesParNiveaux.get(niveauEnCours)) {
            if (plante.getType() == type) {
                plante.EstPoser();
            }
        }
    }

    public int getCoutsPlante(int type) {
        for (Paire plante : plantesParNiveaux.get(niveauEnCours)) {
            if (plante.getType() == type) {
                return plante.getCouts();
            }
        }
        return Integer.MAX_VALUE;
    }

    public static class Paire {
        private int type;
        private long apparition;
        private boolean estApparu;
        private double chargement;

        public Paire(int type, long apparition) {
            this.type = type;
            this.apparition = apparition;
        }

        public int getType() {
            return type;
        }

        public Long getApparition() {
            return apparition;
        }

        public boolean estApparu() {
            return this.estApparu;
        }

        public void setEstApparu(boolean estApparu) {
            this.estApparu = estApparu;
        }

        public void EstPoser() {
            chargement = System.currentTimeMillis();
        }

        public double pourcentageDispo() {
            return (System.currentTimeMillis() - chargement) / apparition;
        }

        public int getCouts() {
            int couts = 100;
            switch (type) {
                case 1:
                    couts = 100;
                    break;
                case 2:
                    couts = 50;
                    break;
                case 3:
                    couts = 50;
                    break;
                case 4:
                    couts = 150;
                    break;
                case 5:
                    couts = 175;
                    break;
            }
            return couts;
        }
    }

}
