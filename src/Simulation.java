package src;

import src.entities.creatures.Herbivore;
import src.entities.creatures.Predator;

public class Simulation {
    public static void main(String[] args) {
        for (int i = 0; i <= 10; i++) {
            int randomPositionX = (int) (Math.random() * WorldMap.getWorldSizeX());
            int randomPositionY = (int) (Math.random() * WorldMap.getWorldSizeY());
            Coordinates randomCoordinates = new Coordinates(randomPositionX,randomPositionY);
            WorldMap.addEntity(new Predator(randomCoordinates));
        }

        for (int i = 0; i <= 10; i++) {
            int randomPositionX = (int) (Math.random() * WorldMap.getWorldSizeX());
            int randomPositionY = (int) (Math.random() * WorldMap.getWorldSizeY());
            Coordinates randomCoordinates = new Coordinates(randomPositionX,randomPositionY);
            WorldMap.addEntity(new Herbivore(randomCoordinates));
        }
        WorldMap.renderWorldMap();
    }
}
