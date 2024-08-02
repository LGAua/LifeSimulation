package src.Actions;

import src.Coordinates;
import src.WorldMap;
import src.entities.creatures.Herbivore;
import src.entities.creatures.Predator;
import src.entities.staticEntities.Grass;
import src.entities.staticEntities.Rock;
import src.entities.staticEntities.Tree;

public abstract class Action {
    protected int amountOfHerbivores;
    protected int amountOfPredators;

    public abstract void perform();

}
