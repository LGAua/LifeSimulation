package src;

import src.entities.Entity;
import src.entities.creatures.Creature;

import java.util.HashMap;
import java.util.Map;

public class WorldMap {
    private static Map<Coordinates, Entity> world = new HashMap<>();
    private static int worldSizeX = 100;
    private static int worldSizeY = 20;


    public static void renderWorldMap() {
        for (int y = 0; y < worldSizeY; y++) {
            for (int x = 0; x < worldSizeX; x++) {
                Coordinates coordinates = new Coordinates(x, y);
                if (world.containsKey(coordinates)) {
                    Creature creature = (Creature) world.get(coordinates);
                    System.out.print(creature.getIcon());
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }


    public static void addEntity(Entity entity) {
        world.put(entity.getPosition(), entity);
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
