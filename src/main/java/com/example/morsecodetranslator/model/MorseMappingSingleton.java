package com.example.morsecodetranslator.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Singleton class for managing mappings between characters and their Morse code equivalents.
 * Loads mappings from a JSON configuration file and provides thread-safe access.
 */
public class MorseMappingSingleton {

    // Singleton instance
    private static volatile MorseMappingSingleton instance;

    // Character-to-Morse mapping
    private final Map<Character, String> charToMorse = new HashMap<>();

    // Morse-to-character mapping
    private final Map<String, Character> morseToChar = new HashMap<>();

    /**
     * Private constructor to load mappings from a JSON file.
     */
    private MorseMappingSingleton() {
        loadMappingsFromJson();
    }

    /**
     * Returns the singleton instance of this class.
     *
     * @return the instance of {@code MorseMappingSingleton}
     */
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

    /**
     * Gets the character-to-Morse code mapping.
     *
     * @return a map of characters to Morse code
     */
    public Map<Character, String> getCharToMorseMap() {
        return charToMorse;
    }

    /**
     * Gets the Morse code-to-character mapping.
     *
     * @return a map of Morse code to characters
     */
    public Map<String, Character> getMorseToCharMap() {
        return morseToChar;
    }

    /**
     * Loads mappings from a JSON file located in the resources directory.
     * Populates both character-to-Morse and Morse-to-character maps.
     */
    private void loadMappingsFromJson() {
        try (InputStream is = getClass().getResourceAsStream(
                "/com/example/morsecodetranslator/config/morse-mapping.json")) {
            if (is == null) {
                throw new IllegalStateException("morse-mapping.json file not found");
            }

            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> rawMap = mapper.readValue(is, new TypeReference<>() {});

            for (Map.Entry<String, String> entry : rawMap.entrySet()) {
                char c = entry.getKey().charAt(0);
                String morse = entry.getValue();
                charToMorse.put(c, morse);
                morseToChar.put(morse, c);
            }
        } catch (Exception e) {
            System.err.println("Error loading morse-mapping.json: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
