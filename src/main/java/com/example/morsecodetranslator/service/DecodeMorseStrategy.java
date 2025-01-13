package com.example.morsecodetranslator.service;

import com.example.morsecodetranslator.model.MorseMappingSingleton;

import java.util.Map;

/**
 * A strategy for decoding Morse code into plain text.
 * Implements the {@link ITranslatorStrategy} interface.
 */
public class DecodeMorseStrategy implements ITranslatorStrategy {

    /**
     * Decodes a given Morse code string into plain text.
     *
     * <p>Normalizes the input by:
     * <ul>
     *   <li>Replacing line breaks with a single newline.</li>
     *   <li>Collapsing multiple spaces into one.</li>
     *   <li>Treating slashes (`/`) as word separators.</li>
     * </ul>
     * Unrecognized tokens are replaced with a question mark (`?`).</p>
     *
     * @param input the Morse code to decode
     * @return the decoded text
     */
    @Override
    public String translate(String input) {
        if (input.trim().isEmpty()) {
            return "";
        }

        String[] tokens = getStrings(input);
        Map<String, Character> morseToChar =
                MorseMappingSingleton.getInstance().getMorseToCharMap();

        StringBuilder sb = new StringBuilder();
        for (String token : tokens) {
            if (token.equals("/")) {
                sb.append(' ');
            } else if (morseToChar.containsKey(token)) {
                sb.append(morseToChar.get(token));
            } else {
                sb.append('?');
            }
        }

        return sb.toString();
    }

    private static String[] getStrings(String input) {
        String tmp = input.replaceAll("\\r\\n", "\n");
        tmp = tmp.replaceAll("\\s+", " ");
        tmp = tmp.replaceAll("(\\s*/\\s*)+", " / ");
        tmp = tmp.trim();

        return tmp.split("\\s+");
    }
}
