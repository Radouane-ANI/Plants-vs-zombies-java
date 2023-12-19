public class Zombies {

    private int vie, degat;
    private char nom;
    private long recharge;
    private double x, y;
    private double vitesse;
    private long timer;

    public Zombies(int vie, int degat, char nom, double x, double y, double vitesse) {
        this.vie = vie;
        this.degat = degat;
        this.nom = nom;
        this.x = x;
        this.y = y;
        this.recharge = System.currentTimeMillis();
        this.vitesse = vitesse;
        this.timer = System.nanoTime();
    }

    public static Zombies generesZombieNormale(int x){
        return new Zombies(190,30,'n',x,8.99,1.25);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getVitesse() {
        return vitesse;
    }

    public void attaquer(Plantes plantes) {
        if (System.currentTimeMillis() - recharge > 1000) {
            plantes.subir(degat);
            recharge = System.currentTimeMillis();
        }
    }

    public boolean enVie() {
        return vie > 0;
    }

    public void subir(int degat) {
        this.vie -= degat;
    }

    public String toString() {
        return nom + "";
    }

    public void avance() {
        timer = System.nanoTime() - timer;
        y = y - 1 * (vitesse * 1E-10 * timer);
        timer = System.nanoTime();

    }
}
