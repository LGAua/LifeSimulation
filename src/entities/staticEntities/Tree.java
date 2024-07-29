package src.entities.staticEntities;

import src.Coordinates;
import src.entities.Entity;

public class Tree extends Entity {
    private String icon = "\uD83C\uDF35";

    public Tree(Coordinates coordinates){
        position = coordinates;
    }

    @Override
    public String getIcon() {
        return icon;
    }
}
