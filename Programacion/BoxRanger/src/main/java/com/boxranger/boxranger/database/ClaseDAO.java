package com.boxranger.boxranger.database;

import com.boxranger.boxranger.modelo.Clase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClaseDAO {

    public List<Clase> listarClases() {
        List<Clase> lista = new ArrayList<>();
        String sql = "SELECT * FROM Clases";

        try {
            Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Clase c = new Clase(
                        rs.getInt("idClase"),
                        rs.getInt("idEntrenador"),
                        rs.getString("nombre"),
                        rs.getString("hora_inicio"),
                        rs.getString("hora_fin"),
                        rs.getInt("max_atletas")
                );

                lista.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public void insertarClase(Clase clase) {
        String sql = "INSERT INTO Clases (idEntrenador, nombre, hora_inicio, hora_fin, max_atletas) VALUES (?,?,?,?,?)";

        try {
            Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, clase.getIdEntrenador());
            ps.setString(2, clase.getNombre());
            ps.setString(3, clase.getHora_inicio());
            ps.setString(4, clase.getHora_fin());
            ps.setInt(5, clase.getMax_atletas());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarClase(Clase clase) {
        String sql = "DELETE FROM Clases WHERE idClase = ?";

        try {
            Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, clase.getIdClase());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarClase(Clase clase) {
        String sql = "UPDATE Clases SET idEntrenador=?, nombre=?, hora_inicio=?, hora_fin=?, max_atletas=? WHERE idClase = ?";

        try {
            Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, clase.getIdEntrenador());
            ps.setString(2, clase.getNombre());
            ps.setString(3, clase.getHora_inicio());
            ps.setString(4, clase.getHora_fin());
            ps.setInt(5, clase.getMax_atletas());
            ps.setInt(6, clase.getIdClase());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}