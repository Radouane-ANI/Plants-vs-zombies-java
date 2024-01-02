import java.awt.Toolkit;
import javax.swing.JFrame;

public class App {

    private static JFrame fenetre;
    private static boolean visible = true;

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

    public static void setVisible(boolean graphique) {
        fenetre.setVisible(graphique);
        visible = graphique;
    }

    public static void repaint() {
        fenetre.getContentPane().repaint();
        Toolkit.getDefaultToolkit().sync();
    }

    public static void showMenu() {
        if (visible) {
            fenetre.getContentPane().removeAll();
            fenetre.getContentPane().add(new MenuScene());
            fenetre.revalidate();
            fenetre.repaint();
            Toolkit.getDefaultToolkit().sync();
        } else {
            MainMenu menu = new MainMenu(false);
            menu.presentationMenu();
            menu.choixNiveau();
        }
    }

    public static void showGame(Game g) {
        fenetre.getContentPane().removeAll();
        fenetre.getContentPane().add(new GameScene(g));
        fenetre.revalidate();
        fenetre.repaint();
        Toolkit.getDefaultToolkit().sync();
    }

}
