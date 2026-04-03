package com.boxranger.boxranger.controller;

import com.boxranger.boxranger.database.PagoDAO;
import com.boxranger.boxranger.modelo.Pago;
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

/**
 * Controlador de la pantalla de visualización de pagos.
 * Esta pantalla es de solo lectura — permite consultar los pagos
 * de los socios pero no modificarlos, ya que es información gestionada internamente.
 */
public class PagosController {

    // Tabla y columnas de pagos
    @FXML private TableView<Pago> tablaPagos;
    @FXML private TableColumn<Pago, Integer> colIdPago;
    @FXML private TableColumn<Pago, Integer> colIdSocio;
    @FXML private TableColumn<Pago, Double> colCuota;
    @FXML private TableColumn<Pago, String> colFechaPago;
    @FXML private TableColumn<Pago, String> colEstado;

    /** DAO para acceder a los datos de pagos en la base de datos */
    private final PagoDAO pagoDAO = new PagoDAO();

    /**
     * Método de inicialización que se ejecuta automáticamente al cargar la pantalla.
     * Configura las columnas de la tabla y carga los datos desde la base de datos.
     */
    @FXML
    public void initialize() {
        // Configura cada columna con el campo correspondiente del objeto Pago
        colIdPago.setCellValueFactory(data ->
                new javafx.beans.property.SimpleIntegerProperty(
                        data.getValue().getIdPago()).asObject());

        colIdSocio.setCellValueFactory(data ->
                new javafx.beans.property.SimpleIntegerProperty(
                        data.getValue().getIdSocio()).asObject());

        colCuota.setCellValueFactory(data ->
                new javafx.beans.property.SimpleDoubleProperty(
                        data.getValue().getCuota()).asObject());

        colFechaPago.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getFecha_pago()));

        colEstado.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getEstado()));

        cargarTabla();
    }

    /**
     * Carga todos los pagos desde la base de datos y los muestra en la tabla.
     * Si hay un error de base de datos, muestra un alert con el motivo.
     */
    private void cargarTabla() {
        try {
            ObservableList<Pago> lista =
                    FXCollections.observableArrayList(pagoDAO.listarPagos());
            tablaPagos.setItems(lista);
        } catch (RuntimeException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se pudieron cargar los pagos");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
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
