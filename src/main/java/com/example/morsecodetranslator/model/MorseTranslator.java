package com.example.morsecodetranslator.model;

import com.example.morsecodetranslator.service.ITranslatorStrategy;

/**
 * A context class in the Strategy design pattern.
 * Delegates translation tasks to the selected strategy.
 */
public class MorseTranslator {

    /**
     * The current translation strategy.
     * Determines how the input is processed (e.g., encode or decode).
     */
    private ITranslatorStrategy strategy;

    /**
     * Sets the translation strategy to use.
     *
     * @param strategy the translation strategy
     */
    public void setStrategy(ITranslatorStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Translates the given input using the selected strategy.
     *
     * @param input the text to translate
     * @return the translated output
     * @throws IllegalStateException if no strategy is set
     */
    public String translate(String input) {
        if (strategy == null) {
            throw new IllegalStateException("Strategy not set");
        }

        return strategy.translate(input);
    }
}
