package com.boxranger.boxranger.controller;

import com.boxranger.boxranger.database.EntrenadorDAO;
import com.boxranger.boxranger.modelo.Entrenador;
import com.boxranger.boxranger.modelo.Socio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;


import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;



public class EntrenadoresController {
    @FXML private TableView<Entrenador> tablaEntrenadores;
    @FXML private TableColumn<Entrenador, Integer> colIdEntrenador;
    @FXML private TableColumn<Entrenador, String> colNombre;
    @FXML private TableColumn<Entrenador, String> colApellidos;
    @FXML private TableColumn<Entrenador, String> colEspecialidad;
    @FXML private TableColumn<Entrenador, String> colDNI;
    @FXML private TableColumn<Entrenador, String> colEmail;
    @FXML private TableColumn<Entrenador, String> colTelefono;

    @FXML
    public void initialize() {
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

    private void cargarTabla() {
        EntrenadorDAO dao = new EntrenadorDAO();
        ObservableList<Entrenador> lista =
                FXCollections.observableArrayList(dao.listarEntrenadores());
        tablaEntrenadores.setItems(lista);
    }

    @FXML
    void volverAlMenu(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/boxranger/boxranger/BoxRanger-view.fxml"));
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