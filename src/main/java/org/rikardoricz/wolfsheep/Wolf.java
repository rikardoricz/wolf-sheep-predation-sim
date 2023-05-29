package org.rikardoricz.wolfsheep;

public class Wolf extends Animal {
    private static int sheepEnegry;
    private final char symbol;

    // Constructor
    public Wolf(int posX, int posY, int energy, int sheepEnegry, double reproductionProb) {
        super(posX, posY, energy, reproductionProb);
        symbol = 'W';
        Wolf.sheepEnegry = sheepEnegry;
    }

    public static int getSheepEnergy() {
        return sheepEnegry;
    }

    @Override
    public void eat() {
        if (getEnergy() <= MAX_ENERGY)
            setEnergy(getEnergy() + sheepEnegry);
        else
            setEnergy(100);
    }

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
//                System.out.println("SHEEP DETECTED TARGET LOCKED");
//                System.out.println(getId() + " chase " + animal.getId());

//                new java.util.Scanner(System.in).nextLine();
                if (animal.getPosX() == this.getPosX() && animal.getPosY() == this.getPosY()) {
                    return ;
                }
                setPosX(animal.getPosX());
                setPosY(animal.getPosY());
//                break;
                return;
            }
        }
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
//                setEnergy(getEnergy() - MOVE_COST); // each move cost some energy
        }
    }
    // Return animal symbol
    @Override
    public char getSymbol(int age) {
        if (age < 5)
            return 'w';
        return symbol;
    }
}
