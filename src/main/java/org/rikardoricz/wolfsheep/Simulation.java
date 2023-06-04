package org.rikardoricz.wolfsheep;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

/**
 * Run and control the simulation and write collected data to CSV-formatted file
 */
public class Simulation {
    /**
     * Board object on which the simulation is happening. It has all the object present in the simulation
     */
    private Board board;

    /**
     * Properties object necessary for property manager
     * @see PropertyManager
     */
    private Properties properties;

    /**
     * Simulation's duration in ticks
     */
    private final int durationTicks;

    /**
     * Counter of simulation's ticks
     */
    private int tickCounter = 0;

    // Constructor

    /**
     * Construct new simulation
     *
     * @param width Board width
     * @param height Board height
     * @param durationTicks Simulation's duration in ticks
     */
    public Simulation(int width, int height, int durationTicks) {
        board = new Board(width, height);
        this.properties = new Properties();
        this.durationTicks = durationTicks;
    }

    /**
     * Get number of ticks
     * @return Number of ticks
     */
    public int getTickCounter() {
        return tickCounter;
    }

    /**
     * Write collected data of the simulation to CSV-formatted file
     * @param filePath Path to write file to
     * @param data Data to be written to file
     */
    public static void writeDataToCSV(String filePath, List<String[]> data) {
        File file = new File(filePath);
        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);

            // adding header to csv
            String[] header = { "Tick", "Animals", "Wolves", "Sheeps", "New Wolves", "New Sheeps", "Dead Wolves", "Dead Sheeps" };
            writer.writeNext(header);

            // add data to csv
            writer.writeAll(data);

            // closing writer connection
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Clear terminal window for clarity
     */
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
    /**
     * Run and control the simulation. Add sheeps, wolves and grass
     * @param sheepAmount Initial sheep amount on the board
     * @param wolfAmount Initial wolf amount on the board
     * @param reproductionProb Probability of reproduction
     * @param grassEnergy The amount of energy the sheep gains from eating a grass
     * @param sheepEnergy The amount of energy the wolf gains from eating a sheep
     * @param grassRegrowthTicks Amount of simulation ticks that takes a grass to (re)grow and be available to eat by sheeps again
     */
    public void run(int sheepAmount, int wolfAmount, double reproductionProb, int grassEnergy, int sheepEnergy, int grassRegrowthTicks) {

        System.out.println(board.getWidth() + " x " + board.getHeight());
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                board.addGrass(new Grass(i,j, 0,  grassRegrowthTicks));
            }
        }
        // Place sheeps on the board
        for (int i = 0; i < sheepAmount; i++) {
            Random random = new Random();
            int randomX = random.nextInt(board.getWidth());
            int randomY = random.nextInt(board.getHeight());

            board.addAnimal(new Sheep(randomX, randomY, 100, grassEnergy, reproductionProb));
        }
        // Place wolves on the board
        for (int i = 0; i < wolfAmount; i++) {
            Random random = new Random();
            int randomX = random.nextInt(board.getWidth());
            int randomY = random.nextInt(board.getHeight());

            board.addAnimal(new Wolf(randomX, randomY, 100, sheepEnergy, reproductionProb));
        }

        List<String[]> data = new ArrayList<>();

        System.out.println("Press any key to start the simulation...");
        new java.util.Scanner(System.in).nextLine();

        for (int i = 0; i < durationTicks; i++) {
            String [] tickData= board.getTickData();
            tickData[0] = Integer.toString(i);

            data.add(tickData);
            clearTerminal();
            tickCounter++;
            System.out.println("TICK: " + getTickCounter());
            if (board.isEmpty() || board.getSheeps().size() == 0 || board.getWolves().size() == 0) {
                System.out.println("""
                        The board is empty\s
                        OR there are only sheeps\s
                        OR there are only wolves\s
                        SEE db.csv file for results""");
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
        writeDataToCSV("./db.csv", data);
    }

    /**
     * The application's entry point
     * The initial values from config.properties file are validated and the simulation is run
     * @param args An array of command-line arguments for the application
     */
    public static void main(String[] args) {
        PropertyManager config = new PropertyManager();

        try {
            config.validateProperties();
        } catch (IllegalArgumentException e) {
            System.err.println("Error loading or validating configuration file.");
            System.exit(1);
        }
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
