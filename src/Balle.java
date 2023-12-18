public class Balle {

    private int degat;
    private double x, y;
    private long timer;

    public Balle(int degat, double x, double y) {
        this.degat = degat;
        this.x = x;
        this.y = y;
        this.timer = System.nanoTime();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void toucher(Zombies zombies) {
        zombies.subir(degat);
    }

    public void avance() {
        timer = System.nanoTime() - timer;
        y = y + 1 * (2*1E-9 * timer);
        timer = System.nanoTime();
    }
}