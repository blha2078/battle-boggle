package com.example.battleboggle;

import java.util.ArrayList;

// class to handle each round of a game
public class GameRound {
    // Factory to create game boards
    GameBoardFactory factory;
    // all the words that a user has input
    public static ArrayList<String> input_words = new ArrayList<>();
    // valid words that a user has input
    public static ArrayList<String> valid_words = new ArrayList<>();
    // invalid words that a user has input
    public ArrayList<String> invalid_words = new ArrayList<>();
    // words the user missed
    public ArrayList<String> missed_words = new ArrayList<>();

    // stores the regular score and arcade score
    public static int scores[] = {-1, -1};
    // gameboard for the round
    // to be replaced later with factory
//    public char[][] gameboard = { { 'n', 'i', 'o', 'r' },
//                                { 'e', 'f', 't', 'n' },
//                                { 'o', 'a', 'c', 'l' },
//                                { 'i', 'e', 'r', 'p' } };
    public GameBoard gameboard;

    // constructor to make a specified type of board
    public GameRound(GameBoardFactory factory){
        this.factory = factory;
    }
    public void addToList(String text) {
        input_words.add(text);
    }
    // method to play a round
    public void play(){
        // generate board
        this.gameboard = this.factory.generateBoard("Standard");
        // sample round
        //input_words.add("near");
        //input_words.add("nit");
        //input_words.add("notaword");
    }



    // method to analyze results of round
    public int[] analyzeRound(){
        // getting all the possible correct words in board
        ArrayList<String> possible_words = Gameplay.validWordHandler.getValidWords(this.gameboard.board);
        // filling valid_words and missed_words
        for (int i = 0; i < possible_words.size();i++)
        {
            // checking if word was input from user
            String word = possible_words.get(i);
            if (this.input_words.contains(word))
            {
                // valid word, on both lists
                this.valid_words.add(word);
                // removing from input words (so that at the end, all we are left with is invalid words in input_words)
                // this also will take care of duplicate words as desired
                this.input_words.remove(word);
            } else
            // in this case, the word was missed
            {
                this.missed_words.add(word);
            }
            System.out.println(possible_words.get(i));
        }
        // input_words is now just words that the user gave but aren't valid. We now make this the invalid_words list
        for (int i = 0; i < this.input_words.size();i++)
        {
            this.invalid_words.add(this.input_words.get(i));
        }
        // completely emptying invalid words just to be thorough (this makes them null, not completely removing)
        this.input_words.clear();


        System.out.println("Valid user words:");
        for (int i = 0; i < this.valid_words.size();i++)
        {
            System.out.println(this.valid_words.get(i));
        }


        System.out.println("Invalid user words:");
        for (int i = 0; i < this.invalid_words.size();i++)
        {
            System.out.println(this.invalid_words.get(i));
        }


        System.out.println("Missed user words:");
        for (int i = 0; i < this.missed_words.size();i++)
        {
            System.out.println(this.missed_words.get(i));
        }

        // calculating scores (using template pattern)
        StandardScorer standardScorer = new StandardScorer(this.valid_words);
        ArcadeScorer arcadeScorer = new ArcadeScorer(this.valid_words, this.invalid_words.size(), this.missed_words.size());
        this.scores[0] = standardScorer.GetScore();
        this.scores[1] = arcadeScorer.GetScore();
        // returns both types of scores
        return this.scores;
    }
}
