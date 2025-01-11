package com.example.morsecodetranslator.model;

import com.example.morsecodetranslator.service.ITranslatorStrategy;

public class MorseTranslator {
    private ITranslatorStrategy strategy;

    public void setStrategy(ITranslatorStrategy strategy) {
        this.strategy = strategy;
    }

    public String translate(String input) {
        if (strategy == null) {
            throw new IllegalStateException("Strategy not set");
        }

        return strategy.translate(input);
    }
}
