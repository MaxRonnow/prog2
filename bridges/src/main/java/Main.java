void main() {

    final int ISLAND_COUNT = 3000;
    final int SPREAD_X = 1400;
    final int SPREAD_Y = 850;

    Random rand = new Random();
    List<Island> islands = new ArrayList<>();

    for (int i = 0; i < ISLAND_COUNT; i++) {
        islands.add( new Island(rand.nextFloat() * SPREAD_X, rand.nextFloat() * SPREAD_Y));
    }

    Archipelago archipelago = new Archipelago(islands);
    Graphics g = new Graphics(archipelago);
    archipelago.buildBridges(g);
    g.drawBridge();
    IO.println("Done!");
    //archipelago.printBridges();

}
