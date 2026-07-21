import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

class BridgesTest {

    Archipelago archipelago;
    BridgeBuilder bridgeBuilder;
    Traveler traveler;

    static final int SIZE = 100;

    Random rand = new Random();

    public BridgesTest() {
        List<Island> islands = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            islands.add(new Island(rand.nextFloat(), rand.nextFloat(), false));
        }

        archipelago = new Archipelago(islands);
        bridgeBuilder = new BridgeBuilder(archipelago);
        traveler = new Traveler(archipelago, bridgeBuilder);
    }


    @Test
    void getCountTest() {
        Map<Island, List<Island>> map = new HashMap<>();

        for (Island island : archipelago.getIslands()) {
            map.put(island, new ArrayList<>());
        }

        for (int i = 0; i < SIZE / 3; i++) {
            int rand1 = rand.nextInt(SIZE);
            int rand2 = rand.nextInt(SIZE);

            map.get(archipelago.getIslands().get(rand1)).add(archipelago.getIslands().get(rand2));
            map.get(archipelago.getIslands().get(rand2)).add(archipelago.getIslands().get(rand1));
        }

        bridgeBuilder.bridgeMap = map;

        assertEquals(SIZE / 3, bridgeBuilder.getCount());
    }

    @Test
    void loadCandidatesTest() {
        // create 5 random islands
        List<Island> islands = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            islands.add(new Island(rand.nextFloat(), rand.nextFloat(), false));
        }

        Archipelago a = new Archipelago(islands);
        BridgeBuilder bb = new BridgeBuilder(a);

        // manually add the expected bridges
        List<Bridge> expected = new ArrayList<>();
        expected.add(new Bridge(islands.get(0), islands.get(1)));
        expected.add(new Bridge(islands.get(0), islands.get(2)));
        expected.add(new Bridge(islands.get(0), islands.get(3)));
        expected.add(new Bridge(islands.get(0), islands.get(4)));
        expected.add(new Bridge(islands.get(1), islands.get(2)));
        expected.add(new Bridge(islands.get(1), islands.get(3)));
        expected.add(new Bridge(islands.get(1), islands.get(4)));
        expected.add(new Bridge(islands.get(2), islands.get(3)));
        expected.add(new Bridge(islands.get(2), islands.get(4)));
        expected.add(new Bridge(islands.get(3), islands.get(4)));

        bb.loadCandidates();
        List<Bridge> actual = bb.bridgeCandidates;

        actual.sort((b1, b2) -> Float.compare(b1.getLength(), b2.getLength()));
        expected.sort((b1, b2) -> Float.compare(b1.getLength(), b2.getLength()));

        boolean equals = true;

        // check if the bridges are the same
        for (int i = 0; i < 5; i++) {
            if(!expected.get(i).equals(actual.get(i))) {
                equals = false;
            }
        }

        // check if arrays contain same amount of bridges
        if (expected.size() != actual.size()) {
            equals = false;
        }

        assertTrue(equals);
    }


    @Test
    void getShortestCandidateTest() {
        // create 4 random islands
        List<Island> islands = new ArrayList<>();
        float r = rand.nextFloat();

        for (int i = 1; i <= 4; i++) {
            islands.add(new Island(i * r, i * r, false));
        }

        // create one island close to the first one
        islands.add(new Island((float) (r * 0.99), (float) (r * 0.99), false));

        Archipelago a = new Archipelago(islands);
        BridgeBuilder bb = new BridgeBuilder(a);

        // manually add the expected bridges
        List<Bridge> candidates = new ArrayList<>();
        candidates.add(new Bridge(islands.get(0), islands.get(1)));
        candidates.add(new Bridge(islands.get(0), islands.get(2)));
        candidates.add(new Bridge(islands.get(0), islands.get(3)));
        candidates.add(new Bridge(islands.get(0), islands.get(4)));
        candidates.add(new Bridge(islands.get(1), islands.get(2)));
        candidates.add(new Bridge(islands.get(1), islands.get(3)));
        candidates.add(new Bridge(islands.get(1), islands.get(4)));
        candidates.add(new Bridge(islands.get(2), islands.get(3)));
        candidates.add(new Bridge(islands.get(2), islands.get(4)));
        candidates.add(new Bridge(islands.get(3), islands.get(4)));

        bb.bridgeCandidates = candidates;

        assertEquals(10, bb.bridgeCandidates.size());
        Bridge actual = bb.getShortestCandidate();
        Bridge expected = new Bridge(islands.getFirst(), islands.getLast());

        assertTrue(expected.equals(actual));
        assertEquals(9, bb.bridgeCandidates.size());
    }

    @Test
    void bridgeConstructorTest() {
        List<Island> islands = new ArrayList<>();
        List<Bridge> candidates = new ArrayList<>();

        islands.add(new Island(0, 0, false));
        islands.add(new Island(1, 1, false));
        islands.add(new Island(2, 2, false));
        islands.add(new Island(3, 3, false));
        islands.add(new Island(4, 4, false));

        candidates.add(new Bridge(islands.get(0), islands.get(1)));
        candidates.add(new Bridge(islands.get(0), islands.get(2)));
        candidates.add(new Bridge(islands.get(0), islands.get(3)));
        candidates.add(new Bridge(islands.get(0), islands.get(4)));
        candidates.add(new Bridge(islands.get(1), islands.get(2)));
        candidates.add(new Bridge(islands.get(1), islands.get(3)));
        candidates.add(new Bridge(islands.get(1), islands.get(4)));
        candidates.add(new Bridge(islands.get(2), islands.get(3)));
        candidates.add(new Bridge(islands.get(2), islands.get(4)));
        candidates.add(new Bridge(islands.get(3), islands.get(4)));

        List<Bridge> expectedBridges = new ArrayList<>();

        expectedBridges.add(new Bridge(islands.get(0), islands.get(1)));
        expectedBridges.add(new Bridge(islands.get(1), islands.get(2)));
        expectedBridges.add(new Bridge(islands.get(2), islands.get(3)));
        expectedBridges.add(new Bridge(islands.get(3), islands.get(4)));

        Map<Island, List<Island>> expectedMap = new HashMap<>();
        expectedMap.put(islands.get(0), new ArrayList<>());
        expectedMap.put(islands.get(1), new ArrayList<>());
        expectedMap.put(islands.get(2), new ArrayList<>());
        expectedMap.put(islands.get(3), new ArrayList<>());
        expectedMap.put(islands.get(4), new ArrayList<>());

        expectedMap.get(islands.get(0)).add(islands.get(1));
        expectedMap.get(islands.get(1)).add(islands.get(0));
        expectedMap.get(islands.get(1)).add(islands.get(2));
        expectedMap.get(islands.get(2)).add(islands.get(1));
        expectedMap.get(islands.get(2)).add(islands.get(3));
        expectedMap.get(islands.get(3)).add(islands.get(2));
        expectedMap.get(islands.get(3)).add(islands.get(4));
        expectedMap.get(islands.get(4)).add(islands.get(3));

        Archipelago a = new Archipelago(islands);
        BridgeBuilder bb = new BridgeBuilder(a);
        Traveler t = new Traveler(a, bb);
        bb.setGraphics(new Graphics(a, t));

        bb.bridgeCandidates = candidates;

        bb.bridgeConstructor();

        Map<Island, List<Island>> actualMap = bb.bridgeMap;
        List<Bridge> actualBridges = a.getBridges();

        assertTrue(expectedBridges.containsAll(actualBridges));
        assertEquals(expectedBridges.size(), actualBridges.size());

        assertEquals(expectedMap.size(), actualMap.size());

        for (Island key : expectedMap.keySet()) {
            assertTrue(actualMap.containsKey(key));
            List<Island> exp = expectedMap.get(key);
            List<Island> act = actualMap.get(key);
            assertEquals(exp.size(), act.size());
            assertTrue(exp.containsAll(act));
        }
    }


    @Test
    void buildBridgesTest() {
        List<Island> islands = new ArrayList<>();
        islands.add(new Island(0, 0, false));
        islands.add(new Island(1, 0, false));
        islands.add(new Island(2, 0, false));
        islands.add(new Island(3, 0, false));

        Archipelago a = new Archipelago(islands);
        BridgeBuilder bb = new BridgeBuilder(a);
        Traveler t = new Traveler(a, bb);
        bb.setGraphics(new Graphics(a, t));

        List<Bridge> expectedBridges = new ArrayList<>();
        expectedBridges.add(new Bridge(islands.get(0), islands.get(1)));
        expectedBridges.add(new Bridge(islands.get(1), islands.get(2)));
        expectedBridges.add(new Bridge(islands.get(2), islands.get(3)));

        Map<Island, List<Island>> expectedMap = new HashMap<>();
        expectedMap.put(islands.get(0), new ArrayList<>(List.of(islands.get(1))));
        expectedMap.put(islands.get(1), new ArrayList<>(List.of(islands.get(0), islands.get(2))));
        expectedMap.put(islands.get(2), new ArrayList<>(List.of(islands.get(1), islands.get(3))));
        expectedMap.put(islands.get(3), new ArrayList<>(List.of(islands.get(2))));

        bb.buildBridges();

        assertEquals(3, bb.getCount());
        assertEquals(expectedBridges, a.getBridges());
        assertEquals(expectedMap, bb.bridgeMap);
        assertEquals(3, bb.bridgeCandidates.size());
    }


    @Test
    void travelTest() {
        List<Island> islands = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            islands.add(new Island(i, 0, false));
        }

        islands.get(3).x = 2;
        islands.get(3).y = 0.5f;

        Archipelago a = new Archipelago(islands);
        BridgeBuilder bb = new BridgeBuilder(a);
        Traveler t = new Traveler(a, bb);

        bb.bridgeMap.get(islands.get(0)).add(islands.get(1));
        bb.bridgeMap.get(islands.get(1)).add(islands.get(0));

        bb.bridgeMap.get(islands.get(1)).add(islands.get(3));
        bb.bridgeMap.get(islands.get(3)).add(islands.get(1));

        bb.bridgeMap.get(islands.get(1)).add(islands.get(2));
        bb.bridgeMap.get(islands.get(2)).add(islands.get(1));
        bb.bridgeMap.get(islands.get(2)).add(islands.get(4));
        bb.bridgeMap.get(islands.get(4)).add(islands.get(2));

        t.travel();

        // traveler should end up at the target (last island) and have traversed n-1 moves
        assertEquals(t.target, t.currentLocation);
        assertEquals(5, t.visited.size());
        assertEquals(4, t.path.size());
    }

    @Test
    void moveTest(){
        // setup a small chain: 0-1-2
        List<Island> islands = new ArrayList<>();
        islands.add(new Island(0, 0, false));
        islands.add(new Island(1, 0, true));
        islands.add(new Island(2, 0, true));

        Archipelago a = new Archipelago(islands);
        BridgeBuilder bb = new BridgeBuilder(a);
        Traveler t = new Traveler(a, bb);

        // connect islands
        bb.bridgeMap.get(islands.get(0)).add(islands.get(1));
        bb.bridgeMap.get(islands.get(1)).add(islands.get(0));
        bb.bridgeMap.get(islands.get(1)).add(islands.get(2));
        bb.bridgeMap.get(islands.get(2)).add(islands.get(1));

        // simulate that traveler has path [0,1,2] and is currently at island 2, with 1 and 2 visited
        ArrayList<Island> path = new ArrayList<>(List.of(islands.get(0), islands.get(1), islands.get(2)));
        t.path = path;
        t.visited = new ArrayList<>(List.of(islands.get(1), islands.get(2)));
        t.currentLocation = islands.get(2);

        //this should backtrack to island 1
        t.move();

        assertEquals(islands.get(1), t.currentLocation);
        // the island we left should no longer be part of the path
        assertFalse(islands.get(2).isPartOfPath());
    }

    @Test
    void getClosestUnvisitedIslandTest() {
        List<Island> islands = new ArrayList<>();
        islands.add(new Island(0, 0, false));
        islands.add(new Island(10, 0, false));
        islands.add(new Island(5, 0, false));
        islands.add(new Island(1, 0, true));

        Archipelago a = new Archipelago(islands);
        BridgeBuilder bb = new BridgeBuilder(a);
        Traveler t = new Traveler(a, bb);

        // current location is island 0 and it has two neighbors: island1 (far) and island2 (near)
        bb.bridgeMap.get(islands.get(0)).add(islands.get(1));
        bb.bridgeMap.get(islands.get(0)).add(islands.get(2));
        bb.bridgeMap.get(islands.get(0)).add(islands.get(3));

        t.currentLocation = islands.get(0);
        t.visited.add(islands.get(3));

        Island closest = t.getClosestUnvisitedIsland();

        assertEquals(islands.get(2), closest);
    }

    @Test
    void getDistanceTest(){
        Island i1 = new Island(0, 0, false);
        Island i2 = new Island(3, 4, false);

        Archipelago a = new Archipelago(new ArrayList<>(List.of(i1, i2)));
        BridgeBuilder bb = new BridgeBuilder(a);
        Traveler t = new Traveler(a, bb);

        float dist = t.getDistance(i1, i2);

        assertEquals(5.0f, dist, 1e-6f);
        // null handling
        float inf = t.getDistance(null, i2);
        assertEquals(Float.MAX_VALUE, inf);
    }
}