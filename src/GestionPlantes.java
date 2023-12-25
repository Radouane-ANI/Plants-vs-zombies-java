import java.util.Scanner;

public class GestionPlantes {

    private Plateau plateau;
    private Soleil soleil;
    private Game game;

    public GestionPlantes(Plateau plateau, Soleil soleil) {
        this.plateau = plateau;
        this.soleil = soleil;
    }

    public void ajouterPlante(Plantes plantes) {
        if (plantes.getCouts() <= soleil.getNbSoleil()) {
            if (plateau.addPlante(plantes)) {
                soleil.soustraitSoleil(plantes.getCouts());
                System.out.println("Plante ajoutée ! Vies restantes : " + plantes.getVie());
            }

        }
    }

    public void placerPlante() {
        Scanner sc = new Scanner(System.in);
        if (soleil.getNbSoleil() >= 100) {
            System.out.println("x :");
            int x = sc.nextInt();
            if (x != -1) {
                System.out.println("y :");
                int y = sc.nextInt();
                if (x >= 0 && y >= 0 && x < plateau.getLargeur() && y < 9) {
                    String[] img = { "/Images/peashooter1.png", "/Images/peashooter2.png" };
                   // this.ajouterPlante(new PlantesAttaquantes(130, 20, 'a', x, y, 100, img));
                    // PlantesAttaquantes a =new PlantesAttaquantes(130, 20, 'a', x, y, 100, img);
                    // this.ajouterPlante(a);
                    // Game.setPlanteCourante(a);
                    PlantesAttaquantes b =new PlantesAttaquantes(130, 'M', x, y, 100, img);
                    this.ajouterPlante(b);
                    Game.setPlanteCourante(b);
                    //this.ajouterPlante(new PlantesAttaquantes(130, 'M', x, y, 100, img));

                }
            }
        }
    }
}
