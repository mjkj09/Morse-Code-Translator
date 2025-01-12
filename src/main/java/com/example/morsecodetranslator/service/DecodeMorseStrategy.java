package com.example.morsecodetranslator.service;

import com.example.morsecodetranslator.model.MorseMappingSingleton;

import java.util.Map;

public class DecodeMorseStrategy implements ITranslatorStrategy {
    @Override
    public String translate(String input) {
        String tmp = input.replaceAll("\\r\\n", "\n");

        tmp = tmp.replaceAll("\\s+", " ");

        tmp = tmp.replaceAll("(\\s*/\\s*)+", " / ");

        tmp = tmp.replaceAll("\\s+", " ");

        tmp = tmp.trim();

        String[] tokens = tmp.split("\\s+");

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
}
