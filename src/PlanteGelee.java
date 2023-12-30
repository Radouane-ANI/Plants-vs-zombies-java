public class PlanteGelee extends PlantesNormale {

    public PlanteGelee(int x, int y) {
        super(130, 10, 'g', x, y, 175, new String[]{"/Images/peag1.png", "/Images/peag2.png"});
    }

    @Override
    public void agir(boolean zombieLane) {
        if (zombieLane) {
            if (System.currentTimeMillis() - recharge > 2250) {
                Plateau.addBalle(new BalleGelee(degat, x, y + 0.75));
                recharge = System.currentTimeMillis();
            }
        }
    }
}
