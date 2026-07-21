/**
 * This class represents an archipelago, i.e. a collection of islands.
 * It also keeps track of the bridges built
 *
 * DO NOT MODIFY THIS CLASS
 *
 * @author Max Rönnow
 */

import java.util.ArrayList;
import java.util.List;

public class Archipelago {

    /**
     * The Archipelago keeps track of which islands it consists of and which bridges have been built
     */
    private final List<Island> islands;
    private final List<Bridge> bridges;

    public Archipelago(List<Island> islands) {
        this.islands = islands;
        bridges = new ArrayList<>();
    }

    /**
     * Getters and Setters
     */
    public List<Island> getIslands() {
        return islands;
    }

    public List<Bridge> getBridges() {
        return bridges;
    }

    public void addBridge(Bridge bridge) {
        bridges.add(bridge);
    }

    public void removeBridge(Bridge bridge) {
        bridges.remove(bridge);
    }

    /**
     * Use this if you want to print all bridges built to the console
     */
    public void printBridges(){
        for (Bridge b : bridges) {
            IO.println(b.toString());
        }
    }
}