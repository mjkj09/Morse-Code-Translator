package com.example.morsecodetranslator.service;

import java.io.File;
import java.io.FileWriter;

/**
 * Saves translations to a plain text (.txt) file.
 */
public class TxtFileSaver implements ITranslationFileSaver {

    /**
     * Saves the input and translated output to the specified text file.
     *
     * @param input  the original text
     * @param output the translated text
     * @param file   the file to save the translation in
     * @throws Exception if an error occurs while writing to the file
     */
    @Override
    public void saveTranslation(String input, String output, File file) throws Exception {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("INPUT:\n" + input + "\n\n");
            writer.write("OUTPUT:\n" + output + "\n");
        }
    }
}
