package com.example.battleboggle;

// main class to run game
public class Gameplay {
    // current user's username
    public String username;

    //leaderboard object
//    LeaderBoard leaderBoard;

    // Alphabet size
    public static final int SIZE = 26;

    public static final int M = 4;
    public static final int N = 4;
    // word handler object to be persisted through all rounds
    public static ValidWordHandler validWordHandler = new ValidWordHandler();
    // file containing dictionary
    public static final String dictionaryPath = "src/main/java/com/example/battleboggle/dictionary.txt";

    // method to run full game
    public void runGame(){
        // keep creating game rounds and running them as long as we want to play
        boolean finished = false;
        // creating a factory object to create boards
        GameBoardFactory factory = new GameBoardFactory();
        do {
            GameRound round = new GameRound(factory);
            round.play();
            round.analyzeRound();
            finished = true; // this will limit to one round for now
        } while (!finished);
    }

    // method to end the game
    public void endGame(){

    }




}