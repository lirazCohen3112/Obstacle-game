package com.example.obstaclegame;


public class GameManager {

    private int carLocation = 1;
    private int lives = 3;
    private int[][] data_stone;

    public GameManager(){
        data_stone = new int[][]{
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };
    };

    public int[][] getDataStone () {
        return data_stone;
    }

    public void changeCarLocation(int carLoc){
        carLocation = carLoc;
    }

    public int getCarLocation(){
        return carLocation;
    }

    public int getLives(){
        return lives;
    }

    public void missingCounterLives(){
        lives--;
    }
}
