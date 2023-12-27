import java.awt.Image;

import javax.swing.ImageIcon;

public class Zombies {

    private int vie, degat;
    private Image[] image;
    private long recharge, timer, changeImage;
    private double x, y;
    private double vitesse;
    private int indiceImage;

    // private boolean ralenti;
    // private long ralentiDebut;

    private boolean ralenti;
    private long ralentiDebut;
    private long tempsRalenti;

    public Zombies(int vie, int degat, String[] path, double x, double y, double vitesse) {
        this.vie = vie;
        this.degat = degat;
        this.x = x;
        this.y = y;
        this.recharge = System.currentTimeMillis();
        this.vitesse = vitesse;
        this.timer = System.nanoTime();
        // this.ralenti=false;
        // this.ralentiDebut = 0;
        this.image = new Image[path.length];
        for (int i = 0; i < path.length; i++) {
            image[i] = new ImageIcon(getClass().getResource(path[i])).getImage();
        }
        this.changeImage = System.currentTimeMillis();
    }

    public static Zombies generesZombieNormale(int x) {
        String[] images = { "/Images/zombie1.png", "/Images/zombie2.png" };
        return new Zombies(190, 30, images, x, 8.99, 1.23);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getVitesse() {
        return vitesse;
    }

    public void attaquer(Plantes plantes) {
        if (System.currentTimeMillis() - recharge > 1000) {
            plantes.subir(degat);
            recharge = System.currentTimeMillis();
        }
    }

    public boolean enVie() {
        return vie > 0;
    }

    public void subir(int degat) {
        this.vie -= degat;
    }

    public void avancePas(){
        timer = System.nanoTime();
    }

    // public void ralentirDeplacement() {
    //     if (!ralenti) {
    //         ralenti = true;
    //         ralentiDebut = System.currentTimeMillis();
    //         // Réduire la vitesse des zombies à une valeur plus lente
    //         vitesse = vitesse / 2;
    //         System.out.println("Zombie ralenti ! Nouvelle vitesse : " + vitesse);
    //     }
    // }

    // public void ralentirDeplacement() {
    //     if (!ralenti) {
    //         ralenti = true;
    //         ralentiDebut = System.currentTimeMillis();
    //         tempsRalenti = ralentiDebut + 5000; // Ralentissement pendant 5 secondes
    //         // Réduire la vitesse des zombies à une valeur plus lente
    //         vitesse = vitesse / 2;
    //         System.out.println("Zombie ralenti ! Nouvelle vitesse : " + vitesse);
    //     }
    // }

    // public void setDeplacementRalenti(boolean ralenti) {
    //     this.ralenti = ralenti;
    // }

    public void setTempsRalenti(long tempsRalenti) {
        this.tempsRalenti = tempsRalenti;
    }

    public void ralentir() {
    // Diviser la vitesse par 2
    vitesse /= 2;
}


    

    // public void avance() {
    //     timer = System.nanoTime() - timer;
    //     y = y - 1 * (vitesse * 1E-10 * timer);
    //     timer = System.nanoTime();
    // //   //  if (ralenti) {
    // //         long tempsEcoule = System.currentTimeMillis() - ralentiDebut;
    // //         if (tempsEcoule >= 5000) { // Si 5 secondes se sont écoulées
    // //             // Rétablir la vitesse normale des zombies
    // //             vitesse = vitesse * 2;
    // //             ralenti = false;
    // //         }
    // //   //  }
        
    // }

    public void avance() {
        timer = System.nanoTime() - timer;
        if (ralenti && System.currentTimeMillis() <= tempsRalenti) {
            y = y - 0.5 * (vitesse * 1E-10 * timer); // Déplacement ralenti
        } else {
            y = y - 1 * (vitesse * 1E-10 * timer); // Déplacement normal
        }
        timer = System.nanoTime();
    }

    public Image getImage() {
        if (indiceImage == image.length) {
            indiceImage = 0;
        }
        if (System.currentTimeMillis() - changeImage > 500) {
            changeImage = System.currentTimeMillis();
            return image[indiceImage++];
        } else {
            return image[indiceImage];
        }
    }

    public void kill() {
        this.vie = 0;
    }

    

    

    
}
