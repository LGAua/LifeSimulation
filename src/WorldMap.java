package src;

import src.entities.Entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WorldMap {
    private static Map<Coordinates, Entity> world = new HashMap<>();
    private static int worldSizeX = 10;
    private static int worldSizeY = 10;


    public static void renderWorldMap() {
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

    public static Set<Coordinates> getOccupiedPositions() {
        return world.keySet();
    }

    public static void removeEntity(Coordinates coordinates) {
        world.remove(coordinates);
    }

    public static int getWorldSizeX() {
        return worldSizeX;
    }

    public static int getWorldSizeY() {
        return worldSizeY;
    }
}
