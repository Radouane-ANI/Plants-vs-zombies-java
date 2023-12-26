import javax.swing.JFrame;

public class App {

    private static JFrame fenetre;

    public static JFrame getFenetre() {
        return fenetre;
    }

    public static void main(String[] args) throws Exception {
        fenetre = new JFrame("Plants Vs Zombies");
        fenetre.setSize(1050, 625);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLocationRelativeTo(null);
        fenetre.setVisible(true);

        showMenu();
    }

    public static void repaint() {
        fenetre.getContentPane().repaint();
    }

    public static void showMenu() {
        fenetre.getContentPane().removeAll();
        fenetre.getContentPane().add(new MenuScene());
        fenetre.revalidate();
        fenetre.repaint();
    }

    public static void showGame(Game g) {
        fenetre.getContentPane().removeAll();
        fenetre.getContentPane().add(new GameScene(g));
        fenetre.revalidate();
        fenetre.repaint();
    }

}
