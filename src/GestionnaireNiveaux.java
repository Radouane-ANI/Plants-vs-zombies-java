import java.util.ArrayList;
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
        zombiesParNiveaux.put(1, genereNiveau(5, 1, 4500L));
        zombiesParNiveaux.put(2, genereNiveau(14, 1, 11500L));
        zombiesParNiveaux.put(3, genereNiveau(18, 3, 12500L));
        zombiesParNiveaux.put(4, genereNiveau(25, 4, 12123L));
        zombiesParNiveaux.put(5, genereNiveau(30, 4, 12123L));
        zombiesParNiveaux.put(6, genereNiveau(32, 5, 12123L));

        Paire[] pairePlante = { new Paire(1, 5000L), new Paire(2, 8000L), new Paire(3, 10000L), new Paire(4, 20000L),
                new Paire(5, 10000L) };
        plantesParNiveaux.put(1, List.of(pairePlante[0]));
        plantesParNiveaux.put(2, List.of(pairePlante[0], pairePlante[1]));
        plantesParNiveaux.put(3, List.of(pairePlante[0], pairePlante[1], pairePlante[3]));
        plantesParNiveaux.put(4,
                List.of(pairePlante[0], pairePlante[1], pairePlante[2], pairePlante[4], pairePlante[3]));
        plantesParNiveaux.put(5,
                List.of(pairePlante[0], pairePlante[1], pairePlante[2], pairePlante[3], pairePlante[4]));
        plantesParNiveaux.put(6,
                List.of(pairePlante[0], pairePlante[1], pairePlante[2], pairePlante[3], pairePlante[4]));

    }

    public static List<Paire> genereNiveau(int nbZombies, int typeMax, long premier) {
        List<Paire> l = new ArrayList<>();
        l.add(new Paire(1, premier));
        for (int i = 5; i <= nbZombies + 3; i++) {
            int type = (int) ((premier % typeMax) == 0 ? 1 : (premier % typeMax));
            if (i > (nbZombies + 7) / 2) {
                premier += Math.max(40000 / nbZombies + i, Math.min(7000 + i, (Math.log(i) / Math.log(2)) * 1330));
                l.add(new Paire(type, premier));
            } else if (i == (nbZombies + 7) / 2) {
                premier += 20456;
                l.add(new Paire(-1, premier));
            } else {
                premier += Math.min(18000 + i, Math.max(12000 + i, (Math.log(i) / Math.log(2)) * 6000));
                l.add(new Paire(type, premier));
            }
        }
        return l;
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
