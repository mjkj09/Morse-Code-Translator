package com.example.morsecodetranslator.service;

import java.util.HashMap;
import java.util.Map;

public class DecodeMorseStrategy implements ITranslatorStrategy {

    @Override
    public String translate(String input) {
        String[] morseTokens = input.trim().split("\\s+");
        Map<String, Character> morseToChar = getMorseToCharMap();

        StringBuilder sb = new StringBuilder();
        for (String token : morseTokens) {
            if (morseToChar.containsKey(token)) {
                sb.append(morseToChar.get(token));
            } else {
                sb.append('?');
            }
        }
        return sb.toString();
    }

    private Map<String, Character> getMorseToCharMap() {
        Map<String, Character> map = new HashMap<>();
        map.put("•-", 'A');
        map.put("-•••", 'B');
        map.put("-•-•", 'C');
        map.put("-••", 'D');
        map.put("•", 'E');
        map.put("••-•", 'F');
        map.put("--•", 'G');
        map.put("••••", 'H');
        map.put("/", ' ');
        // ...

        return map;
    }
}
