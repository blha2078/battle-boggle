package com.example.battleboggle;

// from https://www.geeksforgeeks.org/boggle-using-trie/
public class TrieNode {
    TrieNode[] Child = new TrieNode[Gameplay.SIZE];

    // isLeaf is true if the node represents
    // end of a word
    boolean leaf;

    // constructor
    public TrieNode()
    {
        leaf = false;
        for (int i = 0; i < Gameplay.SIZE; i++)
            Child[i] = null;
    }
}
