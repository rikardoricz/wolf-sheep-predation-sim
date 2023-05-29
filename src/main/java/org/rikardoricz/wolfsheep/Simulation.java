package org.rikardoricz.wolfsheep;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

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
    public void run(int sheepAmount, int wolfAmount, double reproductionProb, int grassEnergy, int sheepEnergy, int grassRegrowthTicks) {

        System.out.println(board.getWidth() + " x " + board.getHeight());
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                board.addGrass(new Grass(i,j, 0,  grassRegrowthTicks));
            }
        }
        // Place both sheeps and wolves on board
        for (int i = 0; i < sheepAmount; i++) {
            Random random = new Random();
            int randomX = random.nextInt(board.getWidth());
            int randomY = random.nextInt(board.getHeight());

            board.addAnimal(new Sheep(randomX, randomY, 100, grassEnergy, reproductionProb));
        }
        for (int i = 0; i < wolfAmount; i++) {
            Random random = new Random();
            int randomX = random.nextInt(board.getWidth());
            int randomY = random.nextInt(board.getHeight());

            board.addAnimal(new Wolf(randomX, randomY, 100, sheepEnergy, reproductionProb));
        }

        List<String[]> data = new ArrayList<>();
        for (int i = 0; i < durationTicks; i++) {
            String [] tickData= board.getTickData();
            tickData[0] = Integer.toString(i);

            data.add(tickData);
            clearTerminal();
            tickCounter++;
            System.out.println("TICK: " + getTickCounter());
            if (board.isEmpty() || board.getSheeps().size() == 0 || board.getWolves().size() == 0) {
                System.out.println("PUSTO lub tylko OWCE lub tylko WILKI");
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
