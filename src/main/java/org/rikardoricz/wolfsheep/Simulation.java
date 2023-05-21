package org.rikardoricz.wolfsheep;

import java.util.List;

public class Simulation {
    private Board board;
//    private List<Animal> animals;
    private int tickCounter;

    // Constructor
    public Simulation(int width, int height) {
        board = new Board(width, height);
    }

    // Runs the simulation
    public void run() {
        tickCounter = 0;
        int simDurationTicks = 20;
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
            board.draw();
            board.update();
            tickCounter++;
            System.out.println("TICK: " + tickCounter);

            try {
                Thread.sleep(1000);
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
