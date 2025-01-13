package com.example.morsecodetranslator.service;

import java.io.File;
import java.io.FileWriter;

public class MdFileSaver implements ITranslationFileSaver {
    @Override
    public void saveTranslation(String input, String output, File file) throws Exception {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("# Morse Translation\n\n");
            writer.write("**Input:**\n```\n" + input + "\n```\n\n");
            writer.write("**Output:**\n```\n" + output + "\n```\n");
        }
    }
}
