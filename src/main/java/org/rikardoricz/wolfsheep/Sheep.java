package org.rikardoricz.wolfsheep;

/**
 * Sheep animal
 */
public class Sheep extends Animal {
    /**
     * The amount of energy the sheep gains from eating a grass
     */
    private static int grassEnergy;

    /**
     * Character that represents a sheep on a board
     */
    private final char symbol;

    /**
     * Construct a new sheep
     *
     * @param posX X starting position on a board
     * @param posY Y starting position on a board
     * @param energy Initial energy level
     * @param grassEnergy The amount of energy the sheep gains from eating a grass
     * @param reproductionProb Reproduction probability
     */
    public Sheep(int posX, int posY, int energy, int grassEnergy, double reproductionProb) {
        super(posX, posY, energy, reproductionProb);
        symbol = 'S';
        Sheep.grassEnergy = grassEnergy;
    }

    /**
     * Get amount of energy the sheep gains from eating a grass
     * @return The amount of energy the sheep gains from eating a grass
     */
    public static int getGrassEnergy() {
        return grassEnergy;
    }


    /**
     * Replenish energy level with energy from eating sheep
     */
    @Override
    public void eat() {
        if (getEnergy() <= MAX_ENERGY)
            setEnergy(getEnergy() + grassEnergy);
        else
            setEnergy(100);
    }

    /**
     * Get character representing the sheep on a board based on its age
     *
     * @param age Age of a sheep that determines the displayed char
     * @return Character representing the sheep on a board
     */
    @Override
    public char getSymbol(int age) {
        if (age < 5)
            return 's';
        return symbol;
    }
}
