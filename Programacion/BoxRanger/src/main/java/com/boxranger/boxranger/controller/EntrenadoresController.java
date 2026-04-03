package com.boxranger.boxranger.controller;

import com.boxranger.boxranger.database.EntrenadorDAO;
import com.boxranger.boxranger.modelo.Entrenador;
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
 * Controlador de la pantalla de visualización de entrenadores.
 * Esta pantalla es de solo lectura — permite consultar los entrenadores
 * del box pero no modificarlos, ya que es información gestionada internamente.
 */
public class EntrenadoresController {

    // Tabla y columnas de entrenadores
    @FXML private TableView<Entrenador> tablaEntrenadores;
    @FXML private TableColumn<Entrenador, Integer> colIdEntrenador;
    @FXML private TableColumn<Entrenador, String> colNombre;
    @FXML private TableColumn<Entrenador, String> colApellidos;
    @FXML private TableColumn<Entrenador, String> colEspecialidad;
    @FXML private TableColumn<Entrenador, String> colDNI;
    @FXML private TableColumn<Entrenador, String> colEmail;
    @FXML private TableColumn<Entrenador, String> colTelefono;

    /** DAO para acceder a los datos de entrenadores en la base de datos */
    private final EntrenadorDAO entrenadorDAO = new EntrenadorDAO();

    /**
     * Metodo de inicialización que se ejecuta automáticamente al cargar la pantalla.
     * Configura las columnas de la tabla y carga los datos desde la base de datos.
     */
    @FXML
    public void initialize() {
        // Configura cada columna con el campo correspondiente del objeto Entrenador
        colIdEntrenador.setCellValueFactory(data ->
                new javafx.beans.property.SimpleIntegerProperty(
                        data.getValue().getIdEntrenador()).asObject());

        colNombre.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getNombre()));

        colApellidos.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getApellidos()));

        colEspecialidad.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getEspecialidad()));

        colDNI.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getDNI()));

        colEmail.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getEmail()));

        colTelefono.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getTelefono()));

        cargarTabla();
    }

    /**
     * Carga todos los entrenadores desde la base de datos y los muestra en la tabla.
     * Si hay un error de base de datos, muestra un alert con el motivo.
     */
    private void cargarTabla() {
        try {
            ObservableList<Entrenador> lista =
                    FXCollections.observableArrayList(entrenadorDAO.listarEntrenadores());
            tablaEntrenadores.setItems(lista);
        } catch (RuntimeException e) {
            // Platform.runLater para no bloquear el FXMLLoader si el error ocurre durante initialize()
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("No se pudieron cargar los entrenadores");
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
    void volverAlMenu(ActionEvent event) {
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
