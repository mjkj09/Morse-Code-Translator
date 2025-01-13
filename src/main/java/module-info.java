module com.example.morsecodetranslator {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.materialdesign;
    requires org.kordamp.bootstrapfx.core;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    exports com.example.morsecodetranslator.application;
    opens com.example.morsecodetranslator.application to javafx.fxml;
    exports com.example.morsecodetranslator.controller;
    opens com.example.morsecodetranslator.controller to javafx.fxml;
    exports com.example.morsecodetranslator.service;
    opens com.example.morsecodetranslator.service to javafx.fxml;
    exports com.example.morsecodetranslator.model;
    opens com.example.morsecodetranslator.model to javafx.fxml;
}
