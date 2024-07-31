package src;

import src.entities.Entity;
import src.entities.creatures.Creature;
import src.entities.creatures.Herbivore;
import src.entities.creatures.Predator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Simulation {
    public static void main(String[] args) throws InterruptedException {
        Actions.addEntitiesOnMap(1,4);
        List<Predator> list = new ArrayList<>();
        WorldMap.renderWorldMap();
        while (true){
            Thread.sleep(300);
            for (Map.Entry<Coordinates,Entity> entry : WorldMap.getWorld().entrySet()){
                if (entry.getValue() instanceof Predator){
                    list.add((Predator)entry.getValue());
                }
            }
            for (Predator predator : list){
                predator.makeMove();
            }
            list.clear();
            WorldMap.renderWorldMap();
            System.out.println(WorldMap.getWorld());
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
