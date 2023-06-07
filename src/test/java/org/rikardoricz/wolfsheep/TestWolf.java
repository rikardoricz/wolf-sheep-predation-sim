package org.rikardoricz.wolfsheep;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestWolf extends Wolf {
    private static final Wolf wolf;
    public TestWolf() {
        super(20,20,80,20,0.05);
    }
    static {
        wolf = new Wolf(15,15,100,20,0.05);
    }
    @Test
    public void testWolfEnergyAndAge() {
        Board board = new Board(15,15);
        wolf.move(15,15, board);
        wolf.move(15,15, board);
        wolf.move(15,15, board);
        wolf.move(15,15, board);
        wolf.move(15,15, board);
        // wolf moves 5 times, so age increments by 5
        // energy decreased by 5 x 2 (move cost equals 2)
        Assertions.assertEquals(90, wolf.getEnergy());
        Assertions.assertEquals(5, wolf.getAge());
    }
}
