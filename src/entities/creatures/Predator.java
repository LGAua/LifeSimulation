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
    private String icon = "\uD83D\uDC7A";
    private Class<? extends Entity> victim = Herbivore.class;

    public Predator(Coordinates coordinates) {
        position = coordinates;
    }

    @Override
    public void makeMove() {
        if (this.getPosition().equals(targetCoordinates)) {
            targetCoordinates = null;
        }

        targetCoordinates = findVictimCoordinates(victim);

        if (targetCoordinates != null) {
            targetCoordinates = checkIfVictimChange();
            Coordinates moveToCoordinates = moveTowardsTarget(targetCoordinates);
            if (victim.isInstance(WorldMap.getWorld().get(moveToCoordinates))) {
                attackVictim(victim, moveToCoordinates);
                checkForEvolution();
            } else if (WorldMap.getWorld().get(moveToCoordinates) instanceof Herbivore && !isEvolved) {
                WorldMap.moveEntity(this,moveToAnotherCoordinates());
            } else {
                WorldMap.moveEntity(this, moveToCoordinates);
            }
        } else {
            WorldMap.moveEntity(this, moveToAnotherCoordinates());
        }
    }

    private Coordinates checkIfVictimChange(){
        Entity entity = WorldMap.getWorld().get(targetCoordinates);
        if (entity instanceof Herbivore
                && ((Herbivore) entity).isEvolved()
                && this.getPower() < ((Herbivore) entity).getPower()) {
            victim = Grass.class;
            return findVictimCoordinates(victim);
        }
        return targetCoordinates;
    }


    private void checkForEvolution() {
        if (this.getPower()>=3) {
            setEvolved(true);
            setIcon("\uD83D\uDE08");
            victim = Herbivore.class;
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

    private void setIcon(String icon) {
        this.icon = icon;
    }

    private void setEvolved(boolean evolved) {
        isEvolved = evolved;
    }

    @Override
    public String toString() {
        return "Predator";
    }
}
