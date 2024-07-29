package src.entities.creatures;


import src.Coordinates;

public class Predator extends Creature {
    private int power = 1;
    private String icon = "P";

    public Predator(Coordinates coordinates){
        position = coordinates;
    }

    @Override
    protected void makeMove() {

    }

    @Override
    public String getIcon() {
        return icon;
    }
}
