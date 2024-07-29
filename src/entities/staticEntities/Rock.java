package src.entities.staticEntities;

import src.Coordinates;
import src.entities.Entity;

public class Rock extends Entity {
    private String icon = "\uD83D\uDDFB";

    public Rock(Coordinates coordinates){
        position = coordinates;
    }

    @Override
    public String getIcon() {
        return icon;
    }
}
