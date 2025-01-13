package com.example.morsecodetranslator.service;

import java.io.File;

public interface ITranslationFileSaver {
    void saveTranslation(String input, String output, File file) throws Exception;
}
