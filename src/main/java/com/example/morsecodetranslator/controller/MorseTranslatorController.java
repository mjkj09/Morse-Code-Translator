package com.example.morsecodetranslator.controller;

import com.example.morsecodetranslator.model.MorseTranslator;
import com.example.morsecodetranslator.service.IObserver;
import com.example.morsecodetranslator.model.ObservedSubject;
import com.example.morsecodetranslator.service.DecodeMorseStrategy;
import com.example.morsecodetranslator.service.EncodeMorseStrategy;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;

public class MorseTranslatorController implements IObserver {

    @FXML
    private TextArea inputTextArea;

    @FXML
    private TextArea outputTextArea;

    @FXML
    private RadioButton encodeRadioBtn;

    @FXML
    private RadioButton decodeRadioBtn;

    private final MorseTranslator translator = new MorseTranslator();

    private final ObservedSubject subject = new ObservedSubject();

    private void handleAnyChange() {
        subject.setState(inputTextArea.getText());
    }

    @FXML
    public void initialize() {
        ToggleGroup strategyToggleGroup = new ToggleGroup();
        encodeRadioBtn.setToggleGroup(strategyToggleGroup);
        decodeRadioBtn.setToggleGroup(strategyToggleGroup);
        encodeRadioBtn.setSelected(true);

        subject.addObserver(this);

        inputTextArea.setOnKeyTyped(e -> handleAnyChange());
        encodeRadioBtn.setOnAction(e -> handleAnyChange());
        decodeRadioBtn.setOnAction(e -> handleAnyChange());
    }

    @Override
    public void update(String newValue) {
        if (encodeRadioBtn.isSelected()) {
            translator.setStrategy(new EncodeMorseStrategy());
        } else {
            translator.setStrategy(new DecodeMorseStrategy());
        }

        String result = translator.translate(newValue);

        outputTextArea.setText(result);
    }
}
