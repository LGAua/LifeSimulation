package src.entities.creatures;


import src.Coordinates;
import src.WorldMap;
import src.entities.staticEntities.Grass;

public class Herbivore extends Creature {
    private int power = 0;
    private String icon = "\uD83E\uDD8E";
    private boolean isEvolved = false;

    public Herbivore(Coordinates coordinates) {
        position = coordinates;
    }

    public boolean isEvolved() {
        return isEvolved;
    }

    @Override
    public void makeMove() {
        if (this.getPosition().equals(targetCoordinates)) {
            targetCoordinates = null;
        }

        if (targetCoordinates == null || !WorldMap.getWorld().containsKey(targetCoordinates)
                || !(WorldMap.getWorld().get(targetCoordinates) instanceof Grass)) {
            if (isEvolved) {
                targetCoordinates = findClosestTarget(getPositionsOfClass(Predator.class));
            } else {
                targetCoordinates = findClosestTarget(getPositionsOfClass(Grass.class));
            }
        }

        if (targetCoordinates != null) {
            Coordinates moveToCoordinates = moveTowardsTarget(targetCoordinates);
            if (isEvolved) {
                if ((WorldMap.getWorld().get(moveToCoordinates) instanceof Predator)) {
                    int predatorHealth = ((Predator) WorldMap.getWorld().get(moveToCoordinates)).getHealth();
                    ((Predator) WorldMap.getWorld().get(moveToCoordinates)).setHealth(--predatorHealth);
                    WorldMap.moveEntity(this, moveToCoordinates);
                }
                WorldMap.moveEntity(this, moveToCoordinates);
            } else if (!(WorldMap.getWorld().get(moveToCoordinates) instanceof Predator)) {
                if (WorldMap.getWorld().get(moveToCoordinates) instanceof Grass) {
                    power++;
                    WorldMap.moveEntity(this, moveToCoordinates);
                    checkForEvolution();
                }
                WorldMap.moveEntity(this, moveToCoordinates);
            } else if (WorldMap.getWorld().get(moveToCoordinates) instanceof Predator) {
                Coordinates moveToAnotherCoordinates = null;
                for (Coordinates coordinates : WorldMap.getAdjacentCells(this.getPosition())) {
                    if (WorldMap.cellIsEmpty(coordinates)) {
                        moveToAnotherCoordinates = coordinates;
                    }
                }
                if (moveToAnotherCoordinates != null) {
                    WorldMap.moveEntity(this, moveToAnotherCoordinates);
                }
            }
        }
    }

    private void checkForEvolution() {
        if (this.power == 3) {
            this.setEvolved(true);
            setIcon("\uD83D\uDC09");
        }
    }

    public void setEvolved(boolean evolved) {
        isEvolved = evolved;
    }

    public int getPower() {
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
