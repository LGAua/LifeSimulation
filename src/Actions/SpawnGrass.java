package src.Actions;

import src.Coordinates;
import src.Simulation;
import src.WorldMap;
import src.entities.staticEntities.Grass;

public class SpawnGrass extends Action {
    @Override
    public void perform() {
        for (int i = 0; i < Simulation.amountOfHerbivores+3; i++) {
            WorldMap.addEntity(new Grass(Coordinates.getRandomCoordinates()));
        }
    }
}
