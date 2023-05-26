package org.rikardoricz.wolfsheep;

public abstract class Animal {
    public int id = 0;
    public static int count = 0;
    int posX;
    int posY;
    private int energy;
    private char symbol;
    private Board board;
    private static final int MOVE_COST = 5;
    private static final double REPRODUCE_PROB = 0.5;

    // Constructor
    public Animal(int posX, int posY, int energy) {
        id = count++;
        this.posX = posX;
        this.posY = posY;
        this.energy = energy;
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

    // Move animal in random direction one cell max
    public void move() {
        int[] deltaMove = {-1, 0, 1};
        int randIndexX = (int) (Math.random() * deltaMove.length);
        int randIndexY = (int) (Math.random() * deltaMove.length);
        int dx = deltaMove[randIndexX];
        int dy = deltaMove[randIndexY];

        int newX = posX + dx;
        int newY = posY + dy;
        // TODO: get board width and height to use this instead of fixed values (10x10 for now)
        if (newX >= 0 && newX < 10 && newY >= 0 && newY < 10) {
            setPosX(newX);
            setPosY(newY);
            setEnergy(getEnergy() - MOVE_COST); // each move cost some energy
        }
//        System.out.println(getId() + " " + getEnergy() + " " + getPosX() + " " + getPosY());
//        System.out.println(dx + " " + dy);
    }
    public boolean isDead() {
        return energy <= 0;
    }

    // Abstract methods
    public abstract void eat();
    public abstract void die();
    public abstract void reproduce();
    public abstract char getSymbol();
}
