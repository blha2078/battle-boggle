package com.example.battleboggle;
import java.util.Random;

public abstract class GameBoard {
    // actual game board
    char[][] board;
    // rows in game board
    public int rows = 4;
    // columns in game baord
    public int columns = 4;

    // method to pick random letter from a string
    public char randLetter(String s){
        Random rand = new Random();
        char[] char_arr = s.toCharArray();
        return char_arr[rand.nextInt(char_arr.length)];
    }

    // method to re-scramble all the letters in the board
    public void shuffle(String[] cubes){
        System.out.println("shuffling");

        // picking the side of each dice that will come up
        char[] tempList = new char[cubes.length];
        for (int i = 0; i < cubes.length; i++){
            tempList[i] = this.randLetter(cubes[i]);
        }

        // now the letters are random, we need to randomize the order
        Random rand = new Random();
        for (int i = 0; i < tempList.length; i++) {
            int randomIndexToSwap = rand.nextInt(tempList.length);
            char temp = tempList[randomIndexToSwap];
            tempList[randomIndexToSwap] = tempList[i];
            tempList[i] = temp;
        }

        // making new board
        char[][] tempBoard = new char[this.rows][this.columns];


        // going through x dim then y dim
        for (int x = 0; x < this.rows; x++){
            for (int y = 0; y < this.columns; y++){
                // changing from the 1-d list of dice to the 2-d board
                tempBoard[x][y] = tempList[(x*4)+y];
            }
        }
        // updating the object baord
        this.board = tempBoard;
    }
}

// Standrad current Boggle dice, 2008+
class StandardBoard extends GameBoard {
    String standardCubes[] = {
            "aaeegn", "abbjoo", "achops", "affkps",
            "aoottw", "cimotu", "deilrx", "delrvy",
            "distty", "eeghnw", "eeinsu", "ehrtvw",
            "eiosst", "elrtty", "himnqu", "hlnnrz"
    };
    // shuffles every time board is created
    public StandardBoard(){
        shuffle(this.standardCubes);
    }
}

// original boggle dice, 1987-2008
class OriginalBoard extends GameBoard {
    String originalCubes[] = {
            "aaciot", "abilty", "abjmoqu", "acdemp",
            "acelrs", "adenvz", "ahmors", "biforx",
            "denosw", "dknotu", "eefhiy", "egkluy",
            "egintv", "ehinps", "elpstu", "gilruw"
    };
    // shuffles every time a board is created
    public OriginalBoard(){
        shuffle(this.originalCubes);
    }
}