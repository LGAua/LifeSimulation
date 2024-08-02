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
    public int numberOfRound=0;
    public boolean gameIsOver = false;

    public Simulation(int amountOfHerbivores, int amountOfPredators) {
        this.amountOfHerbivores = amountOfHerbivores;
        this.amountOfPredators = amountOfPredators;
        prepareForGame();
    }

    public static void main(String[] args) {
        Simulation simulation = new Simulation(3, 3);
        Scanner scanner = new Scanner(System.in);
        simulation.startSimulation();
        System.out.println("===================================================");
        System.out.println("       Welcome to the Life Simulation Game.");
        System.out.println("Where Creatures need to evolve for their surviving.");
        System.out.println();
        System.out.println("Would you like to play? (Y/N)");
        String answer = scanner.nextLine();
        if ("y".equals(answer.toLowerCase())) {
            WorldMap.renderWorldMap();
            System.out.println("Choose action (1/2)");
            while (true) {
                System.out.println("1 - Start next round");
                System.out.println("2 - Start infinite simulation");
                answer = scanner.nextLine();
                if ("1".equals(answer)) {
                    simulation.nextTurn();
                } else if ("2".equals(answer)) {
                    while (!simulation.gameIsOver) {
                        simulation.nextTurn();
                    }
                    System.out.println("Game is over");
                    break;
                } else {
                    break;
                }
            }
        }
        scanner.close();
    }

    public void nextTurn() {
        for (Action action : turnActions) {
            action.perform();
        }
    }

    public void startSimulation() {
        for (Action action : initActions) {
            action.perform();
        }
    }

    public void prepareForGame() {
        initActions.add(new SpawnRocks());
        initActions.add(new SpawnTrees());
        initActions.add(new SpawnGrass(amountOfHerbivores));
        initActions.add(new SpawnPredators(amountOfPredators));
        initActions.add(new SpawnHerbivores(amountOfHerbivores));

        turnActions.add(new MoveCreatures(creatures,this));
    }
}

