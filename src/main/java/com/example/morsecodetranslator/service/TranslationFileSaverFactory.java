package com.example.morsecodetranslator.service;

public class TranslationFileSaverFactory {

    public static ITranslationFileSaver getSaver(String extension) {
        return switch (extension.toLowerCase()) {
            case "txt" -> new TxtFileSaver();
            case "csv" -> new CsvFileSaver();
            case "md" -> new MdFileSaver();
            default -> throw new IllegalArgumentException("Unsupported file extension: " + extension);
        };
    }
}
