package org.rikardoricz.wolfsheep;

public abstract class Animal {
    int posX;
    int posY;
    private int energy;
    private char symbol;
    private Board board;
    private static final double REPRODUCE_PROB = 0.5;

    // Constructor
    public Animal(int posX, int posY, int energy) {
        this.posX = posX;
        this.posY = posY;
        this.energy = energy;
    }

    // Getters and setters
    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    // Move animal in random direction one cell max
    public void move() {
        int[] deltaMove = {-1, 0, 1};
        int randIndexX = (int) (Math.random() * deltaMove.length);
        int randIndexY = (int) (Math.random() * deltaMove.length);

        int newX = posX + deltaMove[randIndexX];
        int newY = posY + deltaMove[randIndexY];
        // TODO: get board width and height to use this instead of fixed values (10x10 for now)
        if (newX >= 0 && newX < 10 && newY >= 0 && newY < 10) {
            setPosX(newX);
            setPosY(newY);
        }
    }
    public void die() {
        // TODO: write code to kill animals (I'm not a murderer)
    }

    // Abstract methods
    public abstract void reproduce();
    public abstract void eat();
    public abstract char getSymbol();

//    public void setReproduceProb(double reproduceProb) {
//        this.reproduceProb = reproduceProb;
//    }
}
