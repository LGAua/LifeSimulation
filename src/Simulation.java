package src;

import src.Actions.*;
import src.entities.Entity;
import src.entities.creatures.Creature;
import src.entities.creatures.Herbivore;
import src.entities.creatures.Predator;

import java.sql.SQLOutput;
import java.util.*;


public class Simulation {
    public List<Creature> creatures = new ArrayList<>();
    public List<Action> initActions = new ArrayList<>();
    public List<Action> turnActions = new ArrayList<>();
    public int amountOfHerbivores;
    public int amountOfPredators;
    private boolean gameIsOver;

    public Simulation(int amountOfHerbivores, int amountOfPredators) {
        this.amountOfHerbivores = amountOfHerbivores;
        this.amountOfPredators = amountOfPredators;
        prepareForGame();
    }

    public static void main(String[] args) {
        Simulation simulation = new Simulation(3, 3);
        Scanner scanner = new Scanner(System.in);
        simulation.startSimulation();
        System.out.println("===========================================");
        System.out.println("Welcome to the Life Simulation Game.");
        System.out.println("Where Creatures need to evolve for their surviving");
        WorldMap.renderWorldMap();

        while (true){
            simulation.nextTurn();
        }
    }

    public void nextTurn() {
        for (Action action : turnActions){
            action.perform();
        }
    }

    public void startSimulation() {
        for (Action action : initActions){
            action.perform();
        }
    }

    public void pauseSimulation() {

    }

    public void prepareForGame() {
        initActions.add(new SpawnRocks());
        initActions.add(new SpawnTrees());
        initActions.add(new SpawnGrass(amountOfHerbivores));
        initActions.add(new SpawnPredators(amountOfPredators));
        initActions.add(new SpawnHerbivores(amountOfHerbivores));

        turnActions.add(new MoveCreatures(creatures));
    }
}

