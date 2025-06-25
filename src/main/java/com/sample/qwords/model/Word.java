package com.sample.qwords.model;

import java.util.Arrays;

/**
 * Represents a word in the Q-Words game with methods to validate guesses.
 * This class encapsulates the target word and provides functionality to compare
 * user guesses against it, generating appropriate feedback.
 */
public class Word {
    private String word;
    private char[] characters;

    public Word(String word) {
        this.word = word;
        this.characters = word.toCharArray();
    }

    public String getWord() {
        return word;
    }

    public boolean contains(char c) {
        for (char ch : characters) {
            if (ch == c) {
                return true;
            }
        }
        return false;
    }

    public boolean isCorrect(char[] guess) {
        return Arrays.equals(characters, guess);
    }

    /**
     * Analyzes the user's guessed word and generates feedback for each character.
     * This is the core logic of the Q-Words game, working similarly to Wordle.
     * 
     * <p>Each character is evaluated based on the following criteria:</p>
     * <ul>
     *   <li><strong>'+'</strong>: Character is present in the target word and in the correct position</li>
     *   <li><strong>'?'</strong>: Character is present in the target word but in the wrong position</li>
     *   <li><strong>'X'</strong>: Character is not present in the target word at all</li>
     * </ul>
     * 
     * <p>Example:</p>
     * <pre>
     * Target word: "SPRING"
     * User guess: "STRONG"
     * Return result: "+?+X++"
     * Explanation: S(+), T(?), R(+), O(X), N(+), G(+)
     * </pre>
     * 
     * @param guess the 6-letter word guessed by the user (case-insensitive)
     * @return a string representing feedback for each character (combination of '+', '?', 'X')
     * @throws NullPointerException if guess is null
     * @see #contains(char) for checking character existence
     * @since 1.0
     */
    public String getInfo(String guess) {
        char[] guessArray = guess.toCharArray();
        StringBuilder result = new StringBuilder();
        
        System.out.println("\n\nWord: " + word);
        System.out.println("\n\nGuess: " + guess);

        for (int i = 0; i < guessArray.length; i++) {
            char currentGuess = guessArray[i];
            System.out.println("Evaluating current char from guess to word: " + currentGuess + "->" + characters[i]);
            if (contains(currentGuess)) {
                // Check if the character is in the correct position
                if (characters[i] == currentGuess) {
                    // present and in the right position
                    result.append('+');
                } else {
                    // present but in wrong position
                    result.append('?');
                }
            } else {
                // character is not present
                result.append('X');
            }
        }

        return result.toString();
    }
}
