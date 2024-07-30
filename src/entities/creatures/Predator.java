package src.entities.creatures;

import src.Coordinates;
import src.WorldMap;
import src.entities.Entity;
import src.entities.staticEntities.Rock;
import src.entities.staticEntities.Tree;

import java.util.*;


public class Predator extends Creature {
    private int power = 1;
    private String icon = "\uD83D\uDC7A";
    private static Queue<Coordinates> predatorsPositions = new LinkedList<>();
    private Coordinates targetCoordinates = new Coordinates(9,9);

    public Predator(Coordinates coordinates) {
        position = coordinates;
    }

    @Override
    public void makeMove() {
        if (this.getPosition().equals(targetCoordinates)) {
            targetCoordinates = null;
        }

        if (targetCoordinates == null || !WorldMap.getWorld().containsKey(targetCoordinates)
                || !(WorldMap.getWorld().get(targetCoordinates) instanceof Herbivore)) {
            targetCoordinates = Herbivore.getHerbivoresPositions().poll();
        }

        if (targetCoordinates!=null){
            Coordinates moveToCoordinates = moveTowardsTarget(targetCoordinates);
            WorldMap.moveEntity(this, moveToCoordinates);
        }
    }

    private Coordinates moveTowardsTarget(Coordinates target) {
        Map<Coordinates, Coordinates> visitedCells = new HashMap<>();
        Queue<Coordinates> adjacentCells = new LinkedList<>();
        adjacentCells.add(this.getPosition());
        visitedCells.put(this.getPosition(), null);

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
                            && !(WorldMap.getWorld().get(coordinates) instanceof Predator)) {
                        adjacentCells.add(coordinates);
                        visitedCells.put(coordinates, currentPosition);
                    }
                }
            }
        }

        Coordinates coordinatesToTarget = getRouteToTarget(visitedCells, targetCoordinates);
        return coordinatesToTarget;
    }

    private Coordinates getRouteToTarget(Map<Coordinates, Coordinates> mapToTarget, Coordinates targetCoordinates) {
        List<Coordinates> route = new ArrayList<>();
        Coordinates currentPosition = targetCoordinates;

        while (!this.getPosition().equals(currentPosition)) {
            route.add(currentPosition);
            currentPosition = mapToTarget.get(currentPosition);
        }

        return route.get(route.size()-1);
    }

    public static Queue<Coordinates> getPredatorsPositions() {
        for (Map.Entry<Coordinates, Entity> el : WorldMap.getWorld().entrySet()) {
            if (el.getValue() instanceof Predator && !predatorsPositions.contains(el.getKey())) {
                predatorsPositions.add(el.getKey());
            }
        }
        return new LinkedList<>(predatorsPositions);
    }

    @Override
    public String getIcon() {
        return icon;
    }
}
