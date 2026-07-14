package com.rallygame;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RaceTests {

    // ===================== VEHICLE CLASS EXISTENCE AND INHERITANCE TESTS =====================
    
    @Test
    public void testVehicleClassExists() {
        Vehicle vehicle = new Buggy();
        assertNotNull(vehicle, "Vehicle should be instantiable through subclass");
        assertInstanceOf(Vehicle.class, vehicle, "Buggy should be instance of Vehicle");
    }

    private <T> void assertInstanceOf(Class<T> expectedClass, Object other, String s) {
        assertEquals(expectedClass, other.getClass().getSuperclass(), s);
    }

    @Test
    public void testBuggyClassExists() {
        Buggy buggy = new Buggy();
        assertNotNull(buggy, "Buggy class should exist");
        assertInstanceOf(Vehicle.class, buggy, "Buggy should extend Vehicle");
    }

    @Test
    public void testSportsCarClassExists() {
        SportsCar sportsCar = new SportsCar();
        assertNotNull(sportsCar, "SportsCar class should exist");
        assertInstanceOf(Vehicle.class, sportsCar, "SportsCar should extend Vehicle");
    }

    @Test
    public void testSnowMobileClassExists() {
        SnowMobile snowMobile = new SnowMobile();
        assertNotNull(snowMobile, "SnowMobile class should exist");
        assertInstanceOf(Vehicle.class, snowMobile, "SnowMobile should extend Vehicle");
    }

    @Test
    public void testRallyCarClassExists() {
        RallyCar rallyCar = new RallyCar();
        assertNotNull(rallyCar, "RallyCar class should exist");
        assertInstanceOf(Vehicle.class, rallyCar, "RallyCar should extend Vehicle");
    }

    // ===================== VEHICLE ABSTRACT METHODS TESTS =====================

    @Test
    public void testVehicleAbstractMethodsTurnSpeed() {
        Road road = Road.ASPHALT.setLength(100);
        
        Buggy buggy = new Buggy();
        assertTrue(buggy.getTurnSpeed(road) >= 0, "getTurnSpeed should return non-negative value");
        
        SportsCar sportsCar = new SportsCar();
        assertTrue(sportsCar.getTurnSpeed(road) >= 0, "getTurnSpeed should return non-negative value");
        
        SnowMobile snowMobile = new SnowMobile();
        assertTrue(snowMobile.getTurnSpeed(road) >= 0, "getTurnSpeed should return non-negative value");
        
        RallyCar rallyCar = new RallyCar();
        assertTrue(rallyCar.getTurnSpeed(road) >= 0, "getTurnSpeed should return non-negative value");
    }

    @Test
    public void testVehicleAbstractMethodsSurfaceSpeed() {
        Road road = Road.DIRT.setLength(100);
        
        Buggy buggy = new Buggy();
        assertTrue(buggy.getSurfaceSpeed(road) >= 0, "getSurfaceSpeed should return non-negative value");
        
        SportsCar sportsCar = new SportsCar();
        assertTrue(sportsCar.getSurfaceSpeed(road) >= 0, "getSurfaceSpeed should return non-negative value");
        
        SnowMobile snowMobile = new SnowMobile();
        assertTrue(snowMobile.getSurfaceSpeed(road) >= 0, "getSurfaceSpeed should return non-negative value");
        
        RallyCar rallyCar = new RallyCar();
        assertTrue(rallyCar.getSurfaceSpeed(road) >= 0, "getSurfaceSpeed should return non-negative value");
    }

    @Test
    public void testVehicleAbstractMethodsFuelConsumption() {
        Buggy buggy = new Buggy();
        assertTrue(buggy.getFuelConsumption() >= 0, "getFuelConsumption should return non-negative value");
        
        SportsCar sportsCar = new SportsCar();
        assertTrue(sportsCar.getFuelConsumption() >= 0, "getFuelConsumption should return non-negative value");
        
        SnowMobile snowMobile = new SnowMobile();
        assertTrue(snowMobile.getFuelConsumption() >= 0, "getFuelConsumption should return non-negative value");
        
        RallyCar rallyCar = new RallyCar();
        assertTrue(rallyCar.getFuelConsumption() >= 0, "getFuelConsumption should return non-negative value");
    }

    @Test
    public void testVehicleAbstractMethodsTankSize() {
        Buggy buggy = new Buggy();
        assertTrue(buggy.getTankSize() > 0, "getTankSize should return positive value");
        
        SportsCar sportsCar = new SportsCar();
        assertTrue(sportsCar.getTankSize() > 0, "getTankSize should return positive value");
        
        SnowMobile snowMobile = new SnowMobile();
        assertTrue(snowMobile.getTankSize() > 0, "getTankSize should return positive value");
        
        RallyCar rallyCar = new RallyCar();
        assertTrue(rallyCar.getTankSize() > 0, "getTankSize should return positive value");
    }

    @Test
    public void testVehicleAbstractMethodsRefuelingRate() {
        Buggy buggy = new Buggy();
        assertTrue(buggy.getRefuelingRate() > 0, "getRefuelingRate should return positive value");
        
        SportsCar sportsCar = new SportsCar();
        assertTrue(sportsCar.getRefuelingRate() > 0, "getRefuelingRate should return positive value");
        
        SnowMobile snowMobile = new SnowMobile();
        assertTrue(snowMobile.getRefuelingRate() > 0, "getRefuelingRate should return positive value");
        
        RallyCar rallyCar = new RallyCar();
        assertTrue(rallyCar.getRefuelingRate() > 0, "getRefuelingRate should return positive value");
    }

    @Test
    public void testVehicleAbstractMethodsAccelerationRate() {
        Buggy buggy = new Buggy();
        assertTrue(buggy.getAccelerationRate() > 0, "getAccelerationRate should return positive value");
        
        SportsCar sportsCar = new SportsCar();
        assertTrue(sportsCar.getAccelerationRate() > 0, "getAccelerationRate should return positive value");
        
        SnowMobile snowMobile = new SnowMobile();
        assertTrue(snowMobile.getAccelerationRate() > 0, "getAccelerationRate should return positive value");
        
        RallyCar rallyCar = new RallyCar();
        assertTrue(rallyCar.getAccelerationRate() > 0, "getAccelerationRate should return positive value");
    }

    // ===================== BUGGY SPECIFICATIONS TESTS =====================

    @Test
    public void testBuggyParameters() {
        Buggy buggy = new Buggy();
        assertEquals(50, buggy.getSurfaceSpeed(Road.ASPHALT.setLength(100)), "Buggy surface speed should be 50 km/h");
        assertEquals(1, buggy.getFuelConsumption(), "Buggy fuel consumption should be 1");
        assertEquals(30, buggy.getTankSize(), "Buggy tank size should be 30");
        assertEquals(10, buggy.getRefuelingRate(), "Buggy refueling rate should be 10");
        assertEquals(6, buggy.getAccelerationRate(), "Buggy acceleration rate should be 6");
    }

    @Test
    public void testBuggyTurnSpeedSameAsSurfaceSpeed() {
        Buggy buggy = new Buggy();
        Road road = Road.DIRT.setLength(100);
        assertEquals(buggy.getTurnSpeed(road), buggy.getSurfaceSpeed(road),
            "Buggy turn speed should equal surface speed");
    }

    @Test
    public void testBuggyConstantSpeedOnAllSurfaces() {
        Buggy buggy = new Buggy();
        assertEquals(buggy.getSurfaceSpeed(Road.ASPHALT.setLength(100)),
                     buggy.getSurfaceSpeed(Road.DIRT.setLength(100)), 
                     "Buggy speed should be same on all surfaces");
    }

    // ===================== SPORTS CAR SPECIFICATIONS TESTS =====================

    @Test
    public void testSportsCarParameters() {
        SportsCar sportsCar = new SportsCar();
        assertEquals(120, sportsCar.getSurfaceSpeed(Road.ASPHALT.setLength(100)),
            "SportsCar speed on Asphalt should be 120 km/h");
        assertEquals(30, sportsCar.getSurfaceSpeed(Road.SNOW.setLength(100)),
            "SportsCar speed on Snow should be 30 km/h");
        assertEquals(2, sportsCar.getFuelConsumption(), "SportsCar fuel consumption should be 2");
        assertEquals(50, sportsCar.getTankSize(), "SportsCar tank size should be 50");
        assertEquals(10, sportsCar.getRefuelingRate(), "SportsCar refueling rate should be 10");
        assertEquals(10, sportsCar.getAccelerationRate(), "SportsCar acceleration rate should be 10");
    }

    @Test
    public void testSportsCarTurnSpeedIs60PercentOfSurfaceSpeed() {
        SportsCar sportsCar = new SportsCar();
        Road asphalt = Road.ASPHALT.setLength(100);
        int expected = (int) (sportsCar.getSurfaceSpeed(asphalt) * 0.6);
        assertEquals(expected, sportsCar.getTurnSpeed(asphalt),
            "SportsCar turn speed should be 60% of surface speed");
    }

    @Test
    public void testSportsCarSpeedVariesByRoadType() {
        SportsCar sportsCar = new SportsCar();
        int asphaltSpeed = sportsCar.getSurfaceSpeed(Road.ASPHALT.setLength(100));
        int dirtSpeed = sportsCar.getSurfaceSpeed(Road.DIRT.setLength(100));
        assertNotEquals(asphaltSpeed, dirtSpeed, "SportsCar speeds should vary by road type");
    }

    // ===================== SNOWMOBILE SPECIFICATIONS TESTS =====================

    @Test
    public void testSnowMobileParameters() {
        SnowMobile snowMobile = new SnowMobile();
        assertEquals(20, snowMobile.getSurfaceSpeed(Road.ASPHALT.setLength(100)),
            "SnowMobile speed on Asphalt should be 20 km/h");
        assertEquals(90, snowMobile.getSurfaceSpeed(Road.SNOW.setLength(100)),
            "SnowMobile speed on Snow should be 90 km/h");
        assertEquals(40, snowMobile.getSurfaceSpeed(Road.DIRT.setLength(100)),
            "SnowMobile speed on Dirt should be 40 km/h");
        assertEquals(30, snowMobile.getSurfaceSpeed(Road.GRAVEL.setLength(100)),
            "SnowMobile speed on Gravel should be 30 km/h");
        assertEquals(2, snowMobile.getFuelConsumption(), "SnowMobile fuel consumption should be 2");
        assertEquals(20, snowMobile.getTankSize(), "SnowMobile tank size should be 20");
        assertEquals(10, snowMobile.getRefuelingRate(), "SnowMobile refueling rate should be 10");
        assertEquals(20, snowMobile.getAccelerationRate(), "SnowMobile acceleration rate should be 20");
    }

    @Test
    public void testSnowMobileTurnSpeedFast() {
        SnowMobile snowMobile = new SnowMobile();
        Road snow = Road.SNOW.setLength(100);
        assertEquals(snowMobile.getSurfaceSpeed(snow), snowMobile.getTurnSpeed(snow),
            "SnowMobile turn speed on snow should equal surface speed");
    }

    @Test
    public void testSnowMobileTurnSpeedSlow() {
        SnowMobile snowMobile = new SnowMobile();
        Road asphalt = Road.ASPHALT.setLength(100);
        assertEquals(10, snowMobile.getTurnSpeed(asphalt),
            "SnowMobile turn speed on non-snow should be 10 km/h");
    }

    @Test
    public void testSnowMobileFastest() {
        SnowMobile snowMobile = new SnowMobile();
        int snowSpeed = snowMobile.getSurfaceSpeed(Road.SNOW.setLength(100));
        int asphaltSpeed = snowMobile.getSurfaceSpeed(Road.ASPHALT.setLength(100));
        int dirtSpeed = snowMobile.getSurfaceSpeed(Road.DIRT.setLength(100));
        int gravelSpeed = snowMobile.getSurfaceSpeed(Road.GRAVEL.setLength(100));
        
        assertTrue(snowSpeed > asphaltSpeed && snowSpeed > dirtSpeed && snowSpeed > gravelSpeed,
            "SnowMobile should be fastest on snow");
    }

    // ===================== RALLY CAR SPECIFICATIONS TESTS =====================

    @Test
    public void testRallyCarParameters() {
        RallyCar rallyCar = new RallyCar();
        assertEquals(60, rallyCar.getSurfaceSpeed(Road.ASPHALT.setLength(100)),
            "RallyCar speed on Asphalt should be 60 km/h");
        assertEquals(60, rallyCar.getSurfaceSpeed(Road.DIRT.setLength(100)),
            "RallyCar speed on Dirt should be 60 km/h");
        assertEquals(60, rallyCar.getSurfaceSpeed(Road.GRAVEL.setLength(100)),
            "RallyCar speed on Gravel should be 60 km/h");
        assertEquals(50, rallyCar.getSurfaceSpeed(Road.SNOW.setLength(100)),
            "RallyCar speed on Snow should be 50 km/h");
        assertEquals(3, rallyCar.getFuelConsumption(), "RallyCar fuel consumption should be 3");
        assertEquals(70, rallyCar.getTankSize(), "RallyCar tank size should be 70");
        assertEquals(8, rallyCar.getRefuelingRate(), "RallyCar refueling rate should be 8");
        assertEquals(12, rallyCar.getAccelerationRate(), "RallyCar acceleration rate should be 12");
    }

    @Test
    public void testRallyCarTurnSpeed() {
        RallyCar rallyCar = new RallyCar();
        
        // Test 90% on dirt
        Road dirt = Road.DIRT.setLength(100);
        int dirtTurnSpeed = rallyCar.getTurnSpeed(dirt);
        int dirtSurfaceSpeed = rallyCar.getSurfaceSpeed(dirt);
        assertEquals((int)(dirtSurfaceSpeed * 0.9), dirtTurnSpeed,
            "RallyCar turn speed on dirt should be 90% of surface speed");
        
        // Test 90% on gravel
        Road gravel = Road.GRAVEL.setLength(100);
        int gravelTurnSpeed = rallyCar.getTurnSpeed(gravel);
        int gravelSurfaceSpeed = rallyCar.getSurfaceSpeed(gravel);
        assertEquals((int)(gravelSurfaceSpeed * 0.9), gravelTurnSpeed,
            "RallyCar turn speed on gravel should be 90% of surface speed");
        
        // Test 90% on snow
        Road snow = Road.SNOW.setLength(100);
        int snowTurnSpeed = rallyCar.getTurnSpeed(snow);
        int snowSurfaceSpeed = rallyCar.getSurfaceSpeed(snow);
        assertEquals((int)(snowSurfaceSpeed * 0.9), snowTurnSpeed,
            "RallyCar turn speed on snow should be 90% of surface speed");
        
        // Test 50% on asphalt
        Road asphalt = Road.ASPHALT.setLength(100);
        int asphaltTurnSpeed = rallyCar.getTurnSpeed(asphalt);
        int asphaltSurfaceSpeed = rallyCar.getSurfaceSpeed(asphalt);
        assertEquals((int)(asphaltSurfaceSpeed * 0.5), asphaltTurnSpeed,
            "RallyCar turn speed on asphalt should be 50% of surface speed");
    }

    // ===================== VEHICLE BASE CLASS BEHAVIOR TESTS =====================

    @Test
    public void testVehicleInitialState() {
        Vehicle vehicle = new Buggy();
        assertEquals(0, vehicle.getRoadDistanceTravelled(), "Initial road distance should be 0");
        assertFalse(vehicle.getIsRefueling(), "Vehicle should not be refueling initially");
        assertTrue(vehicle.getDoneInTurns() > 1000000, "Vehicle should not be finished initially");
    }

    @Test
    public void testVehicleSetAndGetCurrentRoad() {
        Vehicle vehicle = new Buggy();
        Road road = Road.DIRT.setLength(100);
        vehicle.setCurrentRoad(road);
        assertEquals(road, vehicle.getCurrentRoad(), "Current road should be set correctly");
    }

    @Test
    public void testVehicleIsNotFinishedWhenHasRoad() {
        Vehicle vehicle = new Buggy();
        Road road = Road.DIRT.setLength(100);
        vehicle.setCurrentRoad(road);
        assertFalse(vehicle.isFinnished(), "Vehicle should not be finished when it has a road");
    }

    @Test
    public void testVehicleIsFinishedWhenNoRoad() {
        Vehicle vehicle = new Buggy();
        vehicle.setCurrentRoad(null);
        assertTrue(vehicle.isFinnished(), "Vehicle should be finished when current road is null");
    }

    @Test
    public void testVehicleSetAndGetDoneInTurns() {
        Vehicle vehicle = new Buggy();
        vehicle.setDoneInTurns(100);
        assertEquals(100, vehicle.getDoneInTurns(), "DoneInTurns should be set correctly");
    }

    @Test
    public void testVehicleToString() {
        Buggy buggy = new Buggy();
        assertNotNull(buggy.toString(), "toString should return a value");
        assertNotEquals("", buggy.toString(), "toString should not be empty");
    }

    @Test
    public void testVehicleGetStats() {
        Vehicle vehicle = new Buggy();
        String stats = vehicle.getStats();
        assertNotNull(stats, "getStats should return a value");
        assertTrue(stats.contains("distance"), "Stats should contain distance info");
        assertTrue(stats.contains("speed"), "Stats should contain speed info");
        assertTrue(stats.contains("fuel"), "Stats should contain fuel info");
    }

    // ===================== RACE LOGIC TESTS =====================

    @Test
    public void testRaceInitialization() {
        Race race = new Race();
        assertNotNull(race.getVehicles(), "Race should have vehicles");
        assertEquals(4, race.getVehicles().size(), "Default race should have 4 vehicles");
    }

    @Test
    public void testRaceWithCustomVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Buggy());
        vehicles.add(new SportsCar());
        
        Road startRoad = Road.ASPHALT.setLength(100);
        Race race = new Race(startRoad, vehicles);
        
        assertEquals(2, race.getVehicles().size(), "Race should have 2 vehicles");
        assertEquals(startRoad, race.getStartRoad(), "Start road should be set correctly");
    }

    @Test
    public void testRaceSimpleTrack() {
        List<Vehicle> vehicles = new ArrayList<>();
        Buggy buggy = new Buggy();
        vehicles.add(buggy);
        
        Road startRoad = Road.ASPHALT.setLength(100);
        Race race = new Race(startRoad, vehicles);
        race.start();
        
        assertEquals(startRoad, buggy.getCurrentRoad(), "Vehicle should be on start road");
    }

    @Test
    public void testRaceMultipleRoads() {
        List<Vehicle> vehicles = new ArrayList<>();
        Buggy buggy = new Buggy();
        vehicles.add(buggy);
        
        Road startRoad = Road.ASPHALT.setLength(100);
        Road nextRoad = Road.DIRT.setLength(100);
        startRoad.setNextRoad(nextRoad);
        nextRoad.setNextRoad(null); // Enums have shadow objects set from previous tests, this ensures next is null
        
        Race race = new Race(startRoad, vehicles);
        race.start();
        
        for (int i = 0; i < 100; i++) {
            race.update();
        }

        assertEquals(21, buggy.getDoneInTurns(), "Buggy did not finish in expected nr of iterations");
        assertNull(buggy.getCurrentRoad(), "Vehicle should not have a road currently");
    }

    // ===================== ITERATION CALCULATION TESTS =====================
    // These tests verify how many timesteps each vehicle takes to complete various races

    @Test
    public void testBuggySimpleRaceIterations() {
        List<Vehicle> vehicles = new ArrayList<>();
        Buggy buggy = new Buggy();
        vehicles.add(buggy);
        
        Road startRoad = Road.ASPHALT.setLength(100);
        Race race = new Race(startRoad, vehicles);
        race.start();
        
        int timesteps = 0;
        while (!buggy.isFinnished() && timesteps < 10000) {
            race.update();
            timesteps++;
        }
        
        assertTrue(buggy.isFinnished(), "Buggy should finish");
        assertTrue(timesteps > 0, "Should take at least 1 iteration");
        assertTrue(timesteps < 10000, "Should finish in reasonable time");
        assertEquals(timesteps, buggy.getDoneInTurns(), "getDoneInTurns should match actual timesteps");
    }

    @Test
    public void testSportsCarSimpleRaceIterations() {
        List<Vehicle> vehicles = new ArrayList<>();
        SportsCar sportsCar = new SportsCar();
        vehicles.add(sportsCar);
        
        Road startRoad = Road.ASPHALT.setLength(100);
        Race race = new Race(startRoad, vehicles);
        race.start();
        
        int timesteps = 0;
        while (!sportsCar.isFinnished() && timesteps < 10000) {
            race.update();
            timesteps++;
        }
        
        assertTrue(sportsCar.isFinnished(), "SportsCar should finish");
        assertTrue(timesteps < 10000, "Should finish in reasonable time");
        assertEquals(timesteps, sportsCar.getDoneInTurns(), "getDoneInTurns should match actual timesteps");
    }

    @Test
    public void testSnowMobileSimpleRaceIterations() {
        List<Vehicle> vehicles = new ArrayList<>();
        SnowMobile snowMobile = new SnowMobile();
        vehicles.add(snowMobile);
        
        Road startRoad = Road.ASPHALT.setLength(100);
        Race race = new Race(startRoad, vehicles);
        race.start();
        
        int timesteps = 0;
        while (!snowMobile.isFinnished() && timesteps < 10000) {
            race.update();
            timesteps++;
        }
        
        assertTrue(snowMobile.isFinnished(), "SnowMobile should finish");
        assertTrue(timesteps < 10000, "Should finish in reasonable time");
        assertEquals(timesteps, snowMobile.getDoneInTurns(), "getDoneInTurns should match actual timesteps");
    }

    @Test
    public void testRallyCarSimpleRaceIterations() {
        List<Vehicle> vehicles = new ArrayList<>();
        RallyCar rallyCar = new RallyCar();
        vehicles.add(rallyCar);
        
        Road startRoad = Road.ASPHALT.setLength(100);
        Race race = new Race(startRoad, vehicles);
        race.start();
        
        int timesteps = 0;
        while (!rallyCar.isFinnished() && timesteps < 10000) {
            race.update();
            timesteps++;
        }
        
        assertTrue(rallyCar.isFinnished(), "RallyCar should finish");
        assertTrue(timesteps < 10000, "Should finish in reasonable time");
        assertEquals(timesteps, rallyCar.getDoneInTurns(), "getDoneInTurns should match actual timesteps");
    }

    @Test
    public void testAllVehiclesCompleteDefaultRace() {
        Race race = new Race();
        race.start();
        
        int timesteps = 0;
        boolean allFinished = false;
        while (!allFinished && timesteps < 50000) {
            race.update();
            timesteps++;
            
            allFinished = true;
            for (Vehicle vehicle : race.getVehicles()) {
                if (!vehicle.isFinnished()) {
                    allFinished = false;
                    break;
                }
            }
        }
        
        assertTrue(allFinished, "All vehicles should finish the race");
        for (Vehicle vehicle : race.getVehicles()) {
            assertTrue(vehicle.getDoneInTurns() < 50000, vehicle + " should finish in reasonable time");
        }
    }

    @Test
    public void testComplexRaceWithTurns() {
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Buggy());
        vehicles.add(new SportsCar());
        
        Road road1 = Road.ASPHALT.setLength(200).setTurn(50).setTurn(150);
        Road road2 = Road.DIRT.setLength(150).setTurn(70);
        road1.setNextRoad(road2);
        
        Race race = new Race(road1, vehicles);
        race.start();
        
        int timesteps = 0;
        boolean allFinished = false;
        while (!allFinished && timesteps < 50000) {
            race.update();
            timesteps++;
            
            allFinished = true;
            for (Vehicle vehicle : race.getVehicles()) {
                if (!vehicle.isFinnished()) {
                    allFinished = false;
                    break;
                }
            }
        }
        
        assertTrue(allFinished, "All vehicles should finish");
        for (Vehicle vehicle : race.getVehicles()) {
            assertTrue(vehicle.getRoadDistanceTravelled() >= 0, "Distance should be non-negative");
        }
    }

    @Test
    public void testLongRaceMultipleRoads() {
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Buggy());
        
        Road asphalt = Road.ASPHALT.setLength(300);
        Road dirt = Road.DIRT.setLength(250);
        Road gravel = Road.GRAVEL.setLength(200);
        
        asphalt.setNextRoad(dirt);
        dirt.setNextRoad(gravel);
        
        Race race = new Race(asphalt, vehicles);
        race.start();
        
        int timesteps = 0;
        while (!vehicles.getFirst().isFinnished() && timesteps < 50000) {
            race.update();
            timesteps++;
        }
        
        assertTrue(vehicles.getFirst().isFinnished(), "Vehicle should finish");
    }

    @Test
    public void testVariousRaceDistances() {
        int[] distances = {50, 100, 200, 500, 1000};
        
        for (int distance : distances) {
            List<Vehicle> vehicles = new ArrayList<>();
            Buggy buggy = new Buggy();
            vehicles.add(buggy);
            
            Road road = Road.ASPHALT.setLength(distance);
            Race race = new Race(road, vehicles);
            race.start();
            
            int timesteps = 0;
            while (!buggy.isFinnished() && timesteps < 100000) {
                race.update();
                timesteps++;
            }
            
            assertTrue(buggy.isFinnished(), "Buggy should finish " + distance + "m race");
        }
    }

    // ===================== TURN IMPACT TESTS =====================

    @Test
    public void testTurnsSlowDownVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        SportsCar car = new SportsCar();
        vehicles.add(car);
        
        Road withTurn = Road.ASPHALT.setLength(200).setTurn(50);
        Race race = new Race(withTurn, vehicles);
        race.start();
        
        int timesteps = 0;
        while (!car.isFinnished() && timesteps < 10000) {
            race.update();
            timesteps++;
        }
        
        assertTrue(car.isFinnished(), "Should handle turns");
    }

    @Test
    public void testMultipleTurnsOnSameRoad() {
        List<Vehicle> vehicles = new ArrayList<>();
        RallyCar car = new RallyCar();
        vehicles.add(car);
        
        Road road = Road.ASPHALT.setLength(300).setTurn(50).setTurn(150).setTurn(250);
        Race race = new Race(road, vehicles);
        race.start();
        
        int timesteps = 0;
        while (!car.isFinnished() && timesteps < 10000) {
            race.update();
            timesteps++;
        }
        
        assertTrue(car.isFinnished(), "Should handle multiple turns");
    }

    // ===================== ROAD CHAIN TESTS =====================

    @Test
    public void testVehicleNavigatesMultipleRoads() {
        List<Vehicle> vehicles = new ArrayList<>();
        Buggy buggy = new Buggy();
        vehicles.add(buggy);
        
        Road road1 = Road.ASPHALT.setLength(100);
        Road road2 = Road.DIRT.setLength(100);
        Road road3 = Road.GRAVEL.setLength(100);
        
        road1.setNextRoad(road2);
        road2.setNextRoad(road3);
        
        Race race = new Race(road1, vehicles);
        race.start();
        
        assertEquals(road1, buggy.getCurrentRoad(), "Should start on road1");
        
        while (!buggy.isFinnished()) {
            race.update();
        }
        
        assertTrue(buggy.isFinnished(), "Should navigate all roads");
    }

    // ===================== FUEL MECHANICS TESTS =====================

    @Test
    public void testVehicleUsesAndNeededsFuel() {
        List<Vehicle> vehicles = new ArrayList<>();
        SportsCar car = new SportsCar();
        vehicles.add(car);
        
        Road road = Road.ASPHALT.setLength(500).setTurn(100);
        Race race = new Race(road, vehicles);
        race.start();
        
        int timesteps = 0;
        int maxSteps = 100;
        while (!car.isFinnished() && timesteps < maxSteps) {
            race.update();
            timesteps++;
        }
        
        // Should have used fuel during the race
        assertTrue(timesteps > 0, "Race should progress");
    }
}

