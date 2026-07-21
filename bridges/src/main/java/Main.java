/**
 * The main entrypoint of the program
 * This class initializes all needed classes and defines important program parameters such as ISLAND_COUNT
 *
 * DO NOT MODIFY
 *  Except ISLAND_COUNT to test the program with varying amount of islands
 *  Values between 10-2000 are recommended, 200 is a good middle ground
 *
 * @author Max Rönnow
 */

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

    // build the bridges
    bridgeBuilder.buildBridges();
    t.travel();

    IO.println("Done!");
}