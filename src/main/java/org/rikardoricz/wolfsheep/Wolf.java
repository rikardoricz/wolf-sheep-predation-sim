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
//        TODO: feed wolves
    }

//    @Override
//    public Animal reproduce(int posX, int posY) {
////        TODO: allow wolves to reproduce
//        return new Wolf(posX, posY, 80, sheepEnegry, getReproduceProb());
//    }

//    @Override
//    public void die() {
////        TODO: kill wolves
//    }

    // Return animal symbol
    @Override
    public char getSymbol() {
        return symbol;
    }
}
