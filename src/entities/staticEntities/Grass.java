package src.entities.staticEntities;

import src.Coordinates;
import src.WorldMap;
import src.entities.Entity;
import src.entities.creatures.Herbivore;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

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
