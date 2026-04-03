package com.boxranger.boxranger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controlador de la pantalla principal del menú de BoxRanger.
 * Gestiona la navegación entre las diferentes secciones de la aplicación:
 * Socios, Entrenadores, Clases y Pagos.
 * Cada botón del menú carga la vista correspondiente en el mismo Stage.
 */
public class BoxRangerController {

    /**
     * Navega a la pantalla de gestión de socios.
     * @param event evento del botón Socios
     */
    @FXML
    private void abrirSocios(ActionEvent event) {
        navegarA("/com/boxranger/boxranger/Socios-view.fxml", event);
    }

    /**
     * Navega a la pantalla de visualización de entrenadores.
     * @param event evento del botón Entrenadores
     */
    @FXML
    private void abrirEntrenadores(ActionEvent event) {
        navegarA("/com/boxranger/boxranger/Entrenadores-view.fxml", event);
    }

    /**
     * Navega a la pantalla de visualización de clases.
     * @param event evento del botón Clases
     */
    @FXML
    private void abrirClases(ActionEvent event) {
        navegarA("/com/boxranger/boxranger/Clases-view.fxml", event);
    }

    /**
     * Navega a la pantalla de visualización de pagos.
     * @param event evento del botón Pagos
     */
    @FXML
    private void abrirPagos(ActionEvent event) {
        navegarA("/com/boxranger/boxranger/Pagos-view.fxml", event);
    }

    /**
     * Metodo auxiliar que centraliza la lógica de navegación entre pantallas.
     * Carga el FXML indicado y lo muestra en el Stage actual.
     * @param ruta ruta del archivo FXML a cargar
     * @param event evento del botón que origina la navegación
     */
    private void navegarA(String ruta, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ruta));
            Parent root = loader.load();
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se pudo abrir la ventana");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}