package src.entities.creatures;

import src.entities.Entity;

public abstract class Creature extends Entity {
    protected int health = 5;
    protected int movement = 1;

    protected abstract void makeMove();
}
