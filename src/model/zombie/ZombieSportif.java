package model.zombie;
import model.plante.Plantes;

public class ZombieSportif extends Zombies {
    private boolean javelotPerdu;

    public ZombieSportif(int x) {
        super(500, 50, new String[] { "/Images/javelotZombie2.png", "/Images/javelotZombie.png" }, x, 8.99, 1.75, 'o');
        this.javelotPerdu = false;
    }

    @Override
    public void attaquer(Plantes plantes) {
        if (!javelotPerdu) {
            super.setY(super.getY() - 1);
            vitesse -= 0.5;
            javelotPerdu = true;
        } else {
            super.attaquer(plantes);
        }
    }
}
