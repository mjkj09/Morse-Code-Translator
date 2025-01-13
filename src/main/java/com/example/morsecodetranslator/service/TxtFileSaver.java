package com.example.morsecodetranslator.service;

import java.io.File;
import java.io.FileWriter;

public class TxtFileSaver implements ITranslationFileSaver {
    @Override
    public void saveTranslation(String input, String output, File file) throws Exception {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("INPUT:\n" + input + "\n\n");
            writer.write("OUTPUT:\n" + output + "\n");
        }
    }
}
