package src.entities.creatures;


import src.Coordinates;
import src.WorldMap;
import src.entities.Entity;
import src.entities.staticEntities.Grass;

public class Herbivore extends Creature {
    private String icon = "\uD83E\uDD8E";
    private Class<? extends Entity> victim = Grass.class;

    public Herbivore(Coordinates coordinates) {
        position = coordinates;
    }


    @Override
    public void makeMove() {
        if (this.getPosition().equals(targetCoordinates)) {
            targetCoordinates = null;
        }
        targetCoordinates = findVictimCoordinates(victim);

        if (targetCoordinates != null) {
            Coordinates moveToCoordinates = moveTowardsTarget(targetCoordinates);
            if (victim.isInstance(WorldMap.getWorld().get(moveToCoordinates))) {
                attackVictim(victim, moveToCoordinates);
                checkForEvolution();
            } else if (WorldMap.getWorld().get(moveToCoordinates) instanceof Predator && !isEvolved) {
                WorldMap.moveEntity(this,moveToAnotherCoordinates());
            }else{
                WorldMap.moveEntity(this,moveToCoordinates);
            }
        }else {
            WorldMap.moveEntity(this,moveToAnotherCoordinates());
        }
    }



    private void checkForEvolution() {
        if (this.power == 3) {
            this.setEvolved(true);
            victim = Predator.class;
            setIcon("\uD83D\uDC09");
        }
    }

    public boolean isEvolved() {
        return isEvolved;
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
