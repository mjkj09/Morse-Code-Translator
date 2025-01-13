package com.example.morsecodetranslator.controller;

import com.example.morsecodetranslator.model.MorseTranslator;
import com.example.morsecodetranslator.model.ObservedSubject;
import com.example.morsecodetranslator.service.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign.MaterialDesign;

import java.io.File;

public class MorseTranslatorController implements IObserver {

    @FXML
    private TextArea inputTextArea;

    @FXML
    private TextArea outputTextArea;

    @FXML
    private RadioButton encodeRadioBtn;

    @FXML
    private RadioButton decodeRadioBtn;

    @FXML
    private Button saveButton;

    @FXML
    private ComboBox<String> extensionComboBox;

    @FXML
    private TextField filenameField;

    private final MorseTranslator translator = new MorseTranslator();
    private final ObservedSubject subject = new ObservedSubject();

    @FXML
    public void initialize() {
        ToggleGroup strategyToggleGroup = new ToggleGroup();
        encodeRadioBtn.setToggleGroup(strategyToggleGroup);
        decodeRadioBtn.setToggleGroup(strategyToggleGroup);
        encodeRadioBtn.setSelected(true);

        subject.addObserver(this);
        inputTextArea.setOnKeyTyped(e -> subject.setState(inputTextArea.getText()));
        encodeRadioBtn.setOnAction(e -> subject.setState(inputTextArea.getText()));
        decodeRadioBtn.setOnAction(e -> subject.setState(inputTextArea.getText()));

        saveButton.setGraphic(new FontIcon(MaterialDesign.MDI_CONTENT_SAVE));

        extensionComboBox.getItems().addAll("txt", "csv", "md");
        extensionComboBox.setValue("txt");
    }

    @FXML
    public void onSaveButtonClick() {
        try {
            String input = inputTextArea.getText();
            String output = outputTextArea.getText();

            String extension = extensionComboBox.getValue();
            String filename = filenameField.getText();

            if (filename == null || filename.trim().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setResizable(true);
                alert.setTitle("File Name Error");
                alert.setHeaderText("No file name provided");
                alert.setContentText("Please enter a valid file name before saving.");
                alert.showAndWait();
                return;
            }

            ITranslationFileSaver saver = TranslationFileSaverFactory.getSaver(extension);

            File file = new File("src/main/java/com/example/morsecodetranslator/saved/"
                    + filename + "." + extension);
            saver.saveTranslation(input, output, file);

            System.out.println("Saved to " + file.getAbsolutePath());
            Alert success = new Alert(Alert.AlertType.INFORMATION);
            success.setResizable(true);
            success.setTitle("Saved");
            success.setHeaderText("File saved successfully");
            success.setContentText("File: " + file.getAbsolutePath());
            success.showAndWait();

        } catch (Exception e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setResizable(true);
            errorAlert.setTitle("Saving Error");
            errorAlert.setHeaderText("Could not save file");
            errorAlert.setContentText(e.getMessage());
            errorAlert.showAndWait();

            System.err.println("Error saving: "
                    + e.getMessage());
            throw new RuntimeException(e);
        }
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
