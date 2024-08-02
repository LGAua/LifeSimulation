package src.Actions;

import src.Coordinates;
import src.Simulation;
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
    private Simulation simulation;
    private int herbivoresCount;
    private int predatorsCount;

    public MoveCreatures(List<Creature> creatures, Simulation simulation) {
        this.creatures = creatures;
        this.simulation = simulation;
    }

    @Override
    public void perform() {
        herbivoresCount=0;
        predatorsCount=0;
        for (Map.Entry<Coordinates, Entity> entry : WorldMap.getWorld().entrySet()) {
            if (entry.getValue() instanceof Creature) {
                creatures.add((Creature) entry.getValue());
            }
        }
        for (Creature creature : creatures) {
            if (creature instanceof Predator) {
                predatorsCount++;
                Predator predator = (Predator) creature;
                if (predator.getHealth() > 0) {
                    for (int i = 0; i < predator.getAmountOfMoves(); i++) {
                        Coordinates startingPosition = predator.getPosition();
                        predator.makeMove();
                        WorldMap.renderWorldMap();
                        System.out.println("Predator moves from " + startingPosition + " to " + predator.getPosition());
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            } else if (creature instanceof Herbivore) {
                herbivoresCount++;
                Herbivore herbivore = (Herbivore) creature;
                if (herbivore.getHealth() > 0) {
                    for (int i = 0; i < herbivore.getAmountOfMoves(); i++) {
                        Coordinates startingPosition = herbivore.getPosition();
                        herbivore.makeMove();
                        WorldMap.renderWorldMap();
                        System.out.println("Herbivore moves from " + startingPosition + " to " + herbivore.getPosition());
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }
        System.out.println();
        System.out.println("Round: "+ ++simulation.numberOfRound +" is over");
        System.out.println();

        if(herbivoresCount==0 || predatorsCount==0){
            simulation.gameIsOver = true;
        }

        creatures.clear();

    }
}


