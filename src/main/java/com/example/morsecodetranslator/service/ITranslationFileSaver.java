package com.example.morsecodetranslator.service;

import java.io.File;

/**
 * Interface for saving translations to a file.
 */
public interface ITranslationFileSaver {

    /**
     * Saves the input text and its translation to the specified file.
     *
     * @param input  the original text
     * @param output the translated text
     * @param file   the file to save the translation in
     * @throws Exception if an error occurs while saving the file
     */
    void saveTranslation(String input, String output, File file) throws Exception;
}
