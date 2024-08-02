package src.Actions;

import src.Coordinates;
import src.WorldMap;
import src.entities.staticEntities.Rock;

public class SpawnRocks extends Action {
    @Override
    public void perform() {
        for (int i = 0; i < WorldMap.getWorldSizeX()/2; i++) {
            WorldMap.addEntity(new Rock(Coordinates.getRandomCoordinates()));
        }
    }
}
