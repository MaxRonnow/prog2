void main() {

    final int ISLAND_COUNT = 5;
    Random rand = new Random();
    List<Island> islands = new ArrayList<>();

    for (int i = 0; i < ISLAND_COUNT; i++) {
        islands.add( new Island(rand.nextFloat() * 200, rand.nextFloat() * 200));
    }

    Archipelago archipelago = new Archipelago(islands);
    archipelago.buildBridges();
    archipelago.printBridges();

    Graphics g = new Graphics(archipelago);

}
