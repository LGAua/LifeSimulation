package src;

import src.entities.Entity;
import src.entities.staticEntities.Rock;
import src.entities.staticEntities.Tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WorldMap {
    private static Map<Coordinates, Entity> world = new HashMap<>();
    private static final int worldSizeX = 5;
    private static final int worldSizeY = 5;

    public static void renderWorldMap() {
        System.out.println();
        for (int y = 0; y < worldSizeY; y++) {
            for (int x = 0; x < worldSizeX; x++) {
                Coordinates coordinates = new Coordinates(x, y);
                if (world.containsKey(coordinates)) {
                    System.out.print(world.get(coordinates).getIcon());
                } else {
                    System.out.print("⬛");
                }
            }
            System.out.println();
        }
    }

    public static void addEntity(Entity entity) {
        world.put(entity.getPosition(), entity);
    }

    public static void moveEntity(Entity entity, Coordinates coordinates) {
        world.remove(entity.getPosition());
        entity.setPosition(coordinates);
        world.put(coordinates, entity);
    }

    public static Set<Coordinates> getAdjacentCells(Coordinates currentPosition) {
        Set<Coordinates> set = new HashSet<>();
        int[] movesX = {0, -1, 1, 0};
        int[] movesY = {-1, 0, 0, 1};
        for (int i = 0; i < movesX.length; i++) {
            Coordinates coordinates = new Coordinates(currentPosition.getX() + movesX[i], currentPosition.getY() + movesY[i]);
            if ((coordinates.getX() >= 0 && coordinates.getX() < WorldMap.getWorldSizeX())
                    && (coordinates.getY() >= 0 && coordinates.getY() < WorldMap.getWorldSizeY())){
                set.add(coordinates);
            }
        }
        return set;
    }

    public static int heuristic (Coordinates currentPosition, Coordinates targetPosition){
        if(currentPosition==null || targetPosition==null){
            return 0;
        }
        return Math.abs(currentPosition.getX()-targetPosition.getX()) + Math.abs(currentPosition.getY()-targetPosition.getY());
    }

    public static int moveCost(Coordinates from,Coordinates to){
        if (getWorld().get(from) instanceof Rock || getWorld().get(from) instanceof Tree
        || getWorld().get(to) instanceof Rock || getWorld().get(to) instanceof Tree){
            return 100;
        }
        return 0;
    }

    public static Set<Coordinates> getOccupiedPositions() {
        return new HashSet<>(world.keySet());
    }

    public static void removeEntity(Coordinates coordinates) {
        world.remove(coordinates);
    }

    public static Map<Coordinates, Entity> getWorld() {
        return new HashMap<>(world);
    }

    public static boolean cellIsEmpty(Coordinates coordinates){
        return !world.containsKey(coordinates);
    }

    public static int getWorldSizeX() {
        return worldSizeX;
    }

    public static int getWorldSizeY() {
        return worldSizeY;
    }
}
