public class App {
    public static void main(String[] args) throws Exception {
        Plateau p = new Plateau(5, 5);
        p.addPlante(new PlantesAttaquantes(50, 20, 'a', 0, 0));
        p.addZombie(new Zombies(200, 40, 'z', 0, 4.99, 1.25));
        p.addZombie(new Zombies(200, 40, 'z', 0, 4.99, 1.25));

        System.out.println(p);
        while (true) {
            p.etatZombieLane();
            p.etatPlante();
            p.etatBalles();
            Thread.sleep(10);
            System.out.println(p);
        }

    }
}
