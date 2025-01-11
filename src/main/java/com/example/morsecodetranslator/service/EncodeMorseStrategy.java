package com.example.morsecodetranslator.service;

import java.util.HashMap;
import java.util.Map;

public class EncodeMorseStrategy implements ITranslatorStrategy {

    @Override
    public String translate(String input) {
        Map<Character, String> charToMorse = getCharToMorseMap();

        StringBuilder sb = new StringBuilder();
        for (char c : input.toUpperCase().toCharArray()) {
            if (charToMorse.containsKey(c)) {
                sb.append(charToMorse.get(c)).append(" ");
            } else {
                sb.append("? ");
            }
        }
        return sb.toString().trim();
    }

    private Map<Character, String> getCharToMorseMap() {
        Map<Character, String> map = new HashMap<>();
        map.put('A', "•-");
        map.put('B', "-•••");
        map.put('C', "-•-•");
        map.put('D', "-••");
        map.put('E', "•");
        map.put('F', "••-•");
        map.put('G', "--•");
        map.put('H', "••••");
        map.put(' ', "/");
        // ...

        return map;
    }
}
