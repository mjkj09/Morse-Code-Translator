package com.example.morsecodetranslator.service;

/**
 * An interface for implementing different translation strategies,
 * such as converting text to Morse code or other formats.
 */
public interface ITranslatorStrategy {

    /**
     * Translates the input text using the chosen strategy.
     *
     * @param input the text to translate
     * @return the translated text
     */
    String translate(String input);
}
