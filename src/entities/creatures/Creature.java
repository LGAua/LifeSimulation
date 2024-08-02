package src.entities.creatures;

import src.Coordinates;
import src.PrioritizedNode;
import src.WorldMap;
import src.entities.Entity;
import src.entities.staticEntities.Grass;

import javax.crypto.spec.PSource;
import java.util.*;

public abstract class Creature extends Entity {
    protected int health = 1;
    protected int power = 0;
    protected int amountOfMoves = 2;
    protected boolean isEvolved = false;
    protected Class<? extends Entity> victim;
    protected Coordinates targetCoordinates = this.getTargetCoordinates();

    protected abstract void makeMove();

    protected boolean isEvolved() {
        return isEvolved;
    }

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
    protected Coordinates findVictimCoordinates(Class<? extends Entity> victimClass){
        if (targetCoordinates == null || !WorldMap.getWorld().containsKey(targetCoordinates)
                || !(victimClass.isInstance(WorldMap.getWorld().get(targetCoordinates)))){
            targetCoordinates = findClosestTarget(getPositionsOfClass(victimClass));
            return targetCoordinates;
        }
        return targetCoordinates;
    }

    protected void attackVictim(Class<? extends Entity> victim, Coordinates position){
        if (Creature.class.getName().equals(victim.getSuperclass().getName())){
            int victimHealth = ((Creature) WorldMap.getWorld().get(position)).getHealth();
            ((Creature) WorldMap.getWorld().get(position)).setHealth(--victimHealth);
            System.out.println(this + "just killed entity " +  WorldMap.getWorld().get(position).toString());
        }else{
            power++;
        }
        WorldMap.moveEntity(this, position);
    }

    protected Coordinates moveToAnotherCoordinates(){
        Coordinates moveToAnotherCoordinates = null;
        for (Coordinates coordinates : WorldMap.getAdjacentCells(this.getPosition())) {
            if (WorldMap.cellIsEmpty(coordinates)) {
                moveToAnotherCoordinates = coordinates;
            }
        }
        if (moveToAnotherCoordinates != null) {
            return moveToAnotherCoordinates;
        }
        return getPosition();
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

    public int getAmountOfMoves() {
        return amountOfMoves;
    }

    public Class<? extends Entity> getVictim() {
        return victim;
    }

    protected abstract Coordinates getTargetCoordinates();

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }
}
