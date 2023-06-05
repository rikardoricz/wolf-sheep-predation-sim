package org.rikardoricz.wolfsheep;

import java.util.List;
import java.util.ArrayList;

/**
 * Board on which the simulation is happening. It has all the object present in the simulation - grass, wolves and sheeps
 */
public class Board {
    /**
     * Counter of newborn sheeps in each simulation tick
     */
    private static int newbornSheepCount = 0;

    /**
     * Counter of newborn wolves in each simulation tick
     */
    private static int newbornWolfCount = 0;

    /**
     * Counter of dead sheeps in each simulation tick
     */
    private static int deadSheepCount = 0;

    /**
     * Counter of dead wolves in each simulation tick
     */
    private static int deadWolfCount = 0;

    /**
     * Height of the board
     */
    private final int WIDTH;

    /**
     * Width of the board
     */
    private final int HEIGHT;

    /**
     * List of animal objects (both sheeps and wolves) that are present on a board
     */
    private List<Animal> animals;

    /**
     * List of grass objects that are present on a board
     */
    private List<Grass> grasses;

    // Constructor

    /**
     * Construct a new board
     *
     * @param width Width of the board
     * @param height Height of the board
     */
    public Board(int width, int height) {
        this.WIDTH = width;
        this.HEIGHT = height;
        animals = new ArrayList<>();
        grasses = new ArrayList<>();
    }

    // Getters

    /**
     * Get width of the board
     * @return Width of the board
     */
    public int getWidth() {
        return WIDTH;
    }

    /**
     * Get height of the board
     *
     * @return Height of the board
     */
    public int getHeight() {
        return HEIGHT;
    }

    /**
     * Get list of animals
     * @return List of animals
     */
    public List<Animal> getAnimals() {
        return animals;
    }

    /**
     * Get list of wolves
     * @return List of wolves
     */
    public List<Wolf> getWolves() {
        List<Wolf> wolves = new ArrayList<>();
        for (Animal animal : animals) {
            if (animal instanceof Wolf)
                wolves.add((Wolf) animal);
        }
        return wolves;
    }

    /**
     * Get list of sheeps
     *
     * @return List of sheeps
     */
    public List<Sheep> getSheeps() {
        List<Sheep> sheeps = new ArrayList<>();
        for (Animal animal : animals) {
            if (animal instanceof Sheep)
                sheeps.add((Sheep) animal);
        }
        return sheeps;
    }

    /**
     * Get number of newborn sheeps
     * @return Number of newborn sheeps
     */
    public int getNewbornSheepCount() {
        return newbornSheepCount;
    }

    /**
     * Get number of newborn wolves
     * @return Number of newborn wolves
     */
    public int getNewbornWolfCount() {
        return newbornWolfCount;
    }

    /**
     * Get number of dead sheeps
     * @return Number of dead sheeps
     */
    public int getDeadSheepCount() {
        return deadSheepCount;
    }

    /**
     * Get number of dead wolves
     * @return Number of dead wolves
     */
    public int getDeadWolfCount() {
        return deadWolfCount;
    }

    /**
     * Get data from each simulation tick
     * @return Array of strings - simulation data
     */
    public String[] getTickData() {
        String[] tickData = new String[8];
        tickData[1] = Integer.toString(getAnimals().size());
        tickData[2] = Integer.toString(getWolves().size());
        tickData[3] = Integer.toString(getSheeps().size());
        tickData[4] = Integer.toString(getNewbornWolfCount());
        tickData[5] = Integer.toString(getNewbornSheepCount());
        tickData[6] = Integer.toString(getDeadWolfCount());
        tickData[7] = Integer.toString(getDeadSheepCount());
        return tickData;
    }

    // Adds animal (Wolf or Sheep) to List of animals

    /**
     * Add animal to list of animals
     * @param animal Animal to be added to list of animals
     */
    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    // Removes animal (Wolf or Sheep_ from List of animals

    /**
     * Remove animal from list of animals
     * @param animal Animal to be removed from list of animals
     */
    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    /**
     * Add grass to list of grasses
     * @param grass Grass object to be added to list of grasses
     */
    public void addGrass(Grass grass) {
        grasses.add(grass);
    }

    /**
     * Check if the list of animals is empty
     * @return Whether list of animals if empty
     */
    public boolean isEmpty() {
        return animals.isEmpty();
    }

    /**
     * Perform reproduction if two animals of the same species are on the same position.
     * Consider reproduction probability.
     */
    private void reproduceIfSameSpecies() {
        // reset counters
        newbornWolfCount = 0;
        newbornSheepCount = 0;
        // Iterate over the list of animals.
        for (int i = 0; i < animals.size(); i++) {

             for (int j = i; j < animals.size(); j++) {
                 Animal animal1 = animals.get(i);
                 Animal animal2 = animals.get(j);
                // Check if the two animals are of the same species.
                if (animal1.getClass() == animal2.getClass()) {
                    // Check if the two animals are on the same position.
                    if (animal1.getPosX() == animal2.getPosX() && animal1.getPosY() == animal2.getPosY() && animal1.getId() != animal2.getId() && animal1.getAge() > 7 && animal2.getAge() > 7) {
                        int reproductionPosX = animal1.getPosX();
                        int reproductionPosY = animal1.getPosY();
                        // Check if both animals can reproduce
                        if (Animal.canReproduce()) {
                            // Create a new animal of the same type as the two parents.
                            if (animal1 instanceof Wolf) {
                                addAnimal(new Wolf(reproductionPosX, reproductionPosY, 80, Wolf.getSheepEnergy(), Animal.getReproduceProb()));
                                newbornWolfCount++;
                            } else if (animal1 instanceof Sheep) {
                                addAnimal(new Sheep(reproductionPosX, reproductionPosY, 80, Sheep.getGrassEnergy(), Animal.getReproduceProb()));
                                newbornSheepCount++;
                            }
                            animal1.setAge(0);
                            animal2.setAge(0);
                        }
                    }
                }
            }
        }
    }

    // Updates position of each animal (animals move)
    /**
     * Update position of each animal, check for reproduction and perform eating operation.
     * Remove animal if it's dead.
     * Update grass status.
     */
    public void update() {
        deadWolfCount = 0;
        deadSheepCount = 0;
        // reproduce if animals that are on the same position are the same species considering probability of reproduction
        reproduceIfSameSpecies();
        // move animals
        for (int i = 0; i < animals.size(); i++) {
//        for (Animal animal : animals) {
            Animal animal = animals.get(i);

            // eat
            if (animal.getEnergy() < 50) {
                if (animal instanceof Sheep) {
                    for (int k = 0; k < grasses.size(); k++) {
                        Grass grass = grasses.get(k);
//                    for (Grass grass : grasses) {
                        if (animal.getPosX() == grass.getPosX() && animal.getPosY() == grass.getPosY() && grass.getAge() >= 3) {
                            animal.eat();
//                            System.out.println("SHEEP NAJEDZONKO " + grass.getPosX() + " " + grass.getPosY());
                            grass.setAge(0);
                        }
                    }
                } else if (animal instanceof Wolf) {
                    for (int j = 0; j < animals.size(); j++) {
                        Animal maybeSheep = animals.get(j);
//                    }
//                    for (Animal maybeSheep: animals) {
                        if (maybeSheep instanceof Sheep && maybeSheep.getPosX() == animal.getPosX() && maybeSheep.getPosY() == animal.getPosY() && maybeSheep.getAge() >= 5) {
                            animal.eat();
//                            System.out.println("WOLF NAJEDZONKO " + maybeSheep.getId());
                            removeAnimal(maybeSheep);
                            deadSheepCount++;
                        }
                    }
                }
            }
            // move
            animal.move(WIDTH, HEIGHT, this);
        }
        // if animal is dead then remove it from the board
        for (int i = 0; i < animals.size(); i++) {
            Animal animal = animals.get(i);
            if (animal.isDead()) {
                removeAnimal(animal);
                if (animal instanceof Sheep)
                    deadSheepCount++;
                else
                    deadWolfCount++;
            }
        }
        // update grass status
        for (Grass grass : grasses) {
            grass.regrow();
        }
    }

    /**
     * Print horizontal border of the board
     * @param width Width of the board
     */
    public static void printHorizontalBorder(int width) {
        System.out.printf("%-3s", "+--");
        for (int z = 0; z < width; z++) {
            System.out.printf("%-3s", "---");
        }
        System.out.printf("%-3s", "+");
        System.out.println();
    }

    /**
     * Draw the board with its content - grass and animals
     */
    public void draw() {
        printHorizontalBorder(WIDTH);
        for (int i = 0; i < HEIGHT; i++) {
            System.out.printf("%-3s", "|");
            for (int j = 0; j < WIDTH; j++) {
                System.out.printf("%-3s", getSymbol(i, j));
            }
            System.out.printf("%-3s", "|");
            System.out.println();
        }
        printHorizontalBorder(WIDTH);

        System.out.println(animals.size() + " animals"); // displays current amount of animals on board
    }

    // Returns symbol on (x, y) position, doesn't include grass at the moment

    /**
     * Get character of an object on (x,y) position
     * @param x X index of the board
     * @param y Y index of the board
     * @return Char representing the object (animal or grass) on (x,y) position
     */
    private char getSymbol(int x, int y) {
        for (Animal animal : animals) {
            if (animal.getPosX() == x && animal.getPosY() == y) {
                return animal.getSymbol(animal.getAge());
            }
        }
        for (Grass grass : grasses) {
            if (grass.getPosX() == x && grass.getPosY() == y) {
                return grass.getSymbol(grass.getAge());
            }
        }
        return ' ';
    }
}