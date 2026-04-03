package com.boxranger.boxranger.database;

import com.boxranger.boxranger.modelo.Pago;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PagoDAO {

    /**
     * Obtiene todos los pagos registrados junto con el nombre y apellidos del socio.
     */
    public List<Pago> listarPagos() {

        List<Pago> lista = new ArrayList<>();

        String sql = """
                SELECT 
                    p.idPago,
                    p.idSocio,
                    s.nombre,
                    s.apellidos,
                    p.cuota,
                    p.fecha_pago,
                    p.estado
                FROM Pagos p
                INNER JOIN Socios s 
                ON p.idSocio = s.idSocio
                ORDER BY p.fecha_pago DESC
                """;

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Pago pago = new Pago(
                        rs.getInt("idPago"),
                        rs.getInt("idSocio"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getDouble("cuota"),
                        rs.getString("fecha_pago"),
                        rs.getString("estado")
                );

                lista.add(pago);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar los pagos", e);
        }

        return lista;
    }


    /**
     * Inserta un nuevo pago
     */
    public void insertarPago(Pago pago) {

        String sql = """
                INSERT INTO Pagos 
                (idSocio, cuota, fecha_pago, estado) 
                VALUES (?, ?, ?, ?)
                """;

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, pago.getIdSocio());
            ps.setDouble(2, pago.getCuota());
            ps.setString(3, pago.getFecha_pago());
            ps.setString(4, pago.getEstado());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar el pago", e);
        }
    }


    /**
     * Elimina un pago
     */
    public void eliminarPago(Pago pago) {

        String sql = "DELETE FROM Pagos WHERE idPago = ?";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, pago.getIdPago());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar el pago", e);
        }
    }


    /**
     * Actualiza un pago
     */
    public void actualizarPago(Pago pago) {

        String sql = """
                UPDATE Pagos 
                SET idSocio = ?, 
                    cuota = ?, 
                    fecha_pago = ?, 
                    estado = ? 
                WHERE idPago = ?
                """;

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, pago.getIdSocio());
            ps.setDouble(2, pago.getCuota());
            ps.setString(3, pago.getFecha_pago());
            ps.setString(4, pago.getEstado());
            ps.setInt(5, pago.getIdPago());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el pago", e);
        }
    }
}