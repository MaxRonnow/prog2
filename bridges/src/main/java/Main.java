void main() {

    // amount of islands to generate
    final int ISLAND_COUNT = 1000;
    // how far along the x and y axes the islands should be spread, choose these so that all islands fit in the window
    //  defined in the Graphics class
    final int SPREAD_X = 1450;
    final int SPREAD_Y = 880;

    Random rand = new Random();
    List<Island> islands = new ArrayList<>();

    // creates island in the top left corner
    islands.add(new Island(5, 5, true));

    // creates random islands
    for (int i = 0; i < ISLAND_COUNT; i++) {
        islands.add( new Island(rand.nextFloat() * SPREAD_X + 5, rand.nextFloat() * SPREAD_Y + 5, false));
    }

    // creates island in the bottom right corner
    islands.add(new Island(SPREAD_X + 5, SPREAD_Y + 5, false));

    // initialize the classes
    Archipelago archipelago = new Archipelago(islands);
    BridgeBuilder bridgeBuilder = new BridgeBuilder(archipelago);
    Traveler t = new Traveler(archipelago, bridgeBuilder);
    Graphics g = new Graphics(archipelago, t);
    bridgeBuilder.setGraphics(g);

    // build the bridges
    bridgeBuilder.buildBridges();
    t.travel();

    // draw the bridges
    g.drawBridge();
    IO.println("Done!");
}