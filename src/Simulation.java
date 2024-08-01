package src;

import src.entities.Entity;
import src.entities.creatures.Creature;
import src.entities.creatures.Herbivore;
import src.entities.creatures.Predator;

import java.util.*;


public class Simulation {
    public static List<Creature> creatures = new LinkedList<>();

    public static void main(String[] args) throws InterruptedException {
        Actions.addEntitiesOnMap(1, 2);
        WorldMap.renderWorldMap();
        while (true) {
            for (Map.Entry<Coordinates, Entity> entry : WorldMap.getWorld().entrySet()) {
                if (entry.getValue() instanceof Creature) {
                    creatures.add((Creature) entry.getValue());
                }
            }

            for (Creature creature : creatures) {
                if (creature instanceof Predator) {
                    Predator predator = (Predator) creature;
                    if (predator.getHealth() != 0) {
                        for (int i = 0; i < predator.getAmountOfMoves(); i++) {
                            predator.makeMove();
                            WorldMap.renderWorldMap();
                            Thread.sleep(1000);
                        }
                    }
                } else if (creature instanceof Herbivore) {
                    Herbivore herbivore = (Herbivore) creature;
                    if (herbivore.getHealth() != 0) {
                        for (int i = 0; i < herbivore.getAmountOfMoves(); i++) {
                            herbivore.makeMove();
                            WorldMap.renderWorldMap();
                            Thread.sleep(1000);
                        }
                    }
                }
            }
            creatures.clear();

        }


//        Actions.addEntitiesOnMap(2, 5);
//        WorldMap.renderWorldMap();
//        List<Creature> creatures = new ArrayList<>();
//        for (Map.Entry<Coordinates, Entity> entity : WorldMap.getWorld().entrySet()) {
//            if (entity.getValue() instanceof Creature) {
//                creatures.add((Creature) entity.getValue());
//            }
//        }
//
//
//        while (true) {
//            for (Creature creature : creatures){
//                if (creature instanceof Predator){
//                    Predator predator = (Predator) creature;
//                    predator.makeMove();
//                } else if (creature instanceof Herbivore) {
//                    Herbivore herbivore = (Herbivore) creature;
//                    herbivore.makeMove();
//                }
//            }
//            System.out.println();
//            System.out.println();
//            WorldMap.renderWorldMap();
//            Thread.sleep(500);
//        }
    }
}
