package com.example.battleboggle;

public class GameBoardFactory {
    public GameBoard generateBoard(String type) {
        GameBoard board = null;
        System.out.println("in generateboard");
        if (type.equals("Standard")) {
            System.out.println("in standard");
            board = new StandardBoard();
        } else if (type.equals("Original")){
            System.out.println("in standard");
            board = new StandardBoard();
        } else {
            // error case
            System.out.println("SHould have specified a standard or original board");
        }
        return board;
    }
}
