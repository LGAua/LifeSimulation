package src.Actions;

import src.Coordinates;
import src.Simulation;
import src.WorldMap;
import src.entities.creatures.Predator;

import java.util.Map;

public class SpawnPredators extends SpawnCreatures {
    public SpawnPredators(int amountOfPredators) {
        this.amountOfPredators = amountOfPredators;
    }

    @Override
    public void perform() {
        for (int i = 0; i < amountOfPredators; i++) {
            Map<Coordinates, Coordinates> notBlockedCells = findNotBlockedCells();
            Coordinates coordinatesOfSpawn = Coordinates.getRandomCoordinates();
            while (!notBlockedCells.containsKey(coordinatesOfSpawn)) {
                coordinatesOfSpawn = Coordinates.getRandomCoordinates();
            }
            WorldMap.addEntity(new Predator(coordinatesOfSpawn));
        }
    }
}

