package com.example.battleboggle;

public class GameBoardFactory {
    public GameBoard generateBoard(String type) {
        GameBoard board = null;
        System.out.println("in generateboard");
        if (type.equals("Standard")) {
            System.out.println("in standard");
            board = new StandardBoard();
        }
        return board;
    }
}
