package com.boxranger.boxranger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class BoxRangerController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
