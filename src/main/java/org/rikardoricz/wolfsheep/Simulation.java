package org.rikardoricz.wolfsheep;

import java.io.IOException;
import java.util.List;

public class Simulation {
    private Board board;
//    private List<Animal> animals;
    private int tickCounter;

    // Constructor
    public Simulation(int width, int height) {
        board = new Board(width, height);
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
        tickCounter = 0;
        int simDurationTicks = 100;
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

        int tickCounter = 0;

        for (int i = 0; i < simDurationTicks; i++) {
            try {
                clearTerminal();
                board.draw();
                board.update();
                tickCounter++;
                System.out.println("TICK: " + tickCounter);
                Thread.sleep(300);
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
