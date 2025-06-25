package com.sample.qwords.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class GuessHistoryTest {

    @Test
    public void testAddGuess() {
        GuessHistory history = new GuessHistory();
        history.addGuess("SPRING", "+?+X++");
        
        assertEquals(1, history.getEntries().size());
        assertEquals("SPRING", history.getEntries().get(0).getGuess());
        assertEquals("+?+X++", history.getEntries().get(0).getResult());
    }

    @Test
    public void testMultipleGuesses() {
        GuessHistory history = new GuessHistory();
        history.addGuess("SPRING", "+?+X++");
        history.addGuess("STRONG", "++?X++");
        
        assertEquals(2, history.getEntries().size());
        assertEquals("SPRING", history.getEntries().get(0).getGuess());
        assertEquals("STRONG", history.getEntries().get(1).getGuess());
    }

    @Test
    public void testEmptyHistory() {
        GuessHistory history = new GuessHistory();
        assertTrue(history.getEntries().isEmpty());
    }
}