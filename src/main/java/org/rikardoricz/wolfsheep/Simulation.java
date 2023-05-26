package org.rikardoricz.wolfsheep;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Simulation {
    private Board board;
//    private List<Animal> animals;
    private int tickCounter = 0;
    private static final int SIM_DURATION_TICKS = 100;

    // Constructor
    public Simulation(int width, int height) {
        board = new Board(width, height);
    }
    public int getTickCounter() {
        return tickCounter;
    }

    public static void clearTerminal() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("bash", "-c", "clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Runs the simulation
    public void run() {
        // TODO: make prompt for silumation start values

//        Board board = new Board(10,10);
        for (int i = 0; i < board.getWidth(); i++) {
            for (int j = 0; j < board.getHeight(); j++) {
                board.addGrass(new Grass(i,j));
            }
        }
        // Place both sheeps and wolves on board
        board.addAnimal(new Sheep(0,0,100));
        board.addAnimal(new Sheep(1,1,100));
        board.addAnimal(new Sheep(2,2,100));
        board.addAnimal(new Sheep(3,3,100));
        board.addAnimal(new Wolf(1,4,100));
        board.addAnimal(new Wolf(2,5,100));
        board.addAnimal(new Wolf(3,6,100));
        board.addAnimal(new Wolf(4,7,100));

        for (int i = 0; i < SIM_DURATION_TICKS; i++) {
            clearTerminal();
            tickCounter++;
            System.out.println("TICK: " + getTickCounter());
            if (board.isEmpty()) {
                System.out.println("PUSTO");
                break;
            }
            board.draw();
            board.update();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Simulation simulation = new Simulation(10, 10);
        simulation.run();
    }
}
