package com.example.morsecodetranslator.controller;

import com.example.morsecodetranslator.model.MorseTranslator;
import com.example.morsecodetranslator.model.ObservedSubject;
import com.example.morsecodetranslator.service.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign.MaterialDesign;

import java.io.File;

/**
 * Controller for the Morse Code Translator UI.
 * Handles user interactions and updates the translation output based on input and settings.
 */
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

    /**
     * Initializes the controller, sets up event handlers, and configures UI components.
     */
    @FXML
    public void initialize() {
        ToggleGroup strategyToggleGroup = new ToggleGroup();
        encodeRadioBtn.setToggleGroup(strategyToggleGroup);
        decodeRadioBtn.setToggleGroup(strategyToggleGroup);
        encodeRadioBtn.setSelected(true);

        subject.addObserver(this);
        inputTextArea.setOnKeyTyped(event -> subject.setState(inputTextArea.getText()));
        encodeRadioBtn.setOnAction(event -> subject.setState(inputTextArea.getText()));
        decodeRadioBtn.setOnAction(event -> subject.setState(inputTextArea.getText()));

        saveButton.setGraphic(new FontIcon(MaterialDesign.MDI_CONTENT_SAVE));
        extensionComboBox.getItems().addAll("txt", "csv", "md");
        extensionComboBox.setValue("txt");
    }

    /**
     * Saves the translation to a file when the Save button is clicked.
     * Validates the file name and uses the selected file format.
     */
    @FXML
    public void onSaveButtonClick() {
        try {
            String input = inputTextArea.getText();
            String output = outputTextArea.getText();
            String extension = extensionComboBox.getValue();
            String filename = filenameField.getText();

            if (filename == null || filename.trim().isEmpty()) {
                showError("File Name Error", "No file name provided", "Please enter a valid file name before saving.");
                return;
            }

            ITranslationFileSaver saver = TranslationFileSaverFactory.getSaver(extension);
            File file = new File("src/main/java/com/example/morsecodetranslator/saved/" + filename + "." + extension);
            saver.saveTranslation(input, output, file);

            showInfo("File path: " + file.getAbsolutePath());
        } catch (Exception e) {
            showError("Saving Error", "Could not save the file", e.getMessage());
        }
    }

    /**
     * Updates the translation output when the input or strategy changes.
     *
     * @param newValue the updated input text
     */
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

    /**
     * Displays an error alert dialog with the provided title, header, and message.
     */
    private void showError(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.setResizable(true);
        alert.showAndWait();
    }

    /**
     * Displays an information alert dialog confirming a successful file save.
     */
    private void showInfo(String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("File Saved");
        alert.setHeaderText("File saved successfully");
        alert.setContentText(content);
        alert.setResizable(true);
        alert.showAndWait();
    }
}
