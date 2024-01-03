import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuScene extends JPanel {

    public MenuScene() {
        MainMenu menu = new MainMenu(true);
        int nbNiveau = menu.presentationMenu();
        for (int i = 1; i <= nbNiveau; i++) {
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
        JButton textuelButton = new JButton("mode textuel");
        textuelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.setGraphique(false);
                menu.choixNiveau();
            }

        });
        add(textuelButton);
        JButton marathonButton = new JButton("mode marathon");
        marathonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.choixNiveau(-3);
                App.showGame(menu.startGame());
            }

        });
        add(marathonButton);

    }
}
