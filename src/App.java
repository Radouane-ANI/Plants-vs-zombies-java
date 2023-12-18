public class App {
    public static void main(String[] args) throws Exception {
        Plateau p = new Plateau(5, 5);
        p.addPlante(new PlantesAttaquantes(50, 0, 'a', 0, 0));
        p.addZombie(new Zombies(10, 40, 'z', 0, 1.99, 1.25));
        p.addZombie(new Zombies(10, 40, 'z', 0, 3.99, 1.25));

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
