package org.rikardoricz.wolfsheep;

public class Sheep extends Animal {
    private static int grassEnergy;
    private final char symbol;

    public Sheep(int posX, int posY, int energy, int grassEnergy, double reproductionProb) {
        super(posX, posY, energy, reproductionProb);
        symbol = 'S';
        Sheep.grassEnergy = grassEnergy;
    }
    public static int getGrassEnergy() {
        return grassEnergy;
    }


    @Override
    public void eat() {
        // TODO: make sheeps eat grass and replenish energy
//        if (board.getGrass(posX, posY) > 0) {
//            setEnergy(getEnergy() + grassEnergy);
//        }
    }

//    @Override
//    public Animal reproduce(int posX, int posY) {
//        // TODO: write code to allow animals reproduction (remember of reproduction probability)
//        return new Sheep(posX, posY, 80, grassEnergy, getReproduceProb());
//    }

    // Return animal symbol
    @Override
    public char getSymbol() {
        return symbol;
    }
}
