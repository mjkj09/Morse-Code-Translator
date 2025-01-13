package com.example.morsecodetranslator.service;

/**
 * Factory class for creating {@link ITranslationFileSaver} instances based on file extension.
 */
public class TranslationFileSaverFactory {

    /**
     * Returns an appropriate {@link ITranslationFileSaver} implementation for the given file extension.
     *
     * @param extension the file extension (e.g., "txt", "csv", "md")
     * @return an instance of {@link ITranslationFileSaver} for the specified extension
     * @throws IllegalArgumentException if the extension is not supported
     */
    public static ITranslationFileSaver getSaver(String extension) {
        return switch (extension.toLowerCase()) {
            case "txt" -> new TxtFileSaver();
            case "csv" -> new CsvFileSaver();
            case "md" -> new MdFileSaver();
            default -> throw new IllegalArgumentException("Unsupported file extension: " + extension);
        };
    }
}
