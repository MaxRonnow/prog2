import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Archipelago {
    private List<Island> islands;
    private List<Bridge> bridges;

    public Archipelago(List<Island> islands) {
        this.islands = islands;
        bridges = new ArrayList<>();
    }

    public List<Island> getIslands() {
        return islands;
    }

    public List<Bridge> getBridges() {
        return bridges;
    }

    // Build the bridges using Kruskals algorithm
    public void buildBridges(Graphics g) {
        // Create a temporary array with all candidate bridges
        List<Bridge> tempBridges = new ArrayList<>();
        int n = islands.size();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                tempBridges.add(new Bridge(islands.get(i), islands.get(j)));
            }
        }
        // Sort the temporary array according to the length of the bridges
        tempBridges.sort((b1, b2) -> Double.compare(b1.getLength(), b2.getLength()));

        // Kurskals algorithm
        while (bridges.size() != islands.size() - 1 && !tempBridges.isEmpty()) {
            // Take the shortest remaining candidate
            Bridge currShortest = tempBridges.removeFirst();

            // Add the bridge and check for cycles, if cycle is found remove bridge
            bridges.add(currShortest);
            if (!checkForCycles()){
                // drawBridge expects x1, y1, x2, y2
                g.drawBridge();
            }
            else {
                bridges.remove(currShortest);
            }
        }
    }

    // Check for cycles in the graph
    private boolean checkForCycles(){
        Map<Island, Island> parent = new HashMap<>();
        for (Island island : islands){
            parent.put(island, island);
        }

        for (Bridge bridge : bridges) {
            Island a = find(parent, bridge.getIsland1());
            Island b = find(parent, bridge.getIsland2());
            if (a == b) return true;
            parent.put(a, b);
        }
        return false;
    }

    private Island find(Map<Island, Island> parent, Island island) {
        Island root = parent.get(island);
        if (root != island) {
            root = find(parent, root);
            parent.put(island, root);
        }
        return root;
    }

    public void printBridges(){
        for (Bridge b : bridges) {
            IO.println(b.toString());
        }
    }
}