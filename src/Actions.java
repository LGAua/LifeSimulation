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
        for (int i = 0; i < amountOfPredators; i++) {
            WorldMap.addEntity(new Predator(Coordinates.getRandomCoordinates()));
        }

        for (int i = 0; i < amountOFHerbivores; i++) {
            WorldMap.addEntity(new Herbivore(Coordinates.getRandomCoordinates()));
        }

        for (int i = 0; i < amountOfPredators * 2; i++) {
            WorldMap.addEntity(new Rock(Coordinates.getRandomCoordinates()));
        }

        for (int i = 0; i < amountOfPredators * 2; i++) {
            WorldMap.addEntity(new Tree(Coordinates.getRandomCoordinates()));
        }

        for (int i = 0; i < amountOFHerbivores * 2; i++) {
            WorldMap.addEntity(new Grass(Coordinates.getRandomCoordinates()));
        }

    }
    //4 -predators, 1 - herbivore, 2 - rocks ,2 - trees, 3 - grass
}
