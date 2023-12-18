import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class GestionnaireNiveaux {
    private static final Map<Integer, List<ApparitionZombie>> niveaux = new HashMap<>();
    private int niveauDebloque = 1;
    private int niveauEnCours;

    static {
        Random rd = new Random();
        ApparitionZombie[] n1 = { new ApparitionZombie(Zombies.generesZombieNormale(rd.nextInt(getLargeur(1))), 2500L),
                new ApparitionZombie(Zombies.generesZombieNormale(rd.nextInt(getLargeur(1))), 4500L),
                new ApparitionZombie(Zombies.generesZombieNormale(rd.nextInt(getLargeur(1))), 9300L),
                new ApparitionZombie(Zombies.generesZombieNormale(rd.nextInt(getLargeur(1))), 1600L),
                new ApparitionZombie(Zombies.generesZombieNormale(rd.nextInt(getLargeur(1))), 1650L) };
        niveaux.put(1, List.of(n1));
    }

    public static int getLargeur(int niveaux) {
        if (niveaux == 1)
            return 1;
        if (niveaux < 4)
            return 3;
        return 5;
    }

    public Zombies nextZombies(int niveau, long temps) {
        for (ApparitionZombie zombie : niveaux.get(niveauEnCours)) {
            if (zombie.getApparition() < temps && !zombie.estApparu()) {
                zombie.setEstApparu(true);
                return zombie.getZombies();
            }
        }
        return null;
    }

    public boolean tousApparus(){
        for (ApparitionZombie zombie : niveaux.get(niveauEnCours)) {
            if (!zombie.estApparu()) {
                return false;
            }
        }
        return true;
    }

    private static class ApparitionZombie {
        private Zombies zombies;
        private Long apparition;
        private boolean estApparu;

        public ApparitionZombie(Zombies zombies, Long apparition) {
            this.zombies = zombies;
            this.apparition = apparition;
        }

        public Zombies getZombies() {
            return zombies;
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
