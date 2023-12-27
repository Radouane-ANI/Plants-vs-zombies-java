public class Soleil {

    private static int nbSoleil = 25;
    private long timer;

    public Soleil() {
        nbSoleil = 25;
    }

    public static int getNbSoleil() {
        return nbSoleil;
    }

    public void setNbSoleil(int nb) {
        nbSoleil = nb;
    }

    public void generesSoleil() {
        if (System.currentTimeMillis() - timer > 5000) {
            nbSoleil += 25;
            timer = System.currentTimeMillis();
        }
    }

    public void soustraitSoleil(int nb) {
        nbSoleil -= nb;
    }

    public void ajouteSoleil() {
        nbSoleil += 25;
    }

    public String toString() {
        return nbSoleil + "";
    }
}
