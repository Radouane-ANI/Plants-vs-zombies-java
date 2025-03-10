package model.balle;
import javax.swing.ImageIcon;

import model.zombie.Zombies;

public class BalleGelee extends Balle{

    public BalleGelee(int degat, double x, double y) {
        super(degat, x, y);
        super.image = new ImageIcon(getClass().getResource("/Images/balleg.png")).getImage();
    }

    @Override
    public boolean toucher(Zombies zombies) {
        zombies.ralentir();
        return super.toucher(zombies);
    }
}