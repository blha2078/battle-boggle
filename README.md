# battle-boggle: Blake Hamilton, Matthew McDermott

## Battle Boggle Instructions
Battle Boggle is a twist on the classic Boggle word game. Players compete one at a time instead of all together in order to achieve the highest possible score. All scores are stored on a leaderboard, arcade-style. The game will give you your score after each round as it would be scored in standard Boggle, as well as your arcade score, which is generated based on the following:
- Word count/length: Similar to normal Boggle, positive points will be awarded for longer words
- Incorrect words: Contrary to standard Boggle, if you input a word and it is incorrect, you will be deducted for this. Deductions for incorrect words do not vary by word length (a three letter incorrect word hurts you the same as a seven letter one).
- All possible valid words: In each round, we calculate every possible word in the English dictionary on a given board. This is the biggest change compared to normal Boggle, as this is what makes it possible for users to compete against each other on different boards in a somewhat fair way. The goal of calculating all the possible words is to gauge how difficult a board is: the fewer the words, the harder we expect the board to be for a human, so we give more points for a harder board. For example, getting five three-letter words on a board with 500 possible words should be even (score-wise) with getting eight three-letter words on a board with 1000 possible words.



**IMPORTANT NOTE**: This repository was based off of a seperate repository where the back end/business logic was developed. In order to add JavaFX, we needed to completely restructure the project, and we decided the best way to do this was to make a new repository with a toy JavaFX project, and then add all other code from there. This explains the large amount of code added in "Initial Commit".
