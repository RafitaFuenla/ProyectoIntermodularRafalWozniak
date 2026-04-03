package com.boxranger.boxranger.controller;

import com.boxranger.boxranger.database.PagoDAO;
import com.boxranger.boxranger.modelo.Pago;
import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
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
 * Esta pantalla es de solo lectura: permite consultar los pagos
 * de los socios pero no modificarlos.
 */
public class PagosController {

    @FXML private TableView<Pago> tablaPagos;
    @FXML private TableColumn<Pago, Integer> colIdPago;
    @FXML private TableColumn<Pago, String> colSocio;
    @FXML private TableColumn<Pago, Double> colCuota;
    @FXML private TableColumn<Pago, String> colFechaPago;
    @FXML private TableColumn<Pago, String> colEstado;

    private final PagoDAO pagoDAO = new PagoDAO();

    @FXML
    public void initialize() {
        colIdPago.setCellValueFactory(data ->
                new SimpleIntegerProperty(data.getValue().getIdPago()).asObject());

        colSocio.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getNombreCompleto()));

        colCuota.setCellValueFactory(data ->
                new SimpleDoubleProperty(data.getValue().getCuota()).asObject());

        colFechaPago.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getFecha_pago()));

        colEstado.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getEstado()));

        cargarTabla();
    }

    private void cargarTabla() {
        try {
            ObservableList<Pago> lista =
                    FXCollections.observableArrayList(pagoDAO.listarPagos());
            tablaPagos.setItems(lista);
        } catch (RuntimeException e) {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("No se pudieron cargar los pagos");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            });
        }
    }

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