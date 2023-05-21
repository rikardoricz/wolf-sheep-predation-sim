package org.rikardoricz.wolfsheep;

import java.sql.Array;
import java.util.List;
import java.util.ArrayList;

public class Board {
    private static final int MAX_WIDTH = 50;
    private static final int MAX_HEIGHT = 50;
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

    // Updates position of each animal (animals move)
    public void update() {
        for (Animal animal : animals) {
            animal.move();
        }
    }

    // Draws symbols that represents animals on board
//    public void draw() {
//        for (int i = 0; i < width; i++) {
//            for (int j = 0; j < height; j++) {
//                // Printing board frame
//                if (i == 0 || i == height - 1) {
//                    if (j == 0 || j == width - 1) {
//                        System.out.print("+");
//                    } else {
//                        System.out.print("-");
//                    }
//                } else {
//                    if (j == 0 || j == width - 1) {
//                        System.out.print("|");
//                        // End of priting board frame
//                    } else {
//                        System.out.print(getSymbol(i, j));
//                    }
//                }
//            }
//            System.out.println();
//        }
//    }
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
    }

    // Returns symbol on (x, y) position
    private char getSymbol(int x, int y) {
        for (Animal animal : animals) {
            if (animal.getPosX() == x && animal.getPosY() == y) {
                return animal.getSymbol();
            }
        }
        return '.';
    }

//    public static void main(String[] args) {
//        // for now dimensions of board are fixed, but in future user will be prompted for baord width and height
//        Board board = new Board(10,10);
//        board.addAnimal(new Sheep(0,0,100));
//        board.addAnimal(new Sheep(1,1,100));
//        board.addAnimal(new Sheep(2,2,100));
//        board.addAnimal(new Sheep(3,3,100));
//        board.addAnimal(new Wolf(1,4,100));
//        board.addAnimal(new Wolf(2,5,100));
//        board.addAnimal(new Wolf(3,6,100));
//        board.addAnimal(new Wolf(4,7,100));
//        int tickCounter = 0;
//
//        for (int i = 0; i < 20; i++) {
//            board.draw();
//            board.update();
//            tickCounter++;
//            System.out.println("TICK: " + tickCounter);
//
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//
//    }
}