package com.example.morsecodetranslator.service;

import java.io.File;
import java.io.FileWriter;

/**
 * Saves translations to a CSV file with input and output as semicolon-separated values.
 */
public class CsvFileSaver implements ITranslationFileSaver {

    /**
     * Saves the input and translated output to the specified CSV file.
     *
     * @param input  the original text
     * @param output the translated text
     * @param file   the file to save the translation in
     * @throws Exception if an error occurs while writing to the file
     */
    @Override
    public void saveTranslation(String input, String output, File file) throws Exception {
        try (FileWriter writer = new FileWriter(file)) {
            // Write CSV header
            writer.write("input;output\n");
            // Write input and output in double quotes to handle special characters
            writer.write("\"" + input + "\";\"" + output + "\"\n");
        }
    }
}
