package org.rikardoricz.wolfsheep;

public class Grass {
    private int posX;
    private int posY;
    private final int REGROWTH_TIME = 3; // amount of ticks that takes a grass to regrow
    private static int regrowthCounter;

    public Grass(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public static int getRegrowthCounter() {
        return regrowthCounter;
    }

//    public static void setRegrowthCounter(int regrowthCounter) {
//        Grass.regrowthCounter = regrowthCounter;
//    }
}
