package src.Actions;

import src.Coordinates;
import src.WorldMap;
import src.entities.Entity;
import src.entities.creatures.Creature;
import src.entities.creatures.Herbivore;
import src.entities.creatures.Predator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MoveCreatures extends Action {
    protected List<Creature> creatures;

    public MoveCreatures(List<Creature> creatures) {
        this.creatures = creatures;
    }

    @Override
    public void perform() {
        for (Map.Entry<Coordinates, Entity> entry : WorldMap.getWorld().entrySet()) {
            if (entry.getValue() instanceof Creature) {
                creatures.add((Creature) entry.getValue());
            }
        }
        for (Creature creature : creatures) {
            if (creature instanceof Predator) {
                Predator predator = (Predator) creature;
                if (predator.getHealth() > 0) {
                    for (int i = 0; i < predator.getAmountOfMoves(); i++) {
                        Coordinates startingPosition = predator.getPosition();
                        predator.makeMove();
                        WorldMap.renderWorldMap();
                        System.out.println("Походил хищник из "+ startingPosition +" в позицию: " + predator.getPosition());
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            } else if (creature instanceof Herbivore) {
                Herbivore herbivore = (Herbivore) creature;
                if (herbivore.getHealth() != 0) {
                    for (int i = 0; i < herbivore.getAmountOfMoves(); i++) {
                        Coordinates startingPosition = herbivore.getPosition();
                        herbivore.makeMove();
                        WorldMap.renderWorldMap();
                        System.out.println("Походил травоядный из "+ startingPosition +" в позицию: " + herbivore.getPosition());
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }
        creatures.clear();

    }
}


