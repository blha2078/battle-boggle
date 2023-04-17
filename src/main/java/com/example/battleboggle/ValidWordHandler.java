package com.example.battleboggle;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class ValidWordHandler {
    // all valid words for a given board
    public ArrayList<String> valid_words = new ArrayList<>();
    // root node to the trie
    public TrieNode trieRoot = new TrieNode();

    // Default constructor
    ValidWordHandler(){
        loadTrie();
    }

    // fills the valid_words variable based on game board
    public ArrayList<String> getValidWords(char gameboard[][]){
        System.out.println("finding words");
        findWords(gameboard, this.trieRoot);
        return this.valid_words;
    }

    // method to load the trie given a file
    public void loadTrie(){
        long startTime = System.nanoTime();

        // opening file stream to get words
        // from https://stackoverflow.com/questions/13185727/reading-a-txt-file-using-scanner-class-in-java
        FileInputStream inputStream = null;
        Scanner sc = null;
        File file = new File(Gameplay.dictionaryPath);
        try {
            try {
                inputStream = new FileInputStream(file);
                sc = new Scanner(inputStream, "UTF-8");
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    insert(this.trieRoot, line);
                }
//                 note that Scanner suppresses exceptions
                if (sc.ioException() != null) {
                    throw sc.ioException();
                }
            } catch (Exception e) {
                System.out.println("There was an error reading " + e);
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (sc != null) {
                    sc.close();
                }
            }
        } catch(Exception e){
            System.out.println("there was an error closing " + e);
        }
        // finding time it took to load all words
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;
        System.out.println("Trie load time: " + duration + " ms");
    }

    // inserts a new word, or "key" to the trie
    // from https://www.geeksforgeeks.org/boggle-using-trie/
    public void insert(TrieNode root, String key){
        int n = key.length();
        TrieNode pChild = root;

        for (int i = 0; i < n; i++) {
            int index = key.charAt(i) - 'a';
//            int index = key.charAt(i);

            if (pChild.Child[index] == null)
                pChild.Child[index] = new TrieNode();

            pChild = pChild.Child[index];
        }

        // make last node as leaf node
        pChild.leaf = true;
    }

    // function to check that current location
    // (i and j) is in matrix range
    public boolean isSafe(int i, int j, boolean visited[][])
    {
        return (i >= 0 && i < Gameplay.M && j >= 0
                && j < Gameplay.N && !visited[i][j]);
    }

    // A recursive function to add all possible words to valid_words
    // from https://www.geeksforgeeks.org/boggle-using-trie/
    public void searchWord(TrieNode root, char boggle[][], int i,
                           int j, boolean visited[][], String str)
    {
        // if we found word in trie / dictionary
        if (root.leaf == true)
//            System.out.println(str);
            this.valid_words.add(str);

        // If both I and j in  range and we visited
        // that element of matrix first time
        if (isSafe(i, j, visited)) {
            // make it visited
            visited[i][j] = true;

            // traverse all child of current root
            for (int K = 0; K < Gameplay.SIZE; K++) {
                if (root.Child[K] != null) {
                    // current character
                    char ch = (char)(K + 'a');
//                    char ch = (char)(K);

                    // Recursively search reaming character of word
                    // in trie for all 8 adjacent cells of
                    // boggle[i][j]
                    if (isSafe(i + 1, j + 1, visited)
                            && boggle[i + 1][j + 1] == ch)
                        searchWord(root.Child[K], boggle,
                                i + 1, j + 1,
                                visited, str + ch);
                    if (isSafe(i, j + 1, visited)
                            && boggle[i][j + 1] == ch)
                        searchWord(root.Child[K], boggle,
                                i, j + 1,
                                visited, str + ch);
                    if (isSafe(i - 1, j + 1, visited)
                            && boggle[i - 1][j + 1] == ch)
                        searchWord(root.Child[K], boggle,
                                i - 1, j + 1,
                                visited, str + ch);
                    if (isSafe(i + 1, j, visited)
                            && boggle[i + 1][j] == ch)
                        searchWord(root.Child[K], boggle,
                                i + 1, j,
                                visited, str + ch);
                    if (isSafe(i + 1, j - 1, visited)
                            && boggle[i + 1][j - 1] == ch)
                        searchWord(root.Child[K], boggle,
                                i + 1, j - 1,
                                visited, str + ch);
                    if (isSafe(i, j - 1, visited)
                            && boggle[i][j - 1] == ch)
                        searchWord(root.Child[K], boggle,
                                i, j - 1,
                                visited, str + ch);
                    if (isSafe(i - 1, j - 1, visited)
                            && boggle[i - 1][j - 1] == ch)
                        searchWord(root.Child[K], boggle,
                                i - 1, j - 1,
                                visited, str + ch);
                    if (isSafe(i - 1, j, visited)
                            && boggle[i - 1][j] == ch)
                        searchWord(root.Child[K], boggle,
                                i - 1, j,
                                visited, str + ch);
                }
            }

            // make current element unvisited
            visited[i][j] = false;
        }
    }


    // Adds all possible words to valid_words
    // Calls SearchWords (recursive)
    // from https://www.geeksforgeeks.org/boggle-using-trie/
    public void findWords(char boggle[][], TrieNode root)
    {
        // Mark all characters as not visited
        boolean[][] visited = new boolean[Gameplay.M][Gameplay.N];
        TrieNode pChild = root;

        String str = "";

        // traverse all matrix elements
        for (int i = 0; i < Gameplay.M; i++) {
            for (int j = 0; j < Gameplay.N; j++) {
                // we start searching for word in dictionary
                // if we found a character which is child
                // of Trie root
                if (pChild.Child[(boggle[i][j]) - 'a'] != null) {
                    str = str + boggle[i][j];
                    searchWord(pChild.Child[(boggle[i][j]) - 'a'],
                            boggle, i, j, visited, str);
                    str = "";
                }
            }
        }
    }
}
