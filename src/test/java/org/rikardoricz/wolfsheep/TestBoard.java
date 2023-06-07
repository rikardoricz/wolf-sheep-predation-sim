package org.rikardoricz.wolfsheep;

import org.junit.jupiter.api.Assertions;

import org.junit.Test;

public class TestBoard extends Board {
    private static final Board board;
    static {
        board = new Board(30,30);
        board.addAnimal(new Wolf(15,15,80,20,1));
        board.addAnimal(new Wolf(15,15,80,20,1));
        board.addAnimal(new Sheep(5,5,80,4,0.05));
        board.addGrass(new Grass(5,5,5,3));
        board.addGrass(new Grass(15,15,5,3));
    }
    public TestBoard() {
        super(30, 30);
    }

    @Test
    public void testAnimalsAndGrassAmountOnBoard() {
        Assertions.assertEquals(3, board.getAnimals().size());
        Assertions.assertEquals(2, board.getWolves().size());
        Assertions.assertEquals(1, board.getSheeps().size());
    }
    @Test
    public void testAnimalsAge() {
        board.getWolves().get(0).setAge(10);
        board.getWolves().get(1).setAge(10);
        Assertions.assertEquals(10, board.getWolves().get(0).getAge());
        Assertions.assertEquals(0, board.getSheeps().get(0).getAge());
    }
    @Test
    public void testIfAnimalsDieAndReproduce() {
        board.getSheeps().get(0).setEnergy(-5); // set sheep energy to negative value to it will be dead and removed from board
        board.update(); // update the board
        Assertions.assertEquals(1, board.getNewbornWolfCount()); // check if new wolf was born on recent board update
        Assertions.assertEquals(0, board.getSheeps().size()); // check if the sheep with negative energy value was removed from board
        Assertions.assertEquals(1, board.getDeadSheepCount()); // check if deadSheepCounter increments after the sheep's death
//        for (int i = 0; i < board.getAnimals().size(); i++) { // print all animals on whe board with each id and class name
//            Animal animal = board.getAnimals().get(i);
//            System.out.println(animal.getId() + " " + animal.getEnergy() + " " + animal.getClass());
//        }
        Assertions.assertEquals(1, board.getNewbornWolfCount()); // check if newbornWolfCount increments after the wolf is born
        Assertions.assertEquals(3, board.getWolves().size()); // check if wolves amount is correct after reproduction
        Assertions.assertEquals(3, board.getAnimals().size()); // check if animals amount is correct after changes
    }
}