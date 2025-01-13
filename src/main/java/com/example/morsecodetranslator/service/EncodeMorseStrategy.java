package com.example.morsecodetranslator.service;

import com.example.morsecodetranslator.model.MorseMappingSingleton;

import java.util.Map;

/**
 * Implements the {@link ITranslatorStrategy} interface
 * for encoding plain text into Morse code.
 */
public class EncodeMorseStrategy implements ITranslatorStrategy {

    /**
     * Encodes plain text into Morse code.
     *
     * <p>Normalizes the input by:
     * <ul>
     *   <li>Trimming leading and trailing whitespace.</li>
     *   <li>Collapsing multiple spaces into one.</li>
     *   <li>Converting text to uppercase.</li>
     * </ul>
     * Each character is then converted to its Morse code equivalent.
     * Unrecognized characters are replaced with a question mark (`?`).</p>
     *
     * @param input the plain text to encode
     * @return the encoded text in Morse code
     */
    @Override
    public String translate(String input) {
        if (input.trim().isEmpty()) {
            return "";
        }

        Map<Character, String> charToMorse =
                MorseMappingSingleton.getInstance().getCharToMorseMap();

        String normalized = input.trim().replaceAll("\\s+", " ").toUpperCase();
        StringBuilder sb = new StringBuilder();
        for (char c : normalized.toCharArray()) {
            sb.append(charToMorse.getOrDefault(c, "?")).append(" ");
        }
        return sb.toString().trim();
    }
}
