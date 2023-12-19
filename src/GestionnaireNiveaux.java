import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class GestionnaireNiveaux {
    private static final Map<Integer, List<ApparitionZombie>> niveaux = new HashMap<>();
    private int niveauDebloque = 1;
    private int niveauEnCours;

    static {
                ApparitionZombie[] n1 = { new ApparitionZombie(1, 4500L),
                new ApparitionZombie(1, 17500L),
                new ApparitionZombie(1, 27000L),
                new ApparitionZombie(1, 46000L),
                new ApparitionZombie(1, 47500L) };
        niveaux.put(1, List.of(n1));

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
        for (ApparitionZombie zombie : niveaux.get(niveauEnCours)) {
            if (zombie.getApparition() < temps && !zombie.estApparu()) {
                zombie.setEstApparu(true);
                return Zombies.generesZombieNormale(rd.nextInt(getLargeur()));
            }
        }
        return null;
    }

    public boolean tousApparus() {
        for (ApparitionZombie zombie : niveaux.get(niveauEnCours)) {
            if (!zombie.estApparu()) {
                return false;
            }
        }
        return true;
    }

    public void resetNiveau() {
        for (ApparitionZombie zombie : niveaux.get(niveauEnCours)) {
            zombie.setEstApparu(false);
        }
    }

    public void debloqueNiveau() {
        if (niveaux.size() > niveauDebloque) {
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

    private static class ApparitionZombie {
        private int type;
        private Long apparition;
        private boolean estApparu;

        public ApparitionZombie(int type, Long apparition) {
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
    }

}
