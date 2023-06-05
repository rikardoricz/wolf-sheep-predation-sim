package org.rikardoricz.wolfsheep;

/**
 * An abstract class describing an animal
 */
public abstract class Animal {
    /**
     * Unique if of an animal
     */
    public int id = 0;

    /**
     * Animal counter used to give id for new animal
     */
    public static int count = 0;

    /**
     * X index of the animal's position on a board
     */
    private int posX;

    /**
     * Y index of the animal's position on a board
     */
    private int posY;

    /**
     * Variable energy level of an animal
     */
    private int energy;

    /**
     * Age of an animal that increments each simulation tick
     */
    private int age;

    /**
     * Probability of reproduction
     */
    private static double reproduceProb;

    /**
     * How much energy does an animal cost per movement
     */
    public static final int MOVE_COST = 2;

    /**
     * Maximum energy level animal can have
     */
    public static final int MAX_ENERGY = 100;

    /**
     * Construct a new animal
     *
     * @param posX X starting position on a board
     * @param posY Y starting position on a board
     * @param energy Initial energy level
     * @param reproduceProb Reproduction probability
     */
    public Animal(int posX, int posY, int energy, double reproduceProb) {
        id = count++;
        this.posX = posX;
        this.posY = posY;
        this.energy = energy;
        this.age = 0;
        Animal.reproduceProb = reproduceProb;
    }

    // Getters and setters

    /**
     * Get the animal ID
     * @return animal ID
     */
    public int getId() {
        return id;
    }

    /**
     * Get the X index of the animal's position on the board
     * @return X index of the animal's position
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Set a new index X of the animal's position on the board
     * @param posX New index X of the animal's position
     */
    public void setPosX(int posX) {
        this.posX = posX;
    }

    /**
     * Get the Y index of the animal's position on the board
     * @return Y index of the animal's position
     */
    public int getPosY() {
        return posY;
    }

    /**
     * Set a new index Y of the animal's position on the board
     * @param posY New index X of the animal's position
     */
    public void setPosY(int posY) {
        this.posY = posY;
    }

    /**
     * Get energy level
     * @return Energy level
     */
    public int getEnergy() {
        return energy;
    }

    /**
     * Set new energy level
     * @param energy New energy level to be set
     */
    public void setEnergy(int energy) {
        this.energy = energy;
    }

    /**
     * Get the age of the animal
     * @return Age of an animal
     */
    public int getAge() {
        return age;
    }

    /**
     * Set new age
     * @param age Age to be set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Get the probability of animal reproduction
     * @return Reproduction probability
     */
    public static double getReproduceProb() {
        return reproduceProb;
    }

    // Move animal in random direction one cell max

    /**
     * Move an animal randomly around the board by one step. Animal cannot step outside the board.
     * @param width Width of the board
     * @param height Height of the board
     * @param board The board on which the ismulation takes place
     */
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

        int newX = getPosX() + dx;
        int newY = getPosY() + dy;
        if (newX >= 0 && newX < width && newY >= 0 && newY < height) {
            setPosX(newX);
            setPosY(newY);
            setEnergy(getEnergy() - MOVE_COST); // each move cost some energy
        }
    }

    /**
     * Check if the animal is dead
     * @return Whether the animal is dead
     */
    public boolean isDead() {
        return energy <= 0;
    }

    /**
     * Check if the animal can reproduce based on reproduction probability
     * @return Whether the animal is able to reproduce
     */
    public static boolean canReproduce() {
        double probability = getReproduceProb();
        double randomDouble = Math.random();
        return randomDouble >= probability;
    }

    // Abstract methods

    /**
     * Perform an action (eating) specific to the given animal species
     */
    public abstract void eat();

    /**
     * Get character representing the animal on the board
     * @param age Age of an animal that determines the displayed char
     * @return Char representing the animal on the board
     */
    public abstract char getSymbol(int age);
}
