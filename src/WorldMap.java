package src;

import src.entities.Entity;

import java.util.HashMap;
import java.util.Map;

public class WorldMap {
    private static Map<Coordinates, Entity> world = new HashMap<>();
    private static int worldSizeX = 40;
    private static int worldSizeY = 10;

    public static void renderWorldMap(){
        for (int y = 0; y < worldSizeY; y++) {
            for (int x = 0; x < worldSizeX; x++) {
                Coordinates coordinates = new Coordinates(x,y);
                if(world.containsKey(coordinates)){
                    System.out.print(world.get(coordinates));
                }else{
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }


    public static void addEntity(Coordinates coordinates, Entity entity) {
        world.put(coordinates, entity);
    }

    public static void removeEntity(Coordinates coordinates) {
        world.remove(coordinates);
    }

}
