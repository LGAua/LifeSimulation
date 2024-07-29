package src;

import src.entities.creatures.Herbivore;
import src.entities.creatures.Predator;

public class Simulation {
    public static void main(String[] args) {
        Actions.addCreaturesOnMap(10,10);
        WorldMap.renderWorldMap();
    }
}
