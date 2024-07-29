package src.entities.creatures;


import src.Coordinates;

public class Herbivore extends Creature {
    private int power = 0;
    private String icon = "\uD83D\uDC31";

    public Herbivore(Coordinates coordinates){
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
