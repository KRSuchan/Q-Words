package com.sample.qwords.controller;

import com.sample.qwords.model.GuessHistory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sample.qwords.model.GameStatus;
import com.sample.qwords.model.Word;
import com.sample.qwords.service.WordSelectionService;

import io.micrometer.core.lang.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A Spring MVC controller class that handles the game logic and user
 * interactions.
 */
@Controller
@SessionAttributes({"word", "attempts", "guessHistory"})
public class GameController {

    private static final Logger log = LoggerFactory.getLogger(GameController.class);
    private final WordSelectionService wordBank;

    public GameController(WordSelectionService wordBank) {
        this.wordBank = wordBank;
    }

    @GetMapping("/game")
    public String index(@RequestParam String user, Model model) {
        wordBank.selectNewWord();
        Word word = new Word(wordBank.getWord());
        // Log the current word
        log.info("Current word: {}", word.getWord());
        int attempts = 0;

        String result = "";
        GuessHistory guessHistory = new GuessHistory();

        // Set View Attributes
        model.addAttribute("word", word.getWord());
        model.addAttribute("message", "Make your first guess!");
        model.addAttribute("result", result);
        model.addAttribute("status", GameStatus.INPROGRESS);
        model.addAttribute("user", user);
        model.addAttribute("attempts", attempts);
        model.addAttribute("guessHistory", guessHistory);
        return "game";
    }

    /**
     * Process a guess attempt for the game
     */
    @PostMapping("/game")
    public String makeGuess(String guess, Model model, String user) {
        Word word = new Word((String)model.getAttribute("word"));
        String selectedWord = word.getWord();
        int attempts = getAttempts(model);
        String result = word.getInfo(guess);
        GuessHistory guessHistory = (GuessHistory) model.getAttribute("guessHistory");
        
        log.info("User {} made a guess: {}", user, guess);
        log.info("Guess: {}, model: {}, user: {}", guess, model, user);
        
        // Add the guess to history
        guessHistory.addGuess(guess, result);
        attempts = addAttempt(attempts);
        
        model.addAttribute("user", user);
        model.addAttribute("guess", guess);
        model.addAttribute("result", result);
        model.addAttribute("attempts", attempts);
        model.addAttribute("guessHistory", guessHistory);

        if (guess.equalsIgnoreCase(selectedWord)) {
            model.addAttribute("status", GameStatus.SUCCESS);
            model.addAttribute("message", "Congratulations, you guessed the word!");
            return "game";
        }

        if (attempts >= 6) {
            model.addAttribute("status", GameStatus.FAILED);
            model.addAttribute("message", "Game over, you ran out of attempts!");
        }else {
            model.addAttribute("status", GameStatus.INPROGRESS);
            model.addAttribute("message", "Try again!");
            
        }

        return "game";
    }

    /**
     * Get the number of attempts from the model
     */
    private int getAttempts(Model model) {
        Integer attempts = (Integer) model.getAttribute("attempts");
        return (attempts != null) ? attempts : 0;
    }

    private int addAttempt(@Nullable Integer attempt) {
        return (attempt != null) ? attempt + 1 : 1;
    }
}