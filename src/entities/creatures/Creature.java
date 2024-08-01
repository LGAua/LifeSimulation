package src.entities.creatures;

import src.Coordinates;
import src.PrioritizedNode;
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

    protected Coordinates findClosestTarget(Queue<Coordinates> targetPositions){
        Queue<PrioritizedNode> q = new PriorityQueue<>();
        Coordinates closestTarget = null;
        for (Coordinates targetPosition : targetPositions){
            q.add(new PrioritizedNode(targetPosition,WorldMap.heuristic(targetPosition,this.getPosition())));
        }
        if (!q.isEmpty()){
            closestTarget = q.poll().getCoordinates();
        }
        return closestTarget;
    }

    protected Coordinates moveTowardsTarget(Coordinates target) {
        Queue<PrioritizedNode> frontier = new PriorityQueue<>();
        Map<Coordinates, Coordinates> cameFrom = new HashMap<>();
        Map<Coordinates, Integer> costSoFar = new HashMap<>();
        Class<? extends Creature> aClass = this.getClass();

        frontier.add(new PrioritizedNode(this.getPosition(), 0));
        cameFrom.put(this.getPosition(), null);
        costSoFar.put(this.getPosition(), 0);

        System.out.println("**************************");

        while (!frontier.isEmpty()) {
            Coordinates currentPosition = frontier.poll().getCoordinates();
            if (currentPosition.equals(target)) {
                break;
            }
            for (Coordinates coordinates : WorldMap.getAdjacentCells(currentPosition)) {
                int newCost = costSoFar.get(currentPosition) + WorldMap.moveCost(currentPosition, coordinates);
                if ((!costSoFar.containsKey(coordinates) || costSoFar.get(currentPosition) > newCost)
                        && !this.getClass().isInstance(WorldMap.getWorld().get(coordinates))) {
                    costSoFar.put(coordinates,newCost);
                    int priority = newCost + WorldMap.heuristic(this.getTargetCoordinates(),coordinates);
                    frontier.add(new PrioritizedNode(coordinates,priority));
                    cameFrom.put(coordinates, currentPosition);
                }
            }
        }
        return getNextStepTowardsTarget(cameFrom, this.getTargetCoordinates());
    }

    private Coordinates getNextStepTowardsTarget(Map<Coordinates, Coordinates> cameFrom, Coordinates target) {
        Coordinates current = target;
        Coordinates nextStep = this.getPosition();
        while (current != null && !current.equals(this.getPosition())) {
            nextStep = current;
            current = cameFrom.get(current);
        }
        if (current == null) {
            return this.getPosition();
        }
        return nextStep;
    }

    protected abstract Coordinates getTargetCoordinates();
}
