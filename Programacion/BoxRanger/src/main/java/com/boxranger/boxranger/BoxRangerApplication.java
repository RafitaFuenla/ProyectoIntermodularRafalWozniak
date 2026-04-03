package com.boxranger.boxranger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase principal de la aplicación BoxRanger.
 * Punto de entrada de la aplicación JavaFX.
 * Se encarga de cargar la pantalla principal y mostrar la ventana inicial.
 */
public class BoxRangerApplication extends Application {

    /**
     * Método de inicio de la aplicación JavaFX.
     * Carga la vista principal del menú y configura el Stage inicial.
     * @param stage ventana principal de la aplicación
     * @throws IOException si no se puede cargar el archivo FXML
     */
    @Override
    public void start(Stage stage) throws IOException {
        // Carga la pantalla principal del menú
        FXMLLoader fxmlLoader = new FXMLLoader(
                BoxRangerApplication.class.getResource(
                        "/com/boxranger/boxranger/BoxRanger-view.fxml"));

        // Crea la escena con el tamaño definido para la aplicación
        Scene scene = new Scene(fxmlLoader.load(), 350, 509);

        stage.setTitle("BoxRanger");
        stage.setScene(scene);
        stage.show();
    }
}