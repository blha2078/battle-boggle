package com.example.battleboggle;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.max;

public abstract class Scorer {
    // counts the number of words with each letter count
    // example: letter_count[4] = 2 means there are 2 words with 5 letters
    // Since words must be at least 3 letters long, letterCount[0], letterCount[1] = 0 always
    public int[] letterCount = new int[16];
    // constructor updates letterCount
    public Scorer(ArrayList<String> valid_words){
        // counting each word
        // letterCount is all zeros by default
        for (int word = 0; word < valid_words.size(); word++){
            letterCount[valid_words.get(word).length()] ++;
        }
    }

    // default constructor- shouldn't be used but needed for abstract class
    public Scorer(){}

    // calculates a score and returns it
    abstract public int GetScore();

    // calculates score by standard Boggle rules:
//    letters : points
//    3 : 1
//    4 : 1
//    5 : 2
//    6 : 3
//    7 : 5
//    8+ : 11
    public int GetBoggleScore(){
        // initialize standard score to zero
        int standardScore = 0;

        // loop through the letterCount array starting at index 3 (since words must be at least 3 letters long)
        for (int i = 3; i < letterCount.length; i++) {
            // if there are any words with the current letter count
            if (letterCount[i] > 0) {
                // apply the appropriate point value based on the count using a switch statement
                switch (i) {
                    case 3:
                    case 4:
                        standardScore += letterCount[i];
                        break;
                    case 5:
                        standardScore += letterCount[i] * 2;
                        break;
                    case 6:
                        standardScore += letterCount[i] * 3;
                        break;
                    case 7:
                        standardScore += letterCount[i] * 5;
                        break;
                    default:
                        standardScore += letterCount[i] * 11;
                        break;
                }
            }
        }
        return standardScore;
    }
}

class StandardScorer extends Scorer {
    // simply calls super constructor with valid_words
    public StandardScorer(ArrayList<String> valid_words){
        super(valid_words);
    }
    // calculates score by standard Boggle rules
    public int GetScore() {
        // return the final score
        return this.GetBoggleScore();
    }
}


// Arcade scorer is the twist on our game- it creates an arcade-style scoring that includes valid words, invalid words, and missed words
class ArcadeScorer extends Scorer {
    // count variables to be updated later
    public double invalidWordCount = -1;
    public double missedWordCount = -1;
    // calls super constructor with valid_words and adds number of missed and invalid words
    public ArcadeScorer(ArrayList<String> valid_words, int invalidWordCount, int missedWordCount){
        super(valid_words);
        this.invalidWordCount = (double) invalidWordCount;
        this.missedWordCount = (double) missedWordCount;
    }
    // calculates score by Arcade Boggle rules (made up):
    // initial calc: score = ((standard score / missedWordCount) * 100,000 - (invalidWordCount * 10,000)) * randomizer
    // randomizer is added so that scores seem more like arcade (double between (0.99, 1.01))
    public int GetScore() {
        // calling getbogglescore scorer to get starting number
        double standardScore = (double) this.GetBoggleScore();

        // get randomizer
        Random r = new Random();
        double randomizer = 0.99 + (0.02) * r.nextDouble();

        // calculate arcadeScore
        // score minimum is 0
        double arcadeScore = max(0, (((standardScore / this.missedWordCount) * 1000000) - (invalidWordCount * 10000)) * randomizer);
        System.out.println("Arcade score: " + (int)arcadeScore);
        return (int) arcadeScore;
    }
}