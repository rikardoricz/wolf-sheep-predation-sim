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
        if (getEnergy() <= MAX_ENERGY)
            setEnergy(getEnergy() + grassEnergy);
        else
            setEnergy(100);
    }

    // Return animal symbol
    @Override
    public char getSymbol(int age) {
        if (age < 5)
            return 's';
        return symbol;
    }
}
