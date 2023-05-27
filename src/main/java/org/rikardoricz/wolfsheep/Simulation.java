package org.rikardoricz.wolfsheep;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class Simulation {
    private Board board;
    private Properties properties;
    private int durationTicks;
//    private List<Animal> animals;
    private int tickCounter = 0;

    // Constructor
    public Simulation(int width, int height, int durationTicks) {
        board = new Board(width, height);
        this.properties = new Properties();
        this.durationTicks = durationTicks;
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

//    public void readConfigFile(Properties properties) {
//        try {
//            properties.load(new FileInputStream("config.properties"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        int boardWidth = properties.getIntProperty("boardWidth");
//        int boardHeight =properties.getIntProperty("boardHeight");
//        int sheepAmount = properties.getIntProperty("sheepAmount");
//        int wolfAmount = properties.getIntProperty("wolfAmount");
//        int reproductionProb = properties.getIntProperty("reproductionProb");
//        int grassEnergy = properties.getIntProperty("grassEnergy");
//        int sheepEnergy = properties.getIntProperty("sheepEnergy");
//        int grassRegrowthTicks = properties.getIntProperty("grassRegrowthTicks");
//    }

    // Runs the simulation
    public void run(int sheepAmount, int wolfAmount, double reproductionProb, int grassEnergy, int sheepEnergy, int grassRegrowthTicks) {

        System.out.println(board.getWidth() + " x " + board.getHeight());
        for (int i = 0; i < board.getWidth(); i++) {
            for (int j = 0; j < board.getHeight(); j++) {
                board.addGrass(new Grass(i,j, grassRegrowthTicks));
            }
        }
        // Place both sheeps and wolves on board
        for (int i = 0; i < sheepAmount; i++) {
            board.addAnimal(new Sheep(i, i, 100, grassEnergy, reproductionProb));
        }
        for (int i = 0; i < wolfAmount; i++) {
            board.addAnimal(new Wolf(i, i, 100, sheepEnergy, reproductionProb));
        }

        for (int i = 0; i < durationTicks; i++) {
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
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        PropertyManager config = new PropertyManager();
//        System.out.println("boardHeight = " + config.getProperty("boardHeight"));
        int boardWidth = config.getIntProperty("board.width");
        int boardHeight = config.getIntProperty("board.height");
        int durationTicks = config.getIntProperty("simulation.duration.ticks");
        int sheepAmount = config.getIntProperty("initial.sheep.amount");
        int wolfAmount = config.getIntProperty("initial.wolf.amount");
        double reproductionProb = config.getDoubleProperty("reproduction.probability");
        int grassEnergy = config.getIntProperty("sheep.grass.energy");
        int sheepEnergy = config.getIntProperty("wolf.sheep.energy");
        int grassRegrowthTicks = config.getIntProperty("grass.regrowth.ticks");

        Simulation simulation = new Simulation(boardWidth, boardHeight, durationTicks);
        simulation.run(sheepAmount, wolfAmount, reproductionProb, grassEnergy, sheepEnergy, grassRegrowthTicks);
    }
}
