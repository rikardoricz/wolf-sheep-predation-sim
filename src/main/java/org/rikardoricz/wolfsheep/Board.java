package org.rikardoricz.wolfsheep;

import java.sql.Array;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.ListIterator;

public class Board {
    private static final int MAX_WIDTH = 20;
    private static final int MAX_HEIGHT = 20;
    private static int newbornSheepCount = 0;
    private static int newbornWolfCount = 0;
    private int width;
    private int height;
    private List<Animal> animals;
    private List<Grass> grasses;

    // Constructor
    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        animals = new ArrayList<>();
        grasses = new ArrayList<>();
    }

    // Getters
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<Animal> getAnimals() {
        return animals;
    }
    public List<Wolf> getWolves() {
        List<Wolf> wolves = new ArrayList<>();
        for (Animal animal : animals) {
            if (animal instanceof Wolf)
                wolves.add((Wolf) animal);
        }
        return wolves;
    }
    public List<Sheep> getSheeps() {
        List<Sheep> sheeps = new ArrayList<>();
        for (Animal animal : animals) {
            if (animal instanceof Sheep)
                sheeps.add((Sheep) animal);
        }
        return sheeps;
    }

    public int getNewbornSheepCount() {
        return newbornSheepCount;
    }
    public int getNewbornWolfCount() {
        return newbornWolfCount;
    }
    public String[] getTickData() {
        String[] tickData = new String[6];
        tickData[1] = Integer.toString(getAnimals().size());
        tickData[2] = Integer.toString(getWolves().size());
        tickData[3] = Integer.toString(getSheeps().size());
        tickData[4] = Integer.toString(getNewbornWolfCount());
        tickData[5] = Integer.toString(getNewbornSheepCount());
        return tickData;
    }

    // Adds animal (Wolf or Sheep) to List of animals
    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    // Removes animal (Wolf or Sheep_ from List of animals
    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }
    public void addGrass(Grass grass) {
        grasses.add(grass);
    }

    public boolean isEmpty() {
        return animals.isEmpty();
    }

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
                    if (animal1.getPosX() == animal2.getPosX() && animal1.getPosY() == animal2.getPosY() && animal1.getId() != animal2.getId() && animal1.getAge() > 5 && animal2.getAge() > 5) {
                        int reproductionPosX = animal1.getPosX();
                        int reproductionPosY = animal1.getPosY();
//
                        // Check if both animals can reproduce
                        if (Animal.canReproduce()) {
//                        if (animal1.canReproduce() && animal2.canReproduce()) {
                            System.out.println(animal1.getId() + " SEXY TIME WITH " + animal2.getId());

                            // Create a new animal of the same type as the two parents.
                            if (animal1 instanceof Wolf) {
                                addAnimal(new Wolf(reproductionPosX, reproductionPosY, 80, Wolf.getSheepEnergy(), Animal.getReproduceProb()));
                                System.out.println("ADDED WOLF");
                                newbornWolfCount++;
                            } else if (animal1 instanceof Sheep) {
                                addAnimal(new Sheep(reproductionPosX, reproductionPosY, 80, Sheep.getGrassEnergy(), Animal.getReproduceProb()));
                                System.out.println("ADDED SHEEP");
                                newbornSheepCount++;
                            }
                        }
                    }
                }
            }
        }
    }

    // Updates position of each animal (animals move)
    public void update() {
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
                            System.out.println("SHEEP NAJEDZONKO " + grass.getPosX() + " " + grass.getPosY());
                            grass.setAge(0);
                        }
                    }
                } else if (animal instanceof Wolf) {
                    for (int j = 0; j < animals.size(); j++) {
                        Animal maybeSheep = animals.get(j);
//                    }
//                    for (Animal maybeSheep: animals) {
                        if (maybeSheep instanceof Sheep && maybeSheep.getPosX() == animal.getPosX() && maybeSheep.getPosY() == animal.getPosY()) {
                            animal.eat();
                            System.out.println("WOLF NAJEDZONKO " + maybeSheep.getId());
                            removeAnimal(maybeSheep);
                        }
                    }
                }
            }
            // move
            animal.move(width, height);
        }
        // if animal is dead then remove it from the board
        animals.removeIf(Animal::isDead);
        // TODO: update grass status
        for (Grass grass : grasses) {
            grass.regrow();
        }

//        System.out.println(animals);

//        IMPORTANT!!! You can't modify the List in for each loop. If you want to remove any elements in loop use iterator. You can remove elements using iterator.remove(); which deletes current element in the iterator.
//        SO I CAN'T EXECUTE CODE BELOW:
//        for (Animal animal : animals) {
//            if (animal.isDead()) {
//                removeAnimal(animal);
//            }
//        }
    }

    public static void printHorizontalBorder(int width) {
        System.out.printf("%-3s", "+--");
        for (int z = 0; z < width; z++) {
            System.out.printf("%-3s", "---");
        }
        System.out.printf("%-3s", "+");
        System.out.println();
    }
    public void draw() {
        printHorizontalBorder(width);
        for (int i = 0; i < height; i++) {
            System.out.printf("%-3s", "|");
            for (int j = 0; j < width; j++) {
                // TODO: mark accordingly on board when wolf and sheep overlap
                System.out.printf("%-3s", getSymbol(i, j));
            }
            System.out.printf("%-3s", "|");
            System.out.println();
        }
        printHorizontalBorder(width);

        printAnimalsInfo();
        System.out.println(animals.size() + " animals"); // displays current amount of animals on board
    }
    // Used just for print debugging
    private void printAnimalsInfo() {
        System.out.println("Id En X Y");
        for (Animal animal : animals) {
            System.out.println(animal.getId() + " " + animal.getClass() + " " + animal.getEnergy() + " " + animal.getAge() + " " + animal.getPosX() + " " + animal.getPosY());
        }
    }

    // Returns symbol on (x, y) position, doesn't include grass at the moment
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