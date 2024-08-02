package src.Actions;

import src.Coordinates;
import src.PrioritizedNode;
import src.WorldMap;
import src.entities.Entity;
import src.entities.creatures.Creature;
import src.entities.creatures.Predator;
import src.entities.staticEntities.Grass;
import src.entities.staticEntities.Rock;
import src.entities.staticEntities.Tree;

import java.util.*;

public abstract class SpawnCreatures extends Action {

    protected static Map<Coordinates, Coordinates> findNotBlockedCells() {
        Coordinates startSearchFrom = findStartingCoordinates();
        Queue<Coordinates> frontier = new LinkedList<>();
        Map<Coordinates, Coordinates> cameFrom = new HashMap<>();

        frontier.add(startSearchFrom);
        while (!frontier.isEmpty()) {
            Coordinates currentPosition = frontier.poll();
            for (Coordinates coordinates : WorldMap.getAdjacentCells(currentPosition)) {
                if (!cameFrom.containsKey(coordinates)
                        && !(WorldMap.getWorld().get(coordinates) instanceof Entity)) {
                    frontier.add(coordinates);
                    cameFrom.put(coordinates, currentPosition);
                }
            }
        }
        return cameFrom;
    }

    private static Coordinates findStartingCoordinates(){
        Coordinates startSearchFrom = new Coordinates(WorldMap.getWorldSizeX() / 2, WorldMap.getWorldSizeX() / 2);
        if (WorldMap.getWorld().get(startSearchFrom) instanceof Entity){
            for (Coordinates coordinates : WorldMap.getAdjacentCells(startSearchFrom)){
                if (WorldMap.cellIsEmpty(coordinates)){
                    return coordinates;
                }
            }
        }
        return Coordinates.getRandomCoordinates();
    }

}
