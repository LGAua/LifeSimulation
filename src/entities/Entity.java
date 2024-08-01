package src.entities;

import src.Coordinates;
import src.WorldMap;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public abstract class Entity {
    protected Coordinates position;
    protected String icon;

    protected static Queue<Coordinates> getPositionsOfClass(Class<? extends Entity> classOfEntity) {
        Queue<Coordinates> positions = new LinkedList<>();
        for (Map.Entry<Coordinates,Entity> el : WorldMap.getWorld().entrySet()){
            if (classOfEntity.isInstance(el.getValue()) && !positions.contains(el.getKey())){
                positions.add(el.getKey());
            }
        }
        return positions;
    }

    public Coordinates getPosition() {
        return position;
    }

    public void setPosition(Coordinates position) {
        this.position = position;
    }

    public abstract String getIcon();
}
