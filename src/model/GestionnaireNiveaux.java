package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import model.zombie.Zombies;

public class GestionnaireNiveaux {
    private static final Map<Integer, List<Paire>> zombiesParNiveaux = new HashMap<>();
    private static final Map<Integer, List<Paire>> plantesParNiveaux = new HashMap<>();
    private static int niveauDebloque = 1;
    private int niveauEnCours;
    private boolean marathon;
    private long prochainZombieMarathon = 8000;

    static {
        zombiesParNiveaux.put(1, genereNiveau(5, 1, 4500L));
        zombiesParNiveaux.put(2, genereNiveau(14, 1, 11500L));
        zombiesParNiveaux.put(3, genereNiveau(18, 3, 12500L));
        zombiesParNiveaux.put(4, genereNiveau(25, 4, 12123L));
        zombiesParNiveaux.put(5, genereNiveau(32, 4, 13123L));
        zombiesParNiveaux.put(6, genereNiveau(40, 4, 12500L));
        zombiesParNiveaux.put(7, genereNiveau(41, 5, 11023L));
        zombiesParNiveaux.put(8, genereNiveau(46, 5, 12000L));
        zombiesParNiveaux.put(9, genereNiveau(52, 6, 13023L));
        zombiesParNiveaux.put(10, genereNiveau(65, 6, 12345L));

        Paire[] pairePlante = { new Paire(1, 5000L), new Paire(2, 8000L), new Paire(3, 10000L), new Paire(4, 20000L),
                new Paire(5, 10000L), new Paire(6, 20000L), new Paire(7, 5000L) };
        plantesParNiveaux.put(-3, List.of(pairePlante));

        plantesParNiveaux.put(1, List.of(pairePlante[0]));
        plantesParNiveaux.put(2, List.of(pairePlante[0], pairePlante[1]));
        plantesParNiveaux.put(3, List.of(pairePlante[0], pairePlante[1], pairePlante[3]));
        plantesParNiveaux.put(4, List.of(pairePlante[0], pairePlante[1], pairePlante[3]));
        plantesParNiveaux.put(5, List.of(pairePlante[0], pairePlante[1], pairePlante[2], pairePlante[3]));
        plantesParNiveaux.put(6, List.of(pairePlante[0], pairePlante[1], pairePlante[2], pairePlante[3]));
        plantesParNiveaux.put(7,
                List.of(pairePlante[0], pairePlante[1], pairePlante[2], pairePlante[3], pairePlante[4]));
        plantesParNiveaux.put(8, List.of(pairePlante[0], pairePlante[1], pairePlante[2], pairePlante[3], pairePlante[5],
                pairePlante[4]));
        plantesParNiveaux.put(9, List.of(pairePlante));
        plantesParNiveaux.put(10, List.of(pairePlante));

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

    public void setMarathon(boolean marathon) {
        this.marathon = marathon;
    }

    public int getNiveauDebloque() {
        return niveauDebloque;
    }

    public int getNiveauEnCours() {
        return niveauEnCours;
    }

    public int getLargeur() {
        if (marathon) {
            return 5;
        }
        if (niveauEnCours == 1)
            return 1;
        if (niveauEnCours < 4)
            return 3;
        return 5;
    }

    public Zombies nextZombie(long temps) {
        if (marathon) {
            return nextZombieMarathon(temps);
        } else {
            Random rd = new Random();
            for (Paire zombie : zombiesParNiveaux.get(niveauEnCours)) {
                if (zombie.getApparition() < temps && !zombie.estApparu()) {
                    zombie.setEstApparu(true);
                    return Zombies.generesZombie(rd.nextInt(getLargeur()), zombie.type);
                }
            }
            return null;
        }
    }

    public Zombies nextZombieMarathon(long temps) {
        Random rd = new Random();
        if (prochainZombieMarathon < temps) {
            prochainZombieMarathon += (5 + rd.nextInt(12)) * 1000;
            int valeurRandom = rd.nextInt(100);
            int type = -1;
            if (valeurRandom < 30) {
                type = 1;
            } else if (valeurRandom < 55) {
                type = 2;
            } else if (valeurRandom < 75) {
                type = 3;
            } else if (valeurRandom < 90) {
                type = 4;
            } else {
                type = 5;
            }
            return Zombies.generesZombie(rd.nextInt(getLargeur()), type);
        }
        return null;
    }

    public List<Paire> plantesDisponibles() {
        return plantesParNiveaux.get(niveauEnCours);
    }

    public boolean tousApparus() {
        if (marathon) {
            return false;
        }
        for (Paire zombie : zombiesParNiveaux.get(niveauEnCours)) {
            if (!zombie.estApparu()) {
                return false;
            }
        }
        return true;
    }

    public void resetNiveau() {
        if (!marathon) {
            for (Paire zombie : zombiesParNiveaux.get(niveauEnCours)) {
                zombie.setEstApparu(false);
            }
        }
        for (Paire plantes : plantesParNiveaux.get(niveauEnCours)) {
            plantes.setChargement(0);
        }
    }

    public void debloqueNiveau() {
        if (zombiesParNiveaux.size() > niveauDebloque && !marathon) {
            niveauDebloque++;
        }
    }

    public boolean choixNiveau(int niveau) {
        if (niveau == -3) {
            this.niveauEnCours = niveau;
            this.marathon = true;
            return true;
        }
        if (niveau > 0 && niveau <= niveauDebloque) {
            this.niveauEnCours = niveau;
            this.marathon = false;
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

        public void setChargement(double chargement) {
            this.chargement = chargement;
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
                case 6:
                    couts = 50;
                    break;
                case 7:
                    couts = 150;
                    break;

            }
            return couts;
        }
    }

}
