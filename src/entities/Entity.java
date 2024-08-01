package src.entities;

import src.Coordinates;
import src.WorldMap;
import src.entities.staticEntities.Grass;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public abstract class Entity {
    protected Coordinates position;
    protected String icon;

    protected static Queue<Coordinates> getPositionsOfClass(Class classOfEntity) {
        Queue<Coordinates> grassPositions = new LinkedList<>();
        for (Map.Entry<Coordinates,Entity> el : WorldMap.getWorld().entrySet()){
            if (classOfEntity.isInstance(el.getValue()) && !grassPositions.contains(el.getKey())){
                grassPositions.add(el.getKey());
            }
        }
        return grassPositions;
    }

    public Coordinates getPosition() {
        return position;
    }

    public void setPosition(Coordinates position) {
        this.position = position;
    }

    public abstract String getIcon();
}
