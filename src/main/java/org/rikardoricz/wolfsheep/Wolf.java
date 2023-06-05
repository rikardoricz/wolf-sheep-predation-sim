package org.rikardoricz.wolfsheep;

/**
 * Wolf animal
 */
public class Wolf extends Animal {
    /**
     * The amount of energy the wolf gains from eating a sheep
     */
    private static int sheepEnegry;

    /**
     * Character that represents a wolf on a board
     */
    private final char SYMBOL;

    // Constructor

    /**
     * Construct a new wolf
     *
     * @param posX X starting position on a board
     * @param posY Y starting position on a board
     * @param energy Initial energy level
     * @param sheepEnegry The amount of energy the wolf gains from eating a sheep
     * @param reproductionProb Reproduction probability
     */
    public Wolf(int posX, int posY, int energy, int sheepEnegry, double reproductionProb) {
        super(posX, posY, energy, reproductionProb);
        SYMBOL = 'W';
        Wolf.sheepEnegry = sheepEnegry;
    }

    /**
     * Get amount of energy the wolf gains from eating a sheep
     * @return The amount of energy the wolf gains from eating a sheep
     */
    public static int getSheepEnergy() {
        return sheepEnegry;
    }

    /**
     * Replenish energy level with energy from eating sheep
     */
    @Override
    public void eat() {
        if (getEnergy() <= MAX_ENERGY)
            setEnergy(getEnergy() + sheepEnegry);
        else
            setEnergy(100);
    }

    /**
     * The wolf follows the sheep if the sheep is on the field next to it.
     * Otherwise, the wolf moves randomly by one step.
     * @see Animal
     *
     * @param width Width of the board
     * @param height Height of the board
     * @param board The board on which the ismulation takes place
     */
    @Override
    public void move(int width, int height, Board board) {
        if (getAge() > 15) {
            setEnergy(0);
            return ;
        }
        setAge(getAge() + 1);

        setEnergy(getEnergy() - MOVE_COST);
        for (Animal animal : board.getAnimals()) {
            if ((animal instanceof Sheep) && (Math.abs(animal.getPosX() - this.getPosX()) <= 1) && (Math.abs(animal.getPosY() - this.getPosY()) <= 1)) {
                if (animal.getPosX() == this.getPosX() && animal.getPosY() == this.getPosY()) {
                    return ;
                }
                setPosX(animal.getPosX());
                setPosY(animal.getPosY());
                return;
            }
        }
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
        }
    }

    /**
     * Get character representing the wolf on a board based on its age
     *
     * @param age Age of a wolf that determines the displayed char
     * @return Character representing the wolf on a board
     */
    @Override
    public char getSymbol(int age) {
        if (age < 5)
            return Character.toLowerCase(SYMBOL);
        return SYMBOL;
    }
}
