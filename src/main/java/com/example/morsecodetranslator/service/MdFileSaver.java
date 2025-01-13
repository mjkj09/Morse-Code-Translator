package com.example.morsecodetranslator.service;

import java.io.File;
import java.io.FileWriter;

/**
 * Saves translations to a Markdown (.md) file.
 */
public class MdFileSaver implements ITranslationFileSaver {

    /**
     * Saves the input and translated output to a file in Markdown format.
     *
     * @param input  the original text
     * @param output the translated text
     * @param file   the file to save to
     * @throws Exception if an error occurs during file writing
     */
    @Override
    public void saveTranslation(String input, String output, File file) throws Exception {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("# Morse Translation\n\n");
            writer.write("**Input:**\n```\n" + input + "\n```\n\n");
            writer.write("**Output:**\n```\n" + output + "\n```\n");
        }
    }
}
