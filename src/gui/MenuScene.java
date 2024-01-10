package gui;
import javax.swing.*;

import controleur.MainMenu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuScene extends JPanel {
    private boolean selectionner = true;

    public MenuScene() {
        MainMenu menu = new MainMenu(true);
        int nbNiveau = menu.presentationMenu();

        // Chargement de l'image de fond
        ImageIcon accueil = new ImageIcon(getClass().getResource("/Images/Accueil.png"));
        JLabel fond = new JLabel(accueil);
        fond.setLayout(new BorderLayout());
        add(fond);

        JPanel panneauNiveau = new JPanel();
        panneauNiveau.setOpaque(false);
        fond.add(panneauNiveau, BorderLayout.CENTER);

        JPanel autreBoutton = new JPanel();
        autreBoutton.setOpaque(false);
        fond.add(autreBoutton, BorderLayout.SOUTH);

        JLabel phraseLabel = new JLabel("Choisissez parmi les niveaux debloqués:");
        phraseLabel.setForeground(Color.MAGENTA);
        fond.add(phraseLabel, BorderLayout.NORTH);

        for (int i = 1; i <= nbNiveau; i++) {
            JButton niveauButton = new JButton("Niveau : " + i);

            niveauButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String numeroNiveauStr = niveauButton.getText().replaceAll("[^0-9]", "");
                    int numeroNiveau = Integer.parseInt(numeroNiveauStr);
                    menu.choixNiveau(numeroNiveau);
                    App.showGame(menu.startGame(), selectionner);
                }
            });

            panneauNiveau.add(niveauButton);
        }

        JToggleButton mapButton = new JToggleButton("map: jour");
        mapButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                selectionner = !selectionner;
                mapButton.setText(selectionner ? "map: jour" : "map: nuit");
            }
        });
        autreBoutton.add(mapButton);

        JButton textuelButton = new JButton("mode textuel");
        textuelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.setGraphique(false);
                menu.choixNiveau();
            }
        });
        autreBoutton.add(textuelButton);

        JButton marathonButton = new JButton("mode marathon");
        marathonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.choixNiveau(-3);
                App.showGame(menu.startGame(), selectionner);
            }
        });
        autreBoutton.add(marathonButton);

        // Création du bouton Exit
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.BOLD, 14));
        exitButton.setForeground(Color.WHITE);
        exitButton.setBackground(Color.RED);
        exitButton.setFocusPainted(false);
        exitButton.setBorder(BorderFactory.createRaisedBevelBorder());

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        autreBoutton.add(exitButton);
    }
}