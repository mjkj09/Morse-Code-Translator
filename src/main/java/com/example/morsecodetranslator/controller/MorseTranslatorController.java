package com.example.morsecodetranslator.controller;

import com.example.morsecodetranslator.model.MorseTranslator;
import com.example.morsecodetranslator.service.DecodeMorseStrategy;
import com.example.morsecodetranslator.service.EncodeMorseStrategy;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;

public class MorseTranslatorController {

    @FXML
    private TextArea inputTextArea;

    @FXML
    private TextArea outputTextArea;

    @FXML
    private ToggleGroup strategyToggleGroup;

    @FXML
    private RadioButton encodeRadioBtn;

    @FXML
    private RadioButton decodeRadioBtn;

    private final MorseTranslator translator = new MorseTranslator();

    @FXML
    public void onTranslateButtonClick() {
        if (encodeRadioBtn.isSelected()) {
            translator.setStrategy(new EncodeMorseStrategy());
        } else if (decodeRadioBtn.isSelected()) {
            translator.setStrategy(new DecodeMorseStrategy());
        }

        String input = inputTextArea.getText();

        String result = translator.translate(input);

        outputTextArea.setText(result);
    }
}
