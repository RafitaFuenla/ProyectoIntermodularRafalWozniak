package com.boxranger.boxranger.controller;

import com.boxranger.boxranger.database.ClaseDAO;
import com.boxranger.boxranger.modelo.Clase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.application.Platform;

/**
 * Controlador de la pantalla de visualización de clases.
 * Esta pantalla es de solo lectura — permite consultar las clases
 * del box pero no modificarlas, ya que es información gestionada internamente.
 */
public class ClasesController {

    // Tabla y columnas de clases
    @FXML private TableView<Clase> tablaClases;
    @FXML private TableColumn<Clase, Integer> colIdClase;
    @FXML private TableColumn<Clase, Integer> colIdEntrenador;
    @FXML private TableColumn<Clase, String> colNombre;
    @FXML private TableColumn<Clase, String> colHoraInicio;
    @FXML private TableColumn<Clase, String> colHoraFin;
    @FXML private TableColumn<Clase, Integer> colMaxAtletas;

    /** DAO para acceder a los datos de clases en la base de datos */
    private final ClaseDAO claseDAO = new ClaseDAO();

    /**
     * Método de inicialización que se ejecuta automáticamente al cargar la pantalla.
     * Configura las columnas de la tabla y carga los datos desde la base de datos.
     */
    @FXML
    public void initialize() {
        // Configura cada columna con el campo correspondiente del objeto Clase
        colIdClase.setCellValueFactory(data ->
                new javafx.beans.property.SimpleIntegerProperty(
                        data.getValue().getIdClase()).asObject());

        colIdEntrenador.setCellValueFactory(data ->
                new javafx.beans.property.SimpleIntegerProperty(
                        data.getValue().getIdEntrenador()).asObject());

        colNombre.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getNombre()));

        colHoraInicio.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getHora_inicio()));

        colHoraFin.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getHora_fin()));

        colMaxAtletas.setCellValueFactory(data ->
                new javafx.beans.property.SimpleIntegerProperty(
                        data.getValue().getMax_atletas()).asObject());

        cargarTabla();
    }

    /**
     * Carga todas las clases desde la base de datos y las muestra en la tabla.
     * Si hay un error de base de datos, muestra un alert con el motivo.
     */
    private void cargarTabla() {
        try {
            ObservableList<Clase> lista =
                    FXCollections.observableArrayList(claseDAO.listarClases());
            tablaClases.setItems(lista);
        } catch (RuntimeException e) {
            // Platform.runLater para no bloquear el FXMLLoader si el error ocurre durante initialize()
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("No se pudieron cargar las clases");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            });
        }
    }

    /**
     * Navega de vuelta a la pantalla principal del menú.
     * @param event evento del botón Menú Principal
     */
    @FXML
    private void volverAlMenu(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/boxranger/boxranger/BoxRanger-view.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se pudo volver al menú");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}
