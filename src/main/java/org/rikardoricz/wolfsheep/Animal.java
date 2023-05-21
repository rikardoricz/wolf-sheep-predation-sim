package org.rikardoricz.wolfsheep;

public abstract class Animal {
    int posX;
    int posY;
    private int energy;
    private char symbol;
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

    // Abstract methods
    public abstract void move();
    public abstract void eat();
    public abstract void reproduce();
    public abstract void die();
    public abstract char getSymbol();

//    public void setReproduceProb(double reproduceProb) {
//        this.reproduceProb = reproduceProb;
//    }
}
