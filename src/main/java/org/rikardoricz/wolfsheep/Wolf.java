package org.rikardoricz.wolfsheep;

public class Wolf extends Animal {
    private static int sheepEnegry;
    private final char symbol;

    // Constructor
    public Wolf(int posX, int posY, int energy, int sheepEnegry, double reproductionProb) {
        super(posX, posY, energy, reproductionProb);
        symbol = 'W';
        Wolf.sheepEnegry = sheepEnegry;
    }

    public static int getSheepEnergy() {
        return sheepEnegry;
    }

    @Override
    public void eat() {
        if (getEnergy() <= 100)
            setEnergy(getEnergy() + sheepEnegry);
    }

    // Return animal symbol
    @Override
    public char getSymbol(int age) {
        if (age < 5)
            return 'w';
        return symbol;
    }
}
