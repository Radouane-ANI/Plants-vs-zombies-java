import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuScene extends JPanel {

    private Image Accueil ; // Ajout de la variable pour l'image de fond

    public MenuScene() {
        MainMenu menu = new MainMenu();

        // Chargement de l'image de fond
        Accueil = new ImageIcon(getClass().getResource("/Images/Accueil.png")).getImage();

         JLabel phraseLabel = new JLabel("Choisissez parmi les niveaux disponibles:");
         phraseLabel.setForeground(Color.MAGENTA);
        add(phraseLabel);
        
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

         // Création du bouton Exit
         JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.BOLD, 14)); // Changer la police et la taille
        exitButton.setForeground(Color.WHITE); // Changer la couleur du texte
        exitButton.setBackground(Color.RED); // Changer la couleur d'arrière-plan
        exitButton.setFocusPainted(false); // Enlever la mise en évidence lorsqu'on clique
        exitButton.setBorder(BorderFactory.createRaisedBevelBorder()); // Ajouter une bordure

         exitButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 // Code à exécuter lorsqu'on clique sur le bouton Exit
                 System.exit(0); // Ferme l'application
             }
         });
         
         add(exitButton); // Ajout du bouton Exit au panneau
     }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(Accueil, 0, 0, getWidth(), getHeight(), this);
    }
}