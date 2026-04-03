package com.boxranger.boxranger.database;

import com.boxranger.boxranger.modelo.Pago;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAO (Data Access Object) para gestionar las operaciones
 * de base de datos relacionadas con los pagos de los socios del box.
 */
public class PagoDAO {

    /**
     * Obtiene todos los pagos registrados en la base de datos.
     * @return lista de objetos Pago con todos los registros
     */
    public List<Pago> listarPagos() {
        List<Pago> lista = new ArrayList<>();
        String sql = "SELECT * FROM Pagos";

        try {
            Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Recorre cada fila del resultado y crea un objeto Pago
            while (rs.next()) {
                Pago p = new Pago(
                        rs.getInt("idPago"),
                        rs.getInt("idSocio"),
                        rs.getDouble("cuota"),
                        rs.getString("fecha_pago"),
                        rs.getString("estado")
                );
                lista.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    /**
     * Inserta un nuevo pago en la base de datos.
     * El estado puede ser: pagado, pendiente o fallido.
     * @param pago objeto Pago con los datos a insertar
     */
    public void insertarPago(Pago pago) {
        String sql = "INSERT INTO Pagos (idSocio, cuota, fecha_pago, estado) VALUES (?,?,?,?)";

        try {
            Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, pago.getIdSocio());
            ps.setDouble(2, pago.getCuota());
            ps.setString(3, pago.getFecha_pago());
            ps.setString(4, pago.getEstado());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Elimina un pago de la base de datos según su ID.
     * @param pago objeto Pago a eliminar
     */
    public void eliminarPago(Pago pago) {
        String sql = "DELETE FROM Pagos WHERE idPago = ?";

        try {
            Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, pago.getIdPago());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Actualiza los datos de un pago existente en la base de datos.
     * @param pago objeto Pago con los datos actualizados
     */
    public void actualizarPago(Pago pago) {
        String sql = "UPDATE Pagos SET idSocio=?, cuota=?, fecha_pago=?, estado=? WHERE idPago = ?";

        try {
            Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, pago.getIdSocio());
            ps.setDouble(2, pago.getCuota());
            ps.setString(3, pago.getFecha_pago());
            ps.setString(4, pago.getEstado());
            ps.setInt(5, pago.getIdPago());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}