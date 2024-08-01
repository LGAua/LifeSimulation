package src;

import src.entities.creatures.Herbivore;
import src.entities.creatures.Predator;
import src.entities.staticEntities.Grass;
import src.entities.staticEntities.Rock;
import src.entities.staticEntities.Tree;

public class Actions {
    public void startNewGame() {

    }


    public static void addEntitiesOnMap(int amountOfPredators, int amountOFHerbivores) {
        for (int i = 0; i < 1; i++) {
            WorldMap.addEntity(new Rock(new Coordinates(amountOfPredators, Coordinates.getRandomCoordinates().getY())));
        }

        for (int i = 0; i < 1; i++) {
            WorldMap.addEntity(new Tree(new Coordinates(Coordinates.getRandomCoordinates().getX(), amountOFHerbivores)));
        }

        for (int i = 0; i < 15; i++) {
            WorldMap.addEntity(new Grass(Coordinates.getRandomCoordinates()));
        }

        for (int i = 0; i < amountOfPredators; i++) {
            WorldMap.addEntity(new Predator(Coordinates.getRandomCoordinates()));
        }

        for (int i = 0; i < amountOFHerbivores; i++) {
            WorldMap.addEntity(new Herbivore(Coordinates.getRandomCoordinates()));
        }

    }
    //4 -predators, 1 - herbivore, 2 - rocks ,2 - trees, 3 - grass
}
