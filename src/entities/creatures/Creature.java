package src.entities.creatures;

import src.Coordinates;
import src.WorldMap;
import src.entities.Entity;
import src.entities.staticEntities.Rock;
import src.entities.staticEntities.Tree;

import java.util.*;

public abstract class Creature extends Entity {
    protected int health = 5;
    protected int movement = 1;
    protected int[] movesX= {0,-1,1,0};
    protected int[] movesY= {-1,0,0,1};
    protected Coordinates targetCoordinates = this.getTargetCoordinates();



    protected abstract void makeMove();

    protected Coordinates moveTowardsTarget(Coordinates target) {
        Map<Coordinates, Coordinates> visitedCells = new HashMap<>();
        Queue<Coordinates> adjacentCells = new LinkedList<>();
        adjacentCells.add(this.getPosition());
        visitedCells.put(this.getPosition(), null);
        Class<? extends Creature> aClass = this.getClass();

        while (!adjacentCells.isEmpty()) {
            Coordinates currentPosition = adjacentCells.poll();
            if (currentPosition.equals(target)) {
                break;
            }
            for (int i = 0; i < movesX.length; i++) {
                Coordinates coordinates = new Coordinates(currentPosition.getX() + movesX[i], currentPosition.getY() + movesY[i]);
                if ((coordinates.getX() >= 0 && coordinates.getX() < WorldMap.getWorldSizeX())
                        && (coordinates.getY() >= 0 && coordinates.getY() < WorldMap.getWorldSizeY())
                        && !visitedCells.containsKey(coordinates)) {
                    if (!(WorldMap.getWorld().get(coordinates) instanceof Rock)
                            && !(WorldMap.getWorld().get(coordinates) instanceof Tree)
                            && !this.getClass().isInstance(WorldMap.getWorld().get(coordinates))) {
                        adjacentCells.add(coordinates);
                        visitedCells.put(coordinates, currentPosition);
                    }
                }
            }
        }

        Coordinates coordinatesToTarget = getRouteToTarget(visitedCells, this.getTargetCoordinates());
        return coordinatesToTarget;
    }

    protected Coordinates getRouteToTarget(Map<Coordinates, Coordinates> mapToTarget, Coordinates targetCoordinates) {
        List<Coordinates> route = new ArrayList<>();
        Coordinates currentPosition = targetCoordinates;

        while (!this.getPosition().equals(currentPosition)) {
            route.add(currentPosition);
            currentPosition = mapToTarget.get(currentPosition);
        }

        return route.get(route.size()-1);
    }

    protected abstract Coordinates getTargetCoordinates();
}
