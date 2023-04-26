package com.example.battleboggle;

public abstract class GameBoard {
    // actual game board
    char[][] board;
    // rows in game board
    public int rows = 4;
    // columns in game baord
    public int columns = 4;
    // method to re-scramble all the letters in the board
    abstract public void shuffle();
}

class StandardBoard extends GameBoard {

    public StandardBoard(){
        shuffle();
    }
    public void shuffle(){
        // hardcoded default
        System.out.println("shuffling");
        this.board = new char[][] { { 'n', 'i', 'o', 'r' },
                                    { 'e', 'f', 't', 'n' },
                                    { 'o', 'a', 'c', 'l' },
                                    { 'i', 'e', 'r', 'p' } };
    }
}