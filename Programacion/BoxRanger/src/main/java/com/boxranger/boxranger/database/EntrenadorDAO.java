package com.boxranger.boxranger.database;

import com.boxranger.boxranger.modelo.Entrenador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EntrenadorDAO {
    public List<Entrenador> getAll() {
        List<Entrenador> lista = new ArrayList<>();
        String sql = "SELECT * FROM Entrenadores";

        try {
            Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Entrenador e = new Entrenador(
                        rs.getInt("idEntrenador"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("DNI"),
                        rs.getString("email"),
                        rs.getString("telefono"),
                        rs.getString("especialidad")
                );
                lista.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

}
