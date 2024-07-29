package src.entities.staticEntities;

import src.Coordinates;
import src.entities.Entity;

public class Grass extends Entity {
    private String icon = "\uD83C\uDF40";

    public Grass(Coordinates coordinates){
        position = coordinates;
    }

    @Override
    public String getIcon() {
        return icon;
    }
}
