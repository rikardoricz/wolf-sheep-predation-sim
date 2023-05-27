package org.rikardoricz.wolfsheep;

public class Sheep extends Animal {
    private int grassEnergy;
    private final char symbol;

    public Sheep(int posX, int posY, int energy, int grassEnergy, double reproductionProb) {
        super(posX, posY, energy, reproductionProb);
        symbol = 'S';
        this.grassEnergy = grassEnergy;
    }


    @Override
    public void eat() {
        // TODO: make sheeps eat grass and replenish energy
    }

    @Override
    public void reproduce() {
        // TODO: write code to allow animals reproduction (remember of reproduction probability)
    }

    @Override
    public void die() {
        // TODO: old and tired sheeps have to die :(
    }

    // Return animal symbol
    @Override
    public char getSymbol() {
        return symbol;
    }
}
