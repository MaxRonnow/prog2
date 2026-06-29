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

    public void buildBridges(){
        List<Bridge> tempBridges = new ArrayList<>(bridges);

        for (Island island1 : islands) {
            for (Island island2 : islands) {
                if (island1 != island2 && !bridgeExists(tempBridges, island1, island2)) {
                        Bridge b = new Bridge(island1, island2);
                        tempBridges.add(b);
                }
            }
        }

        while (bridges.size() != islands.size() - 1) {
            Bridge currShortest = tempBridges.getFirst();

            for (Bridge b : tempBridges) {
                if (b.getLength() < currShortest.getLength()) {
                    currShortest = b;
                }
            }

            bridges.add(currShortest);

            if (checkForCycles()){
                bridges.remove(currShortest);
            }
            tempBridges.remove(currShortest);

        }
    }

    private boolean bridgeExists(List<Bridge> bridges, Island island1, Island island2) {
        for (Bridge temp : bridges) {
            Island a = temp.getIslands().get(0);
            Island b = temp.getIslands().get(1);
            if ((island1 == a && island2 == b) || (island1 == b && island2 == a)) {
                return true;
            }
        }
        return false;
    }


    private boolean checkForCycles(){
        Map<Island, Island> parent = new HashMap<>();
        for (Island island : islands) parent.put(island, island);

        for (Bridge bridge : bridges) {
            Island a = find(parent, bridge.getIslands().get(0));
            Island b = find(parent, bridge.getIslands().get(1));
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