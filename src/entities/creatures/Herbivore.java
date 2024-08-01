package src.entities.creatures;


import src.Coordinates;
import src.WorldMap;
import src.entities.Entity;
import src.entities.staticEntities.Grass;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Herbivore extends Creature {
    private int power = 0;
    private String icon = "\uD83D\uDC31";

    public Herbivore(Coordinates coordinates){
        position = coordinates;
    }

    @Override
    public void makeMove() {
        if (this.getPosition().equals(targetCoordinates)) {
            targetCoordinates = null;
        }

        if (targetCoordinates == null || !WorldMap.getWorld().containsKey(targetCoordinates)
                || !(WorldMap.getWorld().get(targetCoordinates) instanceof Grass)) {
            targetCoordinates = findClosestTarget(getPositionsOfClass(Grass.class));
        }

        if (targetCoordinates != null) {
            Coordinates moveToCoordinates = moveTowardsTarget(targetCoordinates);
            if (!(WorldMap.getWorld().get(moveToCoordinates) instanceof Predator)) {
                if (WorldMap.getWorld().get(moveToCoordinates) instanceof Grass){
                    power++;
                    WorldMap.moveEntity(this, moveToCoordinates);
                }
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

    private void setIcon(String icon) {
        this.icon = icon;
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
