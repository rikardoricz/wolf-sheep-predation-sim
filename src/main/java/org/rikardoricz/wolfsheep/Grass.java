package org.rikardoricz.wolfsheep;

/**
 * Grass that can be eaten by sheeps and regrow
 */
public class Grass {
    /**
     * X index of the grass' position on a board
     */
    private final int posX;

    /**
     * Y index of the grass' position on a board
     */
    private final int posY;

    /**
     * Age of a grass that increments each simulation tick. And resets if eaten.
     */
    private int age;

    /**
     * Amount of simulation ticks that takes a grass to (re)grow and be available to eat by sheeps
     */
    private final int grassRegrowthTicks;

    /**
     * Construct a new grass
     *
     * @param posX X index position on a board
     * @param posY Y index position on a board
     * @param age Amount of simulation ticks it takes the grass to grow from zero
     * @param grassRegrowthTicks Amount of simulation ticks that takes a grass to (re)grow and be available to eat by sheeps again
     */
    public Grass(int posX, int posY, int age, int grassRegrowthTicks) {
        this.posX = posX;
        this.posY = posY;
        this.age = age;
        this.grassRegrowthTicks = grassRegrowthTicks;
    }

    /**
     * Get the X index of the grass' position on the board
     * @return X index of the grass' position
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Get the Y index of the grass' position on the board
     * @return Y index of the grass' position
     */
    public int getPosY() {
        return posY;
    }

    /**
     * Get amount of simulation ticks it takes the grass to grow from zero
     *
     * @return Amount of ticks it takes the grass to grow from zero
     */
    public int getAge() {
        return age;
    }

    /**
     * Set new age for the grass
     *
     * @param age Age of the grass - amount of ticks it grows
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Increment age by one
     */
    public void regrow() {
        setAge(getAge() + 1);
    }

    /**
     * Get character representing the grass on the board
     * @param age Age of a grass that determines the displayed char
     * @return Char representing the grass on the board
     */
    public char getSymbol(int age) {
        if (age == 0)
            return '.';
        else if (age < grassRegrowthTicks/2)
            return '‥';
        else return '…';
    }
}
