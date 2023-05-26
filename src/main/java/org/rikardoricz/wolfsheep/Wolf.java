package org.rikardoricz.wolfsheep;

public class Wolf extends Animal {
    private static final int MAX_ENERGY = 100;
    private static final int SHEEP_ENERGY = 20;
    private final char symbol;

    // Constructor
    public Wolf(int posX, int posY, int energy) {
        super(posX, posY, energy);
        symbol = 'W';
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

    }

    // Return animal symbol
    @Override
    public char getSymbol() {
        return symbol;
    }
}
