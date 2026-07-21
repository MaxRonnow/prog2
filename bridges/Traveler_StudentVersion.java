/**
 * A class representing a Traveler traveling through the archipelago from the
 * top leftmost island to the bottom rightmost island
 *
 * @author Max Rönnow
 */

import java.util.ArrayList;
import java.util.List;

public class Traveler {
    BridgeBuilder bridgeBuilder;

    // keeps track of the travelers status
    List<Island> visited;
    Island currentLocation;
    List<Island> path;

    // start and target islands for the traveler
    Island start;
    Island target;

    public Traveler(Archipelago archipelago, BridgeBuilder bridgeBuilder) {
        this.visited = new ArrayList<>();
        this.bridgeBuilder = bridgeBuilder;
        this.start = archipelago.getIslands().getFirst();
        this.target = archipelago.getIslands().getLast();
        this.path = new ArrayList<>();
        currentLocation = start;
        visited.add(start);
        path.add(start);
    }


    /**
     * TODO: Implement this method
     *
     * This method should use the move() method to move the traveler from start to target
     */
    public void travel() {

    }


    /**
     * TODO: Implement this method
     *
     * Moves the traveler to the next island, in this priority:
     *  1. Nearest unvisited island
     *  2. Last visited island (previous item in the path list)
     *
     *  On move, update island partOfPath status, update visited list, add to path and set currentLocation
     *  Make sure the target island also gets added to visited!
     */
    public void move() {

    }


    /**
     * TODO: Implement this method
     *
     * Returns the closest unvisited island to currentLocation. If no unvisited islands found return null.
     * Use the bridgeMap from the bridgeBuilder class to get the closest islands.
     * Check the visited list for visited status.
     * @return closest unvisited island, if no unvisited islands, return null.
     */
    public Island getClosestUnvisitedIsland(){

    }


    /**
     * TODO: Implement this method
     *
     * Gets the distance between two islands, if one of the islands is null it returns Float.MAX_VALUE
     * @param island1 is the first island in the calculation
     * @param island2 is the second island in the calculation
     * @return returns the distance as float.
     */
    public float getDistance(Island island1, Island island2) {

    }
}