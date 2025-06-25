package com.sample.qwords.repository;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

// TODO: Upgrade from java v1.8 to 17
// Comprehensive tests for WordList class
public class WordListTest {
    
    private WordList wordList;
    private File tempFile;
    
    @Before
    public void setUp() throws IOException {
        wordList = new WordList();
        // Create temporary file for testing
        tempFile = File.createTempFile("test_words", ".txt");
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("SAMPLE\n");
            writer.write("TESTED\n");
            writer.write("WORDLE\n");
        }
    }
    
    @After
    public void tearDown() {
        if (tempFile != null && tempFile.exists()) {
            tempFile.delete();
        }
    }
    
    @Test
    public void testAddWord() {
        wordList.clearWordList();
        wordList.addWord("QWORDS");
        assertTrue("Word should be added", wordList.containsWord("QWORDS"));
    }
    
    @Test
    public void testAddDuplicateWord() {
        wordList.clearWordList();
        wordList.addWord("QWORDS");
        wordList.addWord("QWORDS");
        assertEquals("Should contain only one instance", 1, wordList.getWordList().size());
    }
    
    @Test
    public void testContainsWord() {
        wordList.clearWordList();
        wordList.addWord("QWORDS");
        assertTrue("Should contain the word", wordList.containsWord("QWORDS"));
        assertFalse("Should not contain non-existent word", wordList.containsWord("ABSENT"));
    }
    
    @Test
    public void testRemoveWord() {
        wordList.clearWordList();
        wordList.addWord("QWORDS");
        assertTrue("Word should be removed", wordList.removeWord("QWORDS"));
        assertFalse("Word should no longer exist", wordList.containsWord("QWORDS"));
    }
    
    @Test
    public void testGetWordsByLength() {
        wordList.clearWordList();
        wordList.addWord("WORD");
        wordList.addWord("QWORDS");
        wordList.addWord("TEST");
        
        List<String> fourLetterWords = wordList.getWordsByLength(4);
        assertEquals("Should find 2 four-letter words", 2, fourLetterWords.size());
        
        List<String> sixLetterWords = wordList.getWordsByLength(6);
        assertEquals("Should find 1 six-letter word", 1, sixLetterWords.size());
    }
    
    @Test
    public void testGetRandomWord() throws IOException {
        // Use a fresh WordList to avoid interference from classpath loading
        WordList freshWordList = new WordList(tempFile.getAbsolutePath());
        freshWordList.clearWordList();
        freshWordList.addWord("QWORDS");
        
        String randomWord = freshWordList.getRandomWord();
        assertEquals("Should return the only word", "QWORDS", randomWord);
    }
    
    @Test
    public void testLoadFromFile() throws IOException {
        WordList fileWordList = new WordList(tempFile.getAbsolutePath());
        assertTrue("Should contain SAMPLE", fileWordList.containsWord("SAMPLE"));
        assertTrue("Should contain TESTED", fileWordList.containsWord("TESTED"));
        assertTrue("Should contain WORDLE", fileWordList.containsWord("WORDLE"));
    }
    
    @Test
    public void testSaveToFile() throws IOException {
        wordList.clearWordList();
        wordList.addWord("QWORDS");
        wordList.addWord("TESTED");
        
        File saveFile = File.createTempFile("save_test", ".txt");
        try {
            wordList.saveWordListToFile(saveFile.getAbsolutePath());
            
            WordList loadedWordList = new WordList(saveFile.getAbsolutePath());
            assertTrue("Should contain saved words", loadedWordList.containsWord("QWORDS"));
            assertTrue("Should contain saved words", loadedWordList.containsWord("TESTED"));
        } finally {
            saveFile.delete();
        }
    }
    
    @Test
    public void testShuffleWordList() {
        wordList.clearWordList();
        for (int i = 0; i < 10; i++) {
            wordList.addWord("WORD" + i);
        }
        
        List<String> originalOrder = wordList.getWordList();
        wordList.shuffleWordList();
        List<String> shuffledOrder = wordList.getWordList();
        
        assertEquals("Size should remain same", originalOrder.size(), shuffledOrder.size());
        // Note: There's a small chance they could be the same after shuffle
    }
    
    @Test
    public void testGetLastWord() throws IOException {
        // Create empty file for clean test
        File emptyFile = File.createTempFile("empty_test", ".txt");
        try {
            WordList freshWordList = new WordList(emptyFile.getAbsolutePath());
            freshWordList.addWord("FIRST");
            freshWordList.addWord("SECOND");
            
            // Since constructor already called getRandomWord(), lastWord might not be null
            // Test the behavior: call getRandomWord() and check if lastWord gets updated
            String firstCall = freshWordList.getRandomWord();
            String lastWordAfterFirst = freshWordList.getLastWord();
            
            String secondCall = freshWordList.getRandomWord();
            String lastWordAfterSecond = freshWordList.getLastWord();
            
            // After second call, lastWord should be the result of first call
            assertEquals("Last word should be the previous random word", firstCall, lastWordAfterSecond);
        } finally {
            emptyFile.delete();
        }
    }
}
