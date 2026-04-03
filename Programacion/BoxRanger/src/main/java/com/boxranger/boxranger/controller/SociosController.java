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

/**
 * Controlador de la pantalla de gestión de socios.
 * Gestiona el CRUD completo de socios: listar, añadir, editar y eliminar.
 * Se comunica con la base de datos a través de SocioDAO.
 */
public class SociosController {

    // Columnas de la tabla de socios
    @FXML private TableColumn<Socio, Integer> colIdSocio;
    @FXML private TableColumn<Socio, String> colNombre;
    @FXML private TableColumn<Socio, String> colApellidos;
    @FXML private TableColumn<Socio, String> colDNI;
    @FXML private TableColumn<Socio, String> colEmail;
    @FXML private TableColumn<Socio, String> colTelefono;
    @FXML private TableColumn<Socio, String> colFecha_alta;
    @FXML private TableColumn<Socio, String> colFecha_baja;

    // Tabla y campos del formulario
    @FXML private TableView<Socio> tablaSocios;
    @FXML private TextField txtNombre;
    @FXML private TextField txtApellidos;
    @FXML private TextField txtDNI;
    @FXML private TextField txtEmail;
    @FXML private TextField txtTelefono;
    @FXML private DatePicker txtFechaAlta;
    @FXML private DatePicker txtFechaBaja;

    /** DAO para acceder a los datos de socios en la base de datos */
    private final SocioDAO socioDAO = new SocioDAO();

    /** Socio actualmente seleccionado en la tabla */
    private Socio socioSeleccionado;

    /**
     * Metodo de inicialización que se ejecuta automáticamente al cargar la pantalla.
     * Configura las columnas de la tabla y carga los datos desde la base de datos.
     */
    @FXML
    public void initialize() {
        // Configura cada columna con el campo correspondiente del objeto Socio
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
        configurarSeleccionTabla();
    }

    /**
     * Configura el listener de selección de la tabla.
     * Al seleccionar un socio, rellena automáticamente el formulario con sus datos.
     */
    private void configurarSeleccionTabla() {
        tablaSocios.getSelectionModel().selectedItemProperty().addListener((obs, anterior, nuevo) -> {
            if (nuevo != null) {
                socioSeleccionado = nuevo;
                rellenarCampos(nuevo);
            }
        });
    }

    /**
     * Rellena los campos del formulario con los datos del socio seleccionado.
     * @param socio socio cuyos datos se mostrarán en el formulario
     */
    private void rellenarCampos(Socio socio) {
        txtNombre.setText(socio.getNombre());
        txtApellidos.setText(socio.getApellidos());
        txtDNI.setText(socio.getDNI());
        txtEmail.setText(socio.getEmail());
        txtTelefono.setText(socio.getTelefono());

        // Convierte el String de fecha a LocalDate para el DatePicker
        txtFechaAlta.setValue(
                socio.getFecha_alta() != null && !socio.getFecha_alta().isBlank()
                        ? LocalDate.parse(socio.getFecha_alta()) : null);

        txtFechaBaja.setValue(
                socio.getFecha_baja() != null && !socio.getFecha_baja().isBlank()
                        ? LocalDate.parse(socio.getFecha_baja()) : null);
    }

    /**
     * Carga todos los socios desde la base de datos y los muestra en la tabla.
     * Si hay un error de base de datos, muestra un alert con el motivo.
     */
    private void cargarTabla() {
        try {
            ObservableList<Socio> lista =
                    FXCollections.observableArrayList(socioDAO.listarSocios());
            tablaSocios.setItems(lista);
        } catch (RuntimeException e) {
            mostrarError("No se pudieron cargar los socios: " + e.getMessage());
        }
    }

    /**
     * Añade un nuevo socio a la base de datos con los datos del formulario.
     * Valida los campos obligatorios antes de insertar.
     * @param event evento del botón Añadir
     */
    @FXML
    private void anadir(ActionEvent event) {
        if (!validarCamposObligatorios()) return;

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

        try {
            socioDAO.insertarSocio(socio);
        } catch (RuntimeException e) {
            mostrarError("Error al guardar el socio: " + e.getMessage());
            return;
        }

        cargarTabla();
        limpiarCampos();
        socioSeleccionado = null;
        tablaSocios.getSelectionModel().clearSelection();
    }

    /**
     * Elimina el socio seleccionado en la tabla de la base de datos.
     * @param event evento del botón Eliminar
     */
    @FXML
    private void eliminar(ActionEvent event) {
        Socio socio = tablaSocios.getSelectionModel().getSelectedItem();

        if (socio == null) {
            mostrarAlerta("Debes seleccionar un socio para eliminar.");
            return;
        }

        try {
            socioDAO.eliminarSocio(socio);
        } catch (RuntimeException e) {
            mostrarError("Error al eliminar el socio: " + e.getMessage());
            return;
        }

        cargarTabla();
        limpiarCampos();
        socioSeleccionado = null;
        tablaSocios.getSelectionModel().clearSelection();
    }

    /**
     * Actualiza los datos del socio seleccionado con los valores del formulario.
     * @param event evento del botón Editar
     */
    @FXML
    private void editar(ActionEvent event) {
        if (socioSeleccionado == null) {
            mostrarAlerta("Debes seleccionar un socio para editar.");
            return;
        }

        if (!validarCamposObligatorios()) return;

        socioSeleccionado.setNombre(txtNombre.getText().trim());
        socioSeleccionado.setApellidos(txtApellidos.getText().trim());
        socioSeleccionado.setDNI(txtDNI.getText().trim());
        socioSeleccionado.setEmail(txtEmail.getText().trim());
        socioSeleccionado.setTelefono(txtTelefono.getText().trim());
        socioSeleccionado.setFecha_alta(
                txtFechaAlta.getValue() != null ? txtFechaAlta.getValue().toString() : null);
        socioSeleccionado.setFecha_baja(
                txtFechaBaja.getValue() != null ? txtFechaBaja.getValue().toString() : null);

        try {
            socioDAO.actualizarSocio(socioSeleccionado);
        } catch (RuntimeException e) {
            mostrarError("Error al actualizar el socio: " + e.getMessage());
            return;
        }

        cargarTabla();
        limpiarCampos();
        socioSeleccionado = null;
        tablaSocios.getSelectionModel().clearSelection();
    }

    /**
     * Valida que los campos del formulario tengan valores correctos antes de guardar.
     * Nombre, apellidos, DNI y fecha de alta son obligatorios.
     * Email, teléfono y fecha de baja son opcionales pero se valida su formato si se rellenan.
     * @return true si todo está correcto, false si hay algún problema
     */
    private boolean validarCamposObligatorios() {
        String nombre = txtNombre.getText();
        String apellidos = txtApellidos.getText();
        String dni = txtDNI.getText();
        String email = txtEmail.getText();
        String telefono = txtTelefono.getText();

        if (nombre == null || nombre.isBlank()) {
            mostrarAlerta("El nombre es obligatorio.");
            return false;
        }
        if (apellidos == null || apellidos.isBlank()) {
            mostrarAlerta("Los apellidos son obligatorios.");
            return false;
        }
        if (dni == null || dni.isBlank()) {
            mostrarAlerta("El DNI es obligatorio.");
            return false;
        }
        // DNI español: 8 dígitos seguidos de una letra
        if (!dni.trim().matches("\\d{8}[A-Za-z]")) {
            mostrarAlerta("El DNI no tiene el formato correcto (ej: 12345678A).");
            return false;
        }
        if (txtFechaAlta.getValue() == null) {
            mostrarAlerta("La fecha de alta es obligatoria.");
            return false;
        }

        // Email y teléfono son opcionales, pero si se rellenan se comprueba el formato
        if (email != null && !email.isBlank()) {
            if (!email.trim().matches("[\\w._%+\\-]+@[\\w.\\-]+\\.[a-zA-Z]{2,}")) {
                mostrarAlerta("El email no tiene un formato válido.");
                return false;
            }
        }
        if (telefono != null && !telefono.isBlank()) {
            if (!telefono.trim().matches("\\d{9}")) {
                mostrarAlerta("El teléfono debe tener 9 dígitos.");
                return false;
            }
        }

        // La fecha de baja, si se indica, no puede ser anterior a la de alta
        if (txtFechaBaja.getValue() != null) {
            if (txtFechaBaja.getValue().isBefore(txtFechaAlta.getValue())) {
                mostrarAlerta("La fecha de baja no puede ser anterior a la fecha de alta.");
                return false;
            }
        }

        return true;
    }

    /**
     * Limpia todos los campos del formulario.
     */
    private void limpiarCampos() {
        txtNombre.clear();
        txtApellidos.clear();
        txtDNI.clear();
        txtEmail.clear();
        txtTelefono.clear();
        txtFechaAlta.setValue(null);
        txtFechaBaja.setValue(null);
    }

    /**
     * Muestra una alerta de tipo WARNING con el mensaje indicado.
     * Se usa para avisos de validación de formulario.
     * @param mensaje texto a mostrar en la alerta
     */
    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aviso");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    /**
     * Muestra una alerta de tipo ERROR con el mensaje indicado.
     * Se usa cuando falla una operación de base de datos.
     * @param mensaje texto a mostrar en la alerta
     */
    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
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
