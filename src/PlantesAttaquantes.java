public class PlantesAttaquantes implements Plantes {
    private int vie, degat;
    private char nom;
    private long recharge;
    private int x, y;

    public PlantesAttaquantes(int vie, int degat, char nom, int x, int y) {
        this.vie = vie;
        this.degat = degat;
        this.nom = nom;
        this.recharge = System.currentTimeMillis();
        this.x = x;
        this.y = y;
    }

    @Override
    public void agir(boolean zombieLane) {
        if (zombieLane) {
            if (System.currentTimeMillis() - recharge > 1500) {
                Plateau.addBalle(new Balle(degat, x, y));
                recharge = System.currentTimeMillis();
            }
        }
    }

    @Override
    public boolean enVie() {
        return vie > 0;
    }

    @Override
    public void subir(int degat) {
        this.vie -= degat;
    }

    public String toString() {
        return nom + "";
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

}
