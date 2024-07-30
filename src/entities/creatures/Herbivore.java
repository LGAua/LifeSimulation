package src.entities.creatures;


import src.Coordinates;
import src.WorldMap;
import src.entities.Entity;

import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Herbivore extends Creature {
    private static Queue<Coordinates> herbivoresPositions = new LinkedList<>();
    private int power = 0;
    private String icon = "\uD83D\uDC31";

    public Herbivore(Coordinates coordinates){
        position = coordinates;
    }

    public static Queue<Coordinates> getHerbivoresPositions() {
        for (Map.Entry<Coordinates,Entity> el : WorldMap.getWorld().entrySet()){
            if (el.getValue() instanceof Herbivore){
                herbivoresPositions.add(el.getKey());
            }
        }
        return new LinkedList<>(herbivoresPositions);
    }

    @Override
    protected void makeMove() {

    }

    @Override
    public String getIcon() {
        return icon;
    }

}
