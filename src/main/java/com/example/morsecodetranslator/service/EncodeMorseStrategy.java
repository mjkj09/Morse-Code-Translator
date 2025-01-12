package com.example.morsecodetranslator.service;

import com.example.morsecodetranslator.model.MorseMappingSingleton;

import java.util.Map;

public class EncodeMorseStrategy implements ITranslatorStrategy {
    @Override
    public String translate(String input) {
        Map<Character, String> charToMorse =
                MorseMappingSingleton.getInstance().getCharToMorseMap();

        String normalized = input.replaceAll("\\s+", " ");
        normalized = normalized.toUpperCase();

        StringBuilder sb = new StringBuilder();
        for (char c : normalized.toCharArray()) {
            if (charToMorse.containsKey(c)) {
                sb.append(charToMorse.get(c)).append(" ");
            } else {
                sb.append("? ");
            }
        }
        return sb.toString().trim();
    }
}
