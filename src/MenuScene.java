import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuScene extends JPanel {

    public MenuScene() {
        MainMenu menu = new MainMenu();

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
}
