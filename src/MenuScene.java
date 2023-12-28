import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuScene extends JPanel {

    private Image jardin; // Ajout de la variable pour l'image de fond

    public MenuScene() {
        MainMenu menu = new MainMenu();

        // Chargement de l'image de fond
        jardin = new ImageIcon(getClass().getResource("/Images/Accueil.png")).getImage();
        
        for (int i = 1; i <= menu.presentationMenu(); i++) {
            JButton niveauButton = new JButton("Niveau : " + i);

            niveauButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String numeroNiveauStr = niveauButton.getText().replaceAll("[^0-9]", "");
                    int numeroNiveau = Integer.parseInt(numeroNiveauStr);
                    menu.choixNiveau(numeroNiveau);
                    App.showGame(menu.startGame());
                }
            });

            add(niveauButton);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(jardin, 0, 0, getWidth(), getHeight(), this);
    }
}