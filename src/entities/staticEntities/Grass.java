package src.entities.staticEntities;

import src.Coordinates;
import src.WorldMap;
import src.entities.Entity;
import src.entities.creatures.Predator;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Grass extends Entity {
    private String icon = "\uD83C\uDF40";
    private static Queue<Coordinates> grassPositions = new LinkedList<>();

    public Grass(Coordinates coordinates){
        position = coordinates;
    }

    public static Queue<Coordinates> getGrassPositions() {
        for (Map.Entry<Coordinates, Entity> el : WorldMap.getWorld().entrySet()) {
            if (el.getValue() instanceof Grass) {
                grassPositions.add(el.getKey());
            }
        }
        return new LinkedList<>(grassPositions);
    }

    @Override
    public String getIcon() {
        return icon;
    }
}
