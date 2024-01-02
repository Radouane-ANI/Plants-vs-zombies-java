import java.util.Random;

public class Arrosoir extends GameScene.Carte {
    private static Random rd = new Random();
    private GameScene gameScene;

    public Arrosoir(GameScene gameScene) {
        gameScene.super(50, 50 + rd.nextInt(350), 0);
        this.gameScene = gameScene;
    }

    @Override
    public void charge() {
        setCharge(false);
    }

    @Override
    public void posePlante(int x, int y) {
        if (gameScene.arrose(x, y)) {
            this.setVisible(false);
        }
    }

}
