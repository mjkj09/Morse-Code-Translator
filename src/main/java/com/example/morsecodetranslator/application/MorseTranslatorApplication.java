package com.example.morsecodetranslator.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MorseTranslatorApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                MorseTranslatorApplication.class.getResource(
                        "/com/example/morsecodetranslator/view/morse-translator-view.fxml"
                )
        );
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Morse Code Translator");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
