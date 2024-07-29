package src.entities.staticEntities;

import src.entities.Entity;

public class Rock extends Entity {
    private String icon = "R";

    @Override
    public String getIcon() {
        return icon;
    }
}
