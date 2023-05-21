package org.rikardoricz.wolfsheep;

import java.util.List;
import java.util.ArrayList;

public class Board {
    private static final int MAX_WIDTH = 50;
    private static final int MAX_HEIGHT = 50;
    private int width;
    private int height;
    private List<Animal> animals;

    // Constructor
    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        animals = new ArrayList<>();
    }

    // Adds animal (Wolf or Sheep) to List of animals
    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    // Removes animal (Wolf or Sheep_ from List of animals
    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    // Updates position of each animal (animals move)
    public void move() {
        for (Animal animal : animals) {
            animal.move();
        }
    }

    // Draws symbols that represents animals on board
    public void draw() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                System.out.print(getSymbol(i, j));
            }
            System.out.println();
        }
    }

    // Returns symbol on (x, y) position
    private char getSymbol(int x, int y) {
        for (Animal animal : animals) {
            if (animal.getPosX() == x && animal.getPosY() == y) {
                return animal.getSymbol();
            }
        }
        return ' ';
    }

    public static void main(String[] args) {
        Board board = new Board(10,10);
        board.addAnimal(new Sheep(0,0,100));
        board.addAnimal(new Sheep(0,1,100));
        board.addAnimal(new Sheep(0,2,100));
        board.addAnimal(new Sheep(0,3,100));
        board.addAnimal(new Wolf(0,4,100));
        board.addAnimal(new Wolf(0,5,100));
        board.addAnimal(new Wolf(0,6,100));
        board.addAnimal(new Wolf(0,7,100));
        int tickCounter = 0;

        for (int i = 0; i < 20; i++) {
            board.draw();
            board.move();
            tickCounter++;
            System.out.println("TICK: " + tickCounter);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}