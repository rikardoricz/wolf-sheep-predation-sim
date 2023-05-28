package org.rikardoricz.wolfsheep;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.ListIterator;

public class Board {
    private static final int MAX_WIDTH = 20;
    private static final int MAX_HEIGHT = 20;
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
    public void removeGrass(Grass grass) {
        grasses.remove(grass);
    }

    public boolean isEmpty() {
        return animals.isEmpty();
    }

    private void reproduceIfSameSpecies() {
//        for (ListIterator<Animal> it1 = animals.listIterator(); it1.hasNext(); ) {
//            for (ListIterator<Animal> it2 = animals.listIterator(); it2.hasNext(); ) {
//                Animal animal1 = it1.next();
//                Animal animal2 = it2.next();
//                if (animal1.getClass() == animal2.getClass()) {
//
//                    // Check if the two animals are on the same position.
//                    if (animal1.getPosX() == animal2.getPosX() && animal1.getPosY() == animal2.getPosY()) {
//                        int reproductionPosX = animal1.getPosX();
//                        int reproductionPosY = animal1.getPosY();
//
//                        // Check if both animals can reproduce
////                        if (animal1.canReproduce() && animal2.canReproduce()) {
//                        if (true) {
//                            System.out.println("REPRODUCTION TIME");
//
//                            // Create a new animal of the same type as the two parents.
//                            if (animal1 instanceof Wolf) {
//                                addAnimal(new Wolf(reproductionPosX, reproductionPosY, 80, Wolf.getSheepEnergy(), Animal.getReproduceProb()));
//                                System.out.println("DODANO WILKA");
//                            } else if (animal1 instanceof Sheep) {
//                                addAnimal(new Sheep(reproductionPosX, reproductionPosY, 80, Sheep.getGrassEnergy(), Animal.getReproduceProb()));
//                                System.out.println("DODANO OWCE");
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }

        // Iterate over the list of animals.
        for (int i = 0; i < animals.size(); i++) {
//            for (Animal animal1 : animals) {

             for (int j = i; j < animals.size(); j++) {
                 Animal animal1 = animals.get(i);
                 Animal animal2 = animals.get(j);
//                 for (Animal animal2 : animals) {

                // Check if the two animals are of the same species.
                if (animal1.getClass() == animal2.getClass()) {

                    // Check if the two animals are on the same position.
                    if (animal1.getPosX() == animal2.getPosX() && animal1.getPosY() == animal2.getPosY() && animal1.getId() != animal2.getId()) {
                        int reproductionPosX = animal1.getPosX();
                        int reproductionPosY = animal1.getPosY();
//
                        // Check if both animals can reproduce
                        if (animal1.canReproduce() && animal2.canReproduce()) {
                            System.out.println("REPRODUCTION TIME");
                            System.out.println(animal1.getId() + " SEX z pedałami " + animal2.getId());

                            // Create a new animal of the same type as the two parents.
                            if (animal1 instanceof Wolf) {
                                addAnimal(new Wolf(reproductionPosX, reproductionPosY, 80, Wolf.getSheepEnergy(), Animal.getReproduceProb()));
                                System.out.println("ADDED WOLF");
                            } else if (animal1 instanceof Sheep) {
                                addAnimal(new Sheep(reproductionPosX, reproductionPosY, 80, Sheep.getGrassEnergy(), Animal.getReproduceProb()));
                                System.out.println("ADDED SHEEP");
                            }
                        }
                    }
                }
            }
        }
    }

    // Updates position of each animal (animals move)
    public void update() {

        reproduceIfSameSpecies();
        // move animals
        for (Animal animal : animals) {
            // move
            animal.move(width, height);
            // eat
            animal.eat();
            // reproduce
        }
        // if animal is dead then remove it from the board
        animals.removeIf(Animal::isDead);
        // reproduce if animals that are on the same position are the same species considering probability of reproduction
//        reproduceIfSameSpecies();
        // TODO: update grass status

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
        for (int i = 0; i < width; i++) {
            System.out.printf("%-3s", "|");
            for (int j = 0; j < height; j++) {
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
            System.out.println(animal.getId() + " " + animal.getClass() + " " + animal.getEnergy() + " " + animal.getPosX() + " " + animal.getPosY());
//            System.out.println(dx + " " + dy);
        }
    }

    // Returns symbol on (x, y) position, doesn't include grass at the moment
    private char getSymbol(int x, int y) {
        for (Animal animal : animals) {
            if (animal.getPosX() == x && animal.getPosY() == y) {
                return animal.getSymbol();
            }
        }
        return '.';
    }
}