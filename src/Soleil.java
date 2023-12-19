public class Soleil {

    private int nbSoleil = 25;
    private long timer;

    public int getNbSoleil() {
        return nbSoleil;
    }

    public void setNbSoleil(int nbSoleil) {
        this.nbSoleil = nbSoleil;
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
