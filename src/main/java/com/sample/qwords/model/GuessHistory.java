package com.sample.qwords.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the history of guesses made by a player in a game session.
 * Each entry contains both the guessed word and the feedback received.
 */
public class GuessHistory {
    private List<GuessEntry> entries;

    public GuessHistory() {
        this.entries = new ArrayList<>();
    }

    public void addGuess(String guess, String result) {
        entries.add(new GuessEntry(guess, result));
    }

    public List<GuessEntry> getEntries() {
        return entries;
    }

    public static class GuessEntry {
        private final String guess;
        private final String result;

        public GuessEntry(String guess, String result) {
            this.guess = guess;
            this.result = result;
        }

        public String getGuess() {
            return guess;
        }

        public String getResult() {
            return result;
        }
    }
}