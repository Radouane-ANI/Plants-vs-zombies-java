import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class GestionnaireNiveaux {
    private static final Map<Integer, List<ApparitionZombie>> zombiesParNiveaux = new HashMap<>();
    private static final Map<Integer, List<Character>> plantesParNiveaux = new HashMap<>();
    private int niveauDebloque = 1;
    private int niveauEnCours;

    static {
        ApparitionZombie[] z1 = { new ApparitionZombie(1, 4500L),
                new ApparitionZombie(1, 17500L),
                new ApparitionZombie(1, 29000L),
                new ApparitionZombie(2, 49000L),
                new ApparitionZombie(1, 54000L) };
        zombiesParNiveaux.put(1, List.of(z1));

        Character[] p1 = { 'a' };
        plantesParNiveaux.put(1, List.of(p1));
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
        for (ApparitionZombie zombie : zombiesParNiveaux.get(niveauEnCours)) {
            if (zombie.getApparition() < temps && !zombie.estApparu()) {
                zombie.setEstApparu(true);
                Zombies z = null;
                switch (zombie.getType()) {
                    case 1:
                        z = Zombies.generesZombieNormale(rd.nextInt(getLargeur()));
                        break;
                    case 2:
                        z = Zombies.generesZombieDrapeau(rd.nextInt(getLargeur()));
                        break;
                }
                return z;
            }
        }
        return null;
    }

    public List<Character> plantesDisponibles() {
        return plantesParNiveaux.get(niveauEnCours);
    }

    public boolean tousApparus() {
        for (ApparitionZombie zombie : zombiesParNiveaux.get(niveauEnCours)) {
            if (!zombie.estApparu()) {
                return false;
            }
        }
        return true;
    }

    public void resetNiveau() {
        for (ApparitionZombie zombie : zombiesParNiveaux.get(niveauEnCours)) {
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
