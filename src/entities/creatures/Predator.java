package src.entities.creatures;


import src.Coordinates;
import src.WorldMap;
import src.entities.Entity;

import java.util.*;

public class Predator extends Creature {
    private int power = 1;
    private String icon = "\uD83D\uDC7A";
    private static Queue<Coordinates> predatorsPositions = new LinkedList<>();
    private Coordinates targetCoordinates;

    public Predator(Coordinates coordinates) {
        position = coordinates;
    }

    @Override
    protected void makeMove() {
        for (Coordinates herbivorePosition : Herbivore.getHerbivoresPositions()) {
            if (position.equals(herbivorePosition)) {
                WorldMap.removeEntity(herbivorePosition);
                return;
            }
        }
        Set<Coordinates> isVisited = new TreeSet<>();
        Queue<Coordinates> adjacentCells = new LinkedList<>();
        Coordinates startCoordinates = this.getPosition();
        if (targetCoordinates==null || !WorldMap.getWorld().containsKey(targetCoordinates)){
            targetCoordinates = Herbivore.getHerbivoresPositions().poll();
        }

        while (true){
            for (Coordinates coordinates : Herbivore.getHerbivoresPositions()){
                if (startCoordinates.equals(coordinates)){
                    break;
                }
            }
            for (int i = 0; i < movesX.length ; i++) {
                for (int j = 0; j < movesY.length; j++) {

                }
            }

        }



    }

    public static Queue<Coordinates> getPredatorsPositions() {
        for (Map.Entry<Coordinates, Entity> el : WorldMap.getWorld().entrySet()) {
            if (el.getValue() instanceof Predator) {
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
