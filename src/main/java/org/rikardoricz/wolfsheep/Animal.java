package org.rikardoricz.wolfsheep;

public abstract class Animal {
    public int id = 0;
    public static int count = 0;
    int posX;
    int posY;
    private int energy;
    private char symbol;
    private Board board;
    private static double reproduceProb;
    private static final int MOVE_COST = 5;
    private static final int MAX_ENERGY = 100;

    // Constructor
    public Animal(int posX, int posY, int energy, double reproduceProb) {
        id = count++;
        this.posX = posX;
        this.posY = posY;
        this.energy = energy;
        Animal.reproduceProb = reproduceProb;
    }

    // Getters and setters
    public int getId() {
        return id;
    }
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
    public static double getReproduceProb() {
        return reproduceProb;
    }

    // Move animal in random direction one cell max
    public void move(int width, int height) {
        int[] deltaMove = {-1, 0, 1};
        int randIndexX = (int) (Math.random() * deltaMove.length);
        int randIndexY = (int) (Math.random() * deltaMove.length);
        int dx = deltaMove[randIndexX];
        int dy = deltaMove[randIndexY];

        int newX = posX + dx;
        int newY = posY + dy;
        if (newX >= 0 && newX < width && newY >= 0 && newY < height) {
            setPosX(newX);
            setPosY(newY);
            setEnergy(getEnergy() - MOVE_COST); // each move cost some energy
        }
    }
    public boolean isDead() {
        return energy <= 0;
    }

    public boolean canReproduce() {
        double probability = getReproduceProb();
//        System.out.println("Probability: " + probability);
        double randomDouble = Math.random();
        return randomDouble >= probability;
    }

    // Abstract methods
    public abstract void eat();
//    public abstract void die();
//    public abstract Animal reproduce(int posX, int posY);
    public abstract char getSymbol();
}
