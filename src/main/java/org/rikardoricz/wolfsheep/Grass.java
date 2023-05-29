package org.rikardoricz.wolfsheep;

public class Grass {
    private int posX;
    private int posY;
    private int age;
    private int grassRegrowthTicks; // amount of ticks that takes a grass to regrow
    private static int regrowthCounter;

    public Grass(int posX, int posY, int age, int grassRegrowthTicks) {
        this.posX = posX;
        this.posY = posY;
        this.age = age;
        this.grassRegrowthTicks = grassRegrowthTicks;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

//    public static int getRegrowthCounter() {
//        return regrowthCounter;
//    }
    public void regrow() {
        setAge(getAge() + 1);
    }
    public char getSymbol(int age) {
        if (age == 0)
            return '.';
        else if (age < grassRegrowthTicks/2)
            return '‥';
        else return '…';
    }
}
