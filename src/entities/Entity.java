package src.entities;

import src.Coordinates;

public abstract class Entity {
    protected Coordinates position;
    protected String icon;

    public Coordinates getPosition() {
        return position;
    }

    public void setPosition(Coordinates position) {
        this.position = position;
    }

    public abstract String getIcon();
}
