package src.entities.creatures;

import src.Coordinates;
import src.PrioritizedNode;
import src.WorldMap;
import src.entities.Entity;
import src.entities.staticEntities.Rock;
import src.entities.staticEntities.Tree;

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
    public void makeMove() {
        if (this.getPosition().equals(targetCoordinates)) {
            targetCoordinates = null;
        }

        if (targetCoordinates == null || !WorldMap.getWorld().containsKey(targetCoordinates)
                || !(WorldMap.getWorld().get(targetCoordinates) instanceof Herbivore)) {
            Queue<PrioritizedNode> q = new PriorityQueue<>();
            for (Coordinates herbivorePosition : Herbivore.getHerbivoresPositions()){
                q.add(new PrioritizedNode(herbivorePosition,WorldMap.heuristic(this.getPosition(),herbivorePosition)));
            }
            if (!q.isEmpty()){
                targetCoordinates = q.poll().getCoordinates();
            }
        }

        if (targetCoordinates != null) {
            Coordinates moveToCoordinates = moveTowardsTarget(targetCoordinates);
            WorldMap.moveEntity(this, moveToCoordinates);
        }
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
    protected Coordinates getTargetCoordinates() {
        return targetCoordinates;
    }

    @Override
    public String getIcon() {
        return icon;
    }
}
