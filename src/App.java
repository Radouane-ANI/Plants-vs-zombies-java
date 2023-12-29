import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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

        // Ajouter une image de fond
        JLabel backgroundLabel = new JLabel();
        ImageIcon backgroundIcon = new ImageIcon(App.class.getResource("/Images/Accueil.png"));
        backgroundLabel.setIcon(backgroundIcon);
        fenetre.setContentPane(backgroundLabel);

        backgroundLabel.setIcon(backgroundIcon);
        fenetre.setContentPane(backgroundLabel);

        fenetre.setVisible(true);

        JButton jouerButton = new JButton("Jouer");
        jouerButton.addActionListener((ActionListener) new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            fenetre.getContentPane().removeAll();
            showMenu(); // Appelle la méthode pour afficher le menu principal
        }
    });
    

        backgroundLabel.setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(jouerButton);
        backgroundLabel.add(buttonPanel, BorderLayout.SOUTH);

        fenetre.pack();
        fenetre.setVisible(true);
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
   
}
