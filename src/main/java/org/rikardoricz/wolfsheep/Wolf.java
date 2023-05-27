package org.rikardoricz.wolfsheep;

public class Wolf extends Animal {
    private int sheepEnegry;
    private final char symbol;

    // Constructor
    public Wolf(int posX, int posY, int energy, int sheepEnegry, double reproductionProb) {
        super(posX, posY, energy, reproductionProb);
        symbol = 'W';
        this.sheepEnegry = sheepEnegry;
    }


    @Override
    public void eat() {
//        TODO: feed wolves
    }

    @Override
    public void reproduce() {
//        TODO: allow wolves to reproduce
    }

    @Override
    public void die() {
//        TODO: kill wolves
    }

    // Return animal symbol
    @Override
    public char getSymbol() {
        return symbol;
    }
}
