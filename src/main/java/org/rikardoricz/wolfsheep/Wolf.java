package org.rikardoricz.wolfsheep;

public class Wolf extends Animal {
    private static final int MAX_ENERGY = 100;
    private static final int SHEEP_ENERGY = 20;
    private char symbol;

    // Constructor
    public Wolf(int posX, int posY, int energy) {
        super(posX, posY, energy);
        symbol = 'W';
    }

    // Move animal in random direction one cell max
    @Override
    public void move() {
        int[] dx = {-1, 0, 1};
        int[] dy = {1, -1, 0};
        int randomIndex = (int) (Math.random() * 3);
//        x += (int) (Math.random() * 2 - 1);
//        y += (int) (Math.random() * 2 - 1);
        int newX = posX + dx[randomIndex];
        int newY = posY + dy[randomIndex];

        if (newX >= 0 && newX < 10 && newY >= 0 && newY < 10) {
            setPosX(newX);
            setPosY(newY);
        }
    }

    @Override
    public void eat() {

    }

    @Override
    public void reproduce() {

    }

    @Override
    public void die() {

    }

    // Return animal symbol
    @Override
    public char getSymbol() {
        return symbol;
    }
}
