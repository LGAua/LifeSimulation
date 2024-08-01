package src.entities.creatures;

import src.Coordinates;
import src.PrioritizedNode;
import src.Simulation;
import src.WorldMap;
import src.entities.Entity;
import src.entities.staticEntities.Grass;
import src.entities.staticEntities.Rock;
import src.entities.staticEntities.Tree;

import java.util.*;


public class Predator extends Creature {
    private int power = 2;
    private String icon = "\uD83D\uDC7A";
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
            targetCoordinates = findClosestTarget(getPositionsOfClass(Herbivore.class));
            if (((Herbivore) WorldMap.getWorld().get(targetCoordinates)).getPower()>this.power){
                targetCoordinates = findClosestTarget(getPositionsOfClass(Grass.class));
            }
        }

        if (targetCoordinates != null) {
            Coordinates moveToCoordinates = moveTowardsTarget(targetCoordinates);
            if (WorldMap.getWorld().get(moveToCoordinates) instanceof Herbivore
                    && !((Herbivore) WorldMap.getWorld().get(moveToCoordinates)).isEvolved()) {
                int herbivoreHealth = ((Herbivore) WorldMap.getWorld().get(moveToCoordinates)).getHealth();
                ((Herbivore) WorldMap.getWorld().get(moveToCoordinates)).setHealth(--herbivoreHealth);
                WorldMap.moveEntity(this, moveToCoordinates);
            } else if (WorldMap.getWorld().get(moveToCoordinates) instanceof Herbivore
                    && ((Herbivore) WorldMap.getWorld().get(moveToCoordinates)).isEvolved()) {

                WorldMap.moveEntity(this, moveTowardsTarget(findClosestTarget(getPositionsOfClass(Grass.class))));
            } else {
                WorldMap.moveEntity(this, moveToCoordinates);
            }
        }
    }

    private int getPower() {
        return power;
    }

    private void setPower(int power) {
        this.power = power;
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
