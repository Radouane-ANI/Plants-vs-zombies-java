import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Tournesol extends Plantes{
    private static final int COUTS = 50;
    private static final long DELAI_GENERATION = 10000; // 40 secondes en millisecondes

    private JPanel panel;
    private JLabel soleilImage;
    private JLabel nbsoleil;
    private long derniereGeneration;
  


    public Tournesol(int x, int y) {
        super(100, 0, 'T', x, y, COUTS, new String[]{"/Images/sunflower1.png","/Images/sunflower2.png"});
         soleilImage = new JLabel(new ImageIcon(getClass().getResource("/Images/Soleil.jpg")));
         panel = new JPanel();
        soleilImage.setBounds(x + 60, y, 50, 50);
        soleilImage.setVisible(false);
        soleilImage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                soleilImage.setVisible(false);
                nbsoleil.setText(Soleil.getNbSoleil() + "");
            }
        });

        panel.add(soleilImage);
        derniereGeneration = System.currentTimeMillis();
       
    }


    @Override
    public void agir(boolean zombieLane, List<Zombies> zombiesAdjacents) {
        long tempsActuel = System.currentTimeMillis();
        if (tempsActuel - derniereGeneration >= DELAI_GENERATION) {
            // Soleil.generesSoleil();
            derniereGeneration = tempsActuel;
            soleilImage.setVisible(true);
    }
}

    
    

    

}

