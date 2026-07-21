/**
 * This class is the bridge builder.
 * It looks at the archipelago and decides which bridges are to be built
 *
 * @author Max Rönnow
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BridgeBuilder {
    Archipelago archipelago;
    List<Bridge> bridgeCandidates;
    Map<Island, List<Island>> bridgeMap;

    /**
     * Constructor, initializes bridgeCandidates, initializes and puts all islands as the keys for the bridgeMap
     * @param archipelago the archipelago in which the bridges are to be built
     */
    public BridgeBuilder(Archipelago archipelago) {
        this.archipelago = archipelago;
        bridgeCandidates = new ArrayList<>();
        bridgeMap = new HashMap<>();

        for (Island island : archipelago.getIslands()) {
            bridgeMap.put(island, new ArrayList<>());
        }
    }

    // Getter
    public Map<Island, List<Island>> getBridgeMap() {
        return bridgeMap;
    }


    /**
     * TODO: Implement this method
     *
     * This method makes the method calls to build the bridges.
     * First it has to load the candidate bridges after which it can construct the bridges.
     */
    public void buildBridges() {

    }


    /**
     * TODO: Implement this method
     *
     * This method should make all possible unique bridges in the archipelago and add them to bridgeCandidates.
     * Remember to make sure all bridges are unique! (island1, island2) is the same bridge as (island2, island1)!
     */
    public void loadCandidates() {

    }


    /**
     * TODO: Implement this method
     *
     * This method should remove the shortest bridge from bridgeCandidates and return it.
     * @return the shortest bridge
     */
    public Bridge getShortestCandidate() {

    }


    /**
     * TODO: Implement part of this method
     *
     * This method creates a map of the bridges as they are built
     * The bridges are represented as a Map where the keys are islands and the values are a list of the islands it
     * is connected to, for example:
     *
     * i1 -> i3, i4
     * i2 -> i4
     * i3 -> i1, i4
     * i4 -> i1, i2, i3, i4
     *
     * In this case island 4 is connected to all other islands, island 2 is only connected to island 4 and so on.
     * Inside the while loop do the following steps to build the bridges:
     *
     *  1. Get the shortest candidate bridge
     *  2. Add it to the bridgeMap and the archipelago
     *  3. Check for cycles - if true, remove bridge from bridgeMap and archipelago, if false, do nothing
     *
     *  Nothing needs to be done outside the loop
     */
    public void bridgeConstructor() {
        while (getCount() != archipelago.getIslands().size() - 1) {

        }
    }


    /**
     * Adds a bridge to the bridgeMap
     * @param b the bridge to be added
     */
    private void addToMap(Bridge b) {
        bridgeMap.get(b.getIsland1()).add(b.getIsland2());
        bridgeMap.get(b.getIsland2()).add(b.getIsland1());
    }


    /**
     * Removes a bridge from the bridgeMap
     * @param b - the bridge to be removed
     */
    private void removeFromMap(Bridge b) {
        bridgeMap.get(b.getIsland1()).remove(b.getIsland2());
        bridgeMap.get(b.getIsland2()).remove(b.getIsland1());
    }


    /**
     * TODO: Implement this method
     *
     * get a count of how many bridges there is in the bridgeMap
     * @return the count of bridges. Because each bridge appears twice in the blueprint (the bridges goes both ways), once
     * for each of its bridges, the count has to be divided before returning.
     */
    public int getCount() {

    }


    /**
     * Checks for cycles among the bridges in the archipelago
     * @return true for cycle, false for no cycle
     */
    private boolean hasCycle() {
        Map<Island, Island> parent = new HashMap<>();
        for (Island island : bridgeMap.keySet()) {
            parent.put(island, island);
        }

        for (Bridge bridge : archipelago.getBridges()) {
            Island a = findRoot(parent, bridge.getIsland1());
            Island b = findRoot(parent, bridge.getIsland2());
            if (a == b) return true;
            parent.put(a, b);
        }
        return false;
    }


    /**
     * helper method for hasCycle. Finds the root of the set in the Union Find algorithm
     * @param parent
     * @param island
     * @return
     */
    private Island findRoot(Map<Island, Island> parent, Island island) {
        Island root = parent.get(island);
        if (root != island) {
            root = findRoot(parent, root);
            parent.put(island, root);
        }
        return root;
    }
}