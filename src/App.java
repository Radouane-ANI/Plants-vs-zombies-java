import java.awt.Toolkit;
import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

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
       //showStartWindow();
    }

    public static void repaint() {
        fenetre.getContentPane().repaint();
        Toolkit.getDefaultToolkit().sync();
    }

    public static void showMenu() {
        fenetre.getContentPane().removeAll();
        fenetre.getContentPane().add(new MenuScene());
        fenetre.revalidate();
        fenetre.repaint();
        Toolkit.getDefaultToolkit().sync();
    }

    public static void showGame(Game g) {
        fenetre.getContentPane().removeAll();
        fenetre.getContentPane().add(new GameScene(g));
        fenetre.revalidate();
        fenetre.repaint();
        Toolkit.getDefaultToolkit().sync();
    }

    public static void showStartWindow() {
        fenetre.getContentPane().removeAll();
        
        // Créez un bouton "Jouer"
        JButton jouerButton = new JButton("Jouer");
        jouerButton.setBounds(400, 250, 100, 20);
        
        // Ajoutez un gestionnaire d'événements pour le bouton "Jouer"
        jouerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showMenu(); // Redirige vers le menu principal (MenuScene)
               
            }
        });
        
        // Ajoutez le bouton à la fenêtre
        fenetre.getContentPane().setLayout(null);
        fenetre.getContentPane().add(jouerButton);
        
        fenetre.revalidate();
        fenetre.repaint();
        Toolkit.getDefaultToolkit().sync();
    }

}
