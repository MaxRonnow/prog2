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


    public void buildBridges(){
        List<Bridge> tempBridges = new ArrayList<>(bridges);

        for (Island island1 : islands) {
            for (Island island2 : islands) {
                if (island1 != island2) {
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


    private boolean checkForCycles(){
        Map<Island, List<Island>> connections = new HashMap<>();
        boolean cycleFound = true;
        for (Island island : islands) {
            connections.put(island, new ArrayList<>());
        }
        for (Bridge b : bridges) {
            connections.get(b.getIslands().get(0)).add(b.getIslands().get(1));
            connections.get(b.getIslands().get(1)).add(b.getIslands().get(0));
        }

        for (Island island : islands) {
            if (connections.get(island).size() < 2){
                cycleFound = false;
            }
        }

        return cycleFound;
    }


    public void printBridges(){
        for (Bridge b : bridges) {
            IO.println(b.toString());
        }
    }





}