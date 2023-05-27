package org.rikardoricz.wolfsheep;

import java.util.List;
import java.util.ArrayList;

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

    // Updates position of each animal (animals move)
    public void update() {
        // if animal is dead then remove it from the board
        animals.removeIf(Animal::isDead);

        // move animals
        for (Animal animal : animals) {
            animal.move(width, height);
        }
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
            System.out.println(animal.getId() + " " + animal.getEnergy() + " " + animal.getPosX() + " " + animal.getPosY());
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