package com.boxranger.boxranger.controller;

import com.boxranger.boxranger.database.SocioDAO;
import com.boxranger.boxranger.modelo.Socio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class SociosController {

    @FXML private TableView<Socio> tablaSocios;
    @FXML private TableColumn<Socio, Integer> colIdSocio;
    @FXML private TableColumn<Socio, String> colNombre;
    @FXML private TableColumn<Socio, String> colApellidos;
    @FXML private TableColumn<Socio, String> colDNI;
    @FXML private TableColumn<Socio, String> colEmail;
    @FXML private TableColumn<Socio, String> colTelefono;
    @FXML private TableColumn<Socio, String> colFecha_alta;
    @FXML private TableColumn<Socio, String> colFecha_baja;

    @FXML private TextField txtNombre;
    @FXML private TextField txtApellidos;
    @FXML private TextField txtDNI;
    @FXML private TextField txtEmail;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtFechaAlta;
    @FXML private TextField txtFechaBaja;

    private SocioDAO socioDAO = new SocioDAO();

    @FXML
    public void initialize() {
        colIdSocio.setCellValueFactory(data ->
                new javafx.beans.property.SimpleIntegerProperty(
                        data.getValue().getIdSocio()).asObject());
        colNombre.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getNombre()));
        colApellidos.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getApellidos()));
        colDNI.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getDNI()));
        colEmail.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getEmail()));
        colTelefono.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getTelefono()));
        colFecha_alta.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getFecha_alta()));
        colFecha_baja.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getFecha_baja()));

        cargarTabla();
    }

    private void cargarTabla() {
        ObservableList<Socio> lista =
                FXCollections.observableArrayList(socioDAO.listarSocios());
        tablaSocios.setItems(lista);
    }

    @FXML
    private void anadir(ActionEvent event) {
        Socio socio = new Socio(0,
                txtNombre.getText(),
                txtApellidos.getText(),
                txtDNI.getText(),
                txtEmail.getText(),
                txtTelefono.getText(),
                txtFechaAlta.getText(),
                txtFechaBaja.getText()
        );
        socioDAO.insertarSocio(socio);
        cargarTabla();
        limpiarCampos();
    }

    @FXML
    private void eliminar(ActionEvent event) {
        Socio socio = tablaSocios.getSelectionModel().getSelectedItem();
        if (socio != null) {
            socioDAO.eliminarSocio(socio);
            cargarTabla();
        }
    }

    @FXML
    private void editar(ActionEvent event) {
        Socio socio = tablaSocios.getSelectionModel().getSelectedItem();
        if (socio != null) {
            socio.setNombre(txtNombre.getText());
            socio.setApellidos(txtApellidos.getText());
            socio.setDNI(txtDNI.getText());
            socio.setEmail(txtEmail.getText());
            socio.setTelefono(txtTelefono.getText());
            socio.setFecha_alta(txtFechaAlta.getText());
            socio.setFecha_baja(txtFechaBaja.getText());
            socioDAO.actualizarSocio(socio);
            cargarTabla();
            limpiarCampos();
        }
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtApellidos.clear();
        txtDNI.clear();
        txtEmail.clear();
        txtTelefono.clear();
        txtFechaAlta.clear();
        txtFechaBaja.clear();
    }

    @FXML
    private void volverAlMenu(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/com/boxranger/boxranger/BoxRanger-view.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((javafx.scene.Node)
                    event.getSource()).getScene().getWindow();
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
