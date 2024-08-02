package src.Actions;

import src.Coordinates;
import src.Simulation;
import src.WorldMap;
import src.entities.Entity;
import src.entities.staticEntities.Grass;

public class SpawnGrass extends Action {
    public SpawnGrass(int amountOfHerbivores) {
        this.amountOfHerbivores = amountOfHerbivores;
    }

    @Override
    public void perform() {
        for (int i = 0; i < amountOfHerbivores * 6; i++) {
            WorldMap.addEntity(new Grass(Coordinates.getRandomCoordinates()));
        }
    }
}
