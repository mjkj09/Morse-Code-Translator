package com.example.morsecodetranslator.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class MorseMappingSingleton {

    private static volatile MorseMappingSingleton instance;

    private final Map<Character, String> charToMorse = new HashMap<>();
    private final Map<String, Character> morseToChar = new HashMap<>();

    private MorseMappingSingleton() {
        loadMappingsFromJson();
    }

    public static MorseMappingSingleton getInstance() {
        if (instance == null) {
            synchronized (MorseMappingSingleton.class) {
                if (instance == null) {
                    instance = new MorseMappingSingleton();
                }
            }
        }
        return instance;
    }

    public Map<Character, String> getCharToMorseMap() {
        return charToMorse;
    }

    public Map<String, Character> getMorseToCharMap() {
        return morseToChar;
    }

    private void loadMappingsFromJson() {
        try (InputStream is = getClass().getResourceAsStream(
                "/com/example/morsecodetranslator/config/morse-mapping.json")) {
            if (is == null) {
                throw new IllegalStateException(
                        "Could not find morse-mapping.json file"
                );
            }

            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> rawMap = mapper.readValue(
                    is, new TypeReference<>() {
                    }
            );

            for (Map.Entry<String, String> entry : rawMap.entrySet()) {
                String key = entry.getKey();
                String morse = entry.getValue();
                char c = key.charAt(0);
                charToMorse.put(c, morse);
                morseToChar.put(morse, c);
            }

        } catch (Exception e) {
            System.err.println("Error loading morse-mapping.json: "
                    + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
