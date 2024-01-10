package model.zombie;
import javax.swing.ImageIcon;

public class Zombiejournal extends Zombies {
    private boolean journalPerdu;

    public Zombiejournal(int x) {
        super(350, 30, new String[] { "/Images/zombiejournal1.png", "/Images/zombiejournal2.png" }, x, 8.99, 1.2,'j');
        this.journalPerdu = false;
    }

    @Override
    public void avance() {
        if (vie <= 181 && !journalPerdu) {
            journalPerdu = true;
            vitesse *= 2;
            changeSkin();
            super.avance();
        } else {
            super.avance();
        }
    }

    private void changeSkin() {
        image[0] = new ImageIcon(getClass().getResource("/Images/zombiesansjournal1.png")).getImage();
        image[1] = new ImageIcon(getClass().getResource("/Images/zombiesansjournal2.png")).getImage();
    }

}
