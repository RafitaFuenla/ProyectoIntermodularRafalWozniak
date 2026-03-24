package com.boxranger.boxranger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BoxRangerApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BoxRangerApplication.class.getResource("/com/boxranger/boxranger/BoxRanger-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 350, 509);
        stage.setTitle("BoxRanger");
        stage.setScene(scene);
        stage.show();
    }
}
