package com.example.battleboggle;

import java.io.*;

public class Leaderboard {
    int leaderboardLength = 10;
    // name of file where data is stored
    public String filename = "src/main/java/com/example/battleboggle/leaderboard.csv";
    // Tope 10 usernames, sorted by score (first best)
    String [] usernames = new String[leaderboardLength];

    // top 10 scores, sorted by score (first best)
    int[] scores = new int[leaderboardLength];

    //getters for scores and usernames
    String[] getUsernames(){ return this.usernames;}
    int[] getScores(){ return this.scores;}

    // SINGLETON PATTERN: EAGER INSTANTIATION
    private static Leaderboard leaderboard = new Leaderboard();
    // Private constructor for singleton
    private Leaderboard(){
        // create text file if it does not already exist
        this.fileCreator(this.filename);
        // filling the new file with blank data
        this.fillNewLeaderboard();
    };
    // instance getter for singleton
    public static synchronized  Leaderboard getLeaderboard(){ return leaderboard; }

    // method to update the leaderboard. Returns true if user broke top 10, false if not
    boolean updateLeaderboard(String username, int score){
        // checking if user beat 10th best score
        if (score < scores[this.leaderboardLength - 1]){
            System.out.println("Score not good enough for leaderboard");
            return false;
        }
        // score is indeed in top 10
        else {
            System.out.println("Score is in top 10, updating leaderboard...");
            // first, iterate through and see where the new score belong
            int index = this.leaderboardLength - 1;
            while (index >= 1 && score > this.scores[index - 1]){
                index --;
            }
            System.out.println("Index for new score: " + index);
            // getting score that is being replaced
            String prevUsername = this.usernames[index];
            int prevScore = this.scores[index];
            // putting new score in
            this.usernames[index] = username;
            this.scores[index] = score;
            // bump down the rest of leaderboard
            while (index < this.leaderboardLength - 1){
                // getting entry from one below
                String tempUsername = this.usernames[index+1];
                int tempScore = this.scores[index+1];
                // updating entry one below with prev
                this.usernames[index+1] = prevUsername;
                this.scores[index+1] = prevScore;
                // updating previous to temp
                prevUsername = tempUsername;
                prevScore = tempScore;
                // updating index
                index ++;
            }
            try { // from stackoverflow
                FileWriter writer = new FileWriter(this.filename);

                // Write the header row
                writer.append("Username,Score\n");

                // Write the data rows
                for (int i = 0; i < usernames.length; i++) {
                    writer.append(usernames[i])
                            .append(",")
                            .append(String.valueOf(scores[i]))
                            .append("\n");
                }

                writer.close();

                System.out.println("Data has been written to " + this.filename);

            } catch (IOException e) {
                System.out.println("An error occurred while writing to " + this.filename + ": " + e.getMessage());
            }
            return true;
        }
    }

    // method to create a file if it doesn't yet exist
    public void fileCreator(String filename){
        File file = new File(filename);

        if (file.exists()) {
            System.out.println("The leaderboard file exists!");
            // if file exists, we need to set arrays to what they should be based on the csv
            // from stack overflow: getting csv data
            int row = 0;

            try {
                BufferedReader reader = new BufferedReader(new FileReader(this.filename));

                // Skip the header row
                reader.readLine();

                // Read the data rows
                String line;
                while ((line = reader.readLine()) != null && row < 10) {
                    String[] data = line.split(",");
                    this.usernames[row] = data[0];
                    this.scores[row] = Integer.parseInt(data[1]);
                    row++;
                }

                reader.close();

                System.out.println("Data has been read from " + this.filename);

            } catch (IOException e) {
                System.out.println("An error occurred while reading from " + this.filename + ": " + e.getMessage());
            }

            // Print the data from arrays
            for (int i = 0; i < this.leaderboardLength; i++) {
                System.out.println(usernames[i] + ": " + scores[i]);
            }
        } else {
            System.out.println("The leaderboard file does not exist, creating it now.");

            try {
                boolean created = file.createNewFile();
                if (created) {
                    System.out.println("The leaderboard file has been created.");
                } else {
                    System.out.println("Failed to create the leaderboard file.");
                }
            } catch (IOException e) {
                System.out.println("An error occurred while creating the leaderboard file: " + e.getMessage());
            }
        }
    }

    // fills new file with sample data
    public void fillNewLeaderboard(){
        try { // from stackoverflow
            FileWriter writer = new FileWriter(this.filename);
            // Write the header row
            writer.append("Username,Score\n");
            // Write the data rows
            for (int i = 0; i < this.leaderboardLength; i++) {
                writer.append("--------")
                        .append(",")
                        .append(String.valueOf(0))
                        .append("\n");
            }
            writer.close();
            System.out.println("Data has been written to " + this.filename);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to " + this.filename + ": " + e.getMessage());
        }
    }
}
