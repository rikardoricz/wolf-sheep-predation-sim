package org.rikardoricz.wolfsheep;

public abstract class Animal {
    public int id = 0;
    public static int count = 0;
    int posX;
    int posY;
    private int energy;
    private int age;
    private char symbol;
    private Board board;
    private static double reproduceProb;
    public static final int MOVE_COST = 2;
    public static final int MAX_ENERGY = 100;

    // Constructor
    public Animal(int posX, int posY, int energy, double reproduceProb) {
        id = count++;
        this.posX = posX;
        this.posY = posY;
        this.energy = energy;
        this.age = 0;
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
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public static double getReproduceProb() {
        return reproduceProb;
    }

    // Move animal in random direction one cell max
    public void move(int width, int height, Board board) {
        if (getAge() > 35) {
            setEnergy(0);
            return ;
        }
        setAge(getAge() + 1);
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

    public static boolean canReproduce() {
        double probability = getReproduceProb();
//        System.out.println("Probability: " + probability);
        double randomDouble = Math.random();
        return randomDouble >= probability;
    }

    // Abstract methods
    public abstract void eat();
//    public abstract void die();
//    public abstract Animal reproduce(int posX, int posY);
    public abstract char getSymbol(int age);
}
