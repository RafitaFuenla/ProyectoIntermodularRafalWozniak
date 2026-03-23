package com.boxranger.boxranger.database;

import com.boxranger.boxranger.modelo.Pago;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PagoDAO {

    public List<Pago> listarPagos() {
        List<Pago> lista = new ArrayList<>();
        String sql = "SELECT * FROM Pagos";

        try {
            Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

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