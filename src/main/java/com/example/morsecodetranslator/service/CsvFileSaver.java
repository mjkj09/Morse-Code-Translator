package com.example.morsecodetranslator.service;

import java.io.File;
import java.io.FileWriter;

public class CsvFileSaver implements ITranslationFileSaver {
    @Override
    public void saveTranslation(String input, String output, File file) throws Exception {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("input;output\n");
            // Ewentualnie użyć cudzysłowów, by uniknąć problemów z przecinkami
            writer.write("\"" + input + "\";\"" + output + "\"\n");
        }
    }
}
