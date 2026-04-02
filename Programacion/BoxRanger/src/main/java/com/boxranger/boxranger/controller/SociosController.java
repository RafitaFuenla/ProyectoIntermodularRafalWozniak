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
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

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
    @FXML private DatePicker txtFechaAlta;
    @FXML private DatePicker txtFechaBaja;

    private final SocioDAO socioDAO = new SocioDAO();
    private Socio socioSeleccionado;

    @FXML
    public void initialize() {
        colIdSocio.setCellValueFactory(data ->
                new javafx.beans.property.SimpleIntegerProperty(
                        data.getValue().getIdSocio()
                ).asObject()
        );

        colNombre.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getNombre()
                )
        );

        colApellidos.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getApellidos()
                )
        );

        colDNI.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getDNI()
                )
        );

        colEmail.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getEmail()
                )
        );

        colTelefono.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getTelefono()
                )
        );

        colFecha_alta.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getFecha_alta()
                )
        );

        colFecha_baja.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getFecha_baja()
                )
        );

        cargarTabla();
        configurarSeleccionTabla();
    }

    private void configurarSeleccionTabla() {
        tablaSocios.getSelectionModel().selectedItemProperty().addListener((obs, anterior, nuevo) -> {
            if (nuevo != null) {
                socioSeleccionado = nuevo;
                rellenarCampos(nuevo);
            }
        });
    }

    private void rellenarCampos(Socio socio) {
        txtNombre.setText(socio.getNombre());
        txtApellidos.setText(socio.getApellidos());
        txtDNI.setText(socio.getDNI());
        txtEmail.setText(socio.getEmail());
        txtTelefono.setText(socio.getTelefono());

        txtFechaAlta.setValue(
                socio.getFecha_alta() != null && !socio.getFecha_alta().isBlank()
                        ? LocalDate.parse(socio.getFecha_alta())
                        : null
        );

        txtFechaBaja.setValue(
                socio.getFecha_baja() != null && !socio.getFecha_baja().isBlank()
                        ? LocalDate.parse(socio.getFecha_baja())
                        : null
        );
    }

    private void cargarTabla() {
        ObservableList<Socio> lista =
                FXCollections.observableArrayList(socioDAO.listarSocios());
        tablaSocios.setItems(lista);
    }

    @FXML
    private void anadir(ActionEvent event) {
        if (!validarCamposObligatorios()) {
            return;
        }

        Socio socio = new Socio(
                0,
                txtNombre.getText().trim(),
                txtApellidos.getText().trim(),
                txtDNI.getText().trim(),
                txtEmail.getText().trim(),
                txtTelefono.getText().trim(),
                txtFechaAlta.getValue() != null ? txtFechaAlta.getValue().toString() : null,
                txtFechaBaja.getValue() != null ? txtFechaBaja.getValue().toString() : null
        );

        socioDAO.insertarSocio(socio);
        cargarTabla();
        limpiarCampos();
        socioSeleccionado = null;
        tablaSocios.getSelectionModel().clearSelection();
    }

    @FXML
    private void eliminar(ActionEvent event) {
        Socio socio = tablaSocios.getSelectionModel().getSelectedItem();

        if (socio == null) {
            mostrarAlerta("Debes seleccionar un socio para eliminar.");
            return;
        }

        socioDAO.eliminarSocio(socio);
        cargarTabla();
        limpiarCampos();
        socioSeleccionado = null;
        tablaSocios.getSelectionModel().clearSelection();
    }

    @FXML
    private void editar(ActionEvent event) {
        if (socioSeleccionado == null) {
            mostrarAlerta("Debes seleccionar un socio para editar.");
            return;
        }

        if (!validarCamposObligatorios()) {
            return;
        }

        socioSeleccionado.setNombre(txtNombre.getText().trim());
        socioSeleccionado.setApellidos(txtApellidos.getText().trim());
        socioSeleccionado.setDNI(txtDNI.getText().trim());
        socioSeleccionado.setEmail(txtEmail.getText().trim());
        socioSeleccionado.setTelefono(txtTelefono.getText().trim());
        socioSeleccionado.setFecha_alta(
                txtFechaAlta.getValue() != null ? txtFechaAlta.getValue().toString() : null
        );
        socioSeleccionado.setFecha_baja(
                txtFechaBaja.getValue() != null ? txtFechaBaja.getValue().toString() : null
        );

        socioDAO.actualizarSocio(socioSeleccionado);
        cargarTabla();
        limpiarCampos();
        socioSeleccionado = null;
        tablaSocios.getSelectionModel().clearSelection();
    }

    private boolean validarCamposObligatorios() {
        if (txtNombre.getText() == null || txtNombre.getText().isBlank()) {
            mostrarAlerta("El nombre es obligatorio.");
            return false;
        }

        if (txtApellidos.getText() == null || txtApellidos.getText().isBlank()) {
            mostrarAlerta("Los apellidos son obligatorios.");
            return false;
        }

        if (txtDNI.getText() == null || txtDNI.getText().isBlank()) {
            mostrarAlerta("El DNI es obligatorio.");
            return false;
        }

        if (txtFechaAlta.getValue() == null) {
            mostrarAlerta("La fecha de alta es obligatoria.");
            return false;
        }

        return true;
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtApellidos.clear();
        txtDNI.clear();
        txtEmail.clear();
        txtTelefono.clear();
        txtFechaAlta.setValue(null);
        txtFechaBaja.setValue(null);
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aviso");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    private void volverAlMenu(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/boxranger/boxranger/BoxRanger-view.fxml")
            );
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