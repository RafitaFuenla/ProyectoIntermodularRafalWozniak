package com.boxranger.boxranger.database;

import com.boxranger.boxranger.modelo.Inscripcion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InscripcionDAO {

    public List<Inscripcion> listarInscripciones() {
        List<Inscripcion> lista = new ArrayList<>();
        String sql = "SELECT * FROM Inscripciones";

        try {
            Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Inscripcion i = new Inscripcion(
                        rs.getInt("idSocio"),
                        rs.getInt("idClase"),
                        rs.getString("fecha_inscripcion")
                );

                lista.add(i);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public void insertarInscripcion(Inscripcion inscripcion) {
        String sql = "INSERT INTO Inscripciones (idSocio, idClase, fecha_inscripcion) VALUES (?,?,?)";

        try {
            Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, inscripcion.getIdSocio());
            ps.setInt(2, inscripcion.getIdClase());
            ps.setString(3, inscripcion.getFecha_inscripcion());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarInscripcion(Inscripcion inscripcion) {
        String sql = "DELETE FROM Inscripciones WHERE idSocio = ? AND idClase = ?";

        try {
            Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, inscripcion.getIdSocio());
            ps.setInt(2, inscripcion.getIdClase());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarInscripcion(Inscripcion inscripcion) {
        String sql = "UPDATE Inscripciones SET fecha_inscripcion=? WHERE idSocio = ? AND idClase = ?";

        try {
            Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, inscripcion.getFecha_inscripcion());
            ps.setInt(2, inscripcion.getIdSocio());
            ps.setInt(3, inscripcion.getIdClase());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}