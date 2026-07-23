import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * The main entrypoint of the program
 * This class initializes all needed classes and defines important program parameters such as ISLAND_COUNT
 *
 * DO NOT MODIFY
 *  Except ISLAND_COUNT to test the program with varying amount of randomly generated islands
 *  Values between 10-2000 are recommended, 200 is a good middle ground
 *
 * @author Max Rönnow
 */
public class Main {

    void main(String[] args) {


        // amount of islands to generate if no data file provided
        final int ISLAND_COUNT = 1000;
        // how far along the x and y axes the islands should be spread
        final int SPREAD_X = 100;
        final int SPREAD_Y = 100;

        var islands = new ArrayList<Island>(); // list for islands
        var rand = new Random();

        // input arg provided
        if (args.length == 1) {

            File inputFile = new File(args[0]);

            if (!inputFile.exists()) {
                throw new IllegalArgumentException("Input file does not exist");
            }

            try (Scanner fileReader = new Scanner(inputFile)) {
                String[] first = fileReader.nextLine().split(",");
                islands.add(new Island(Float.parseFloat(first[0]) * SPREAD_X + 5, Float.parseFloat(first[1]) * SPREAD_Y + 5, true));

                while (fileReader.hasNextLine()) {
                    String data = fileReader.nextLine();
                    String[] islandData = data.split(",");
                    islands.add(new Island(Float.parseFloat(islandData[0]) * SPREAD_X + 5, Float.parseFloat(islandData[1]) * SPREAD_Y + 5, false));
                }
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

        }
        else if (args.length > 1) { // too many args

            throw new IllegalArgumentException("Too many arguments");

        } else { // no args, generate islands randomly

            // creates island in the top left corner
            islands.add(new Island(5, 5, true));

            // creates random islands
            for (int i = 0; i < ISLAND_COUNT; i++) {
                islands.add(new Island(rand.nextFloat() * SPREAD_X + 5, rand.nextFloat() * SPREAD_Y + 5, false));
            }

            // creates island in the bottom right corner
            islands.add(new Island(SPREAD_X + 5, SPREAD_Y + 5, false));

        }


        // initialize the classes
        var archipelago = new Archipelago(islands);
        var bridgeBuilder = new BridgeBuilder(archipelago);
        var t = new Traveler(archipelago, bridgeBuilder);
        var g = new Graphics(archipelago, t);

        // build the bridges
        bridgeBuilder.buildBridges();
        t.travel();

        IO.println("Done!");
    }
}