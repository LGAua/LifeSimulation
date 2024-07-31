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
    protected int[] movesX = {0, -1, 1, 0};
    protected int[] movesY = {-1, 0, 0, 1};
    protected Coordinates targetCoordinates = this.getTargetCoordinates();


    protected abstract void makeMove();

    protected Coordinates moveTowardsTarget(Coordinates target) {
        Map<Coordinates, Coordinates> cameFrom = new HashMap<>();
        Queue<Coordinates> adjacentCells = new LinkedList<>();
        Class<? extends Creature> aClass = this.getClass();
        adjacentCells.add(this.getPosition());
        cameFrom.put(this.getPosition(), null);
        System.out.println("**************************");
        while (!adjacentCells.isEmpty()) {
            Coordinates currentPosition = adjacentCells.poll();
            if (currentPosition.equals(target)) {
                break;
            }
            for (Coordinates coordinates : WorldMap.getAdjacentCells(currentPosition)) {
                if (!cameFrom.containsKey(coordinates)
                        && !(WorldMap.getWorld().get(coordinates) instanceof Rock)
                        && !(WorldMap.getWorld().get(coordinates) instanceof Tree)
                        && !this.getClass().isInstance(WorldMap.getWorld().get(coordinates))) {
                    adjacentCells.add(coordinates);
                    cameFrom.put(coordinates, currentPosition);
//                    Thread.sleep(300);
//                    WorldMap.addEntity(new Predator(coordinates));
//                    WorldMap.renderWorldMap();
                }
            }
        }
        System.out.println(this.getPosition());
        return getNextStepTowardsTarget(cameFrom, this.getTargetCoordinates());
    }

    private Coordinates getNextStepTowardsTarget(Map<Coordinates, Coordinates> cameFrom, Coordinates target) {
        Coordinates current = target;
        Coordinates nextStep = this.getPosition();

        while (current != null && !current.equals(this.getPosition())) {
            nextStep = current;
            current = cameFrom.get(current);
        }
//        System.out.println("Позиция демона"+this.getPosition());
//        System.out.println("Идет в"+target);
//        System.out.println(cameFrom);
//        System.out.println(nextStep);
//        System.out.println(current);
        if(current==null){
            return this.getPosition();
        }
        return nextStep;
    }

//    protected Coordinates getRouteToTarget(Map<Coordinates, Coordinates> mapToTarget, Coordinates targetCoordinates) {
//        List<Coordinates> route = new ArrayList<>();
//        Coordinates currentPosition = targetCoordinates;
//
//        while (!this.getPosition().equals(currentPosition)) {
//            route.add(currentPosition);
//            currentPosition = mapToTarget.get(currentPosition);
//        }
//
//        return route.get(route.size() - 1);
//    }

    protected abstract Coordinates getTargetCoordinates();
}
