package com.boxranger.boxranger.database;

import com.boxranger.boxranger.modelo.Entrenador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EntrenadorDAO {

    public List<Entrenador> listarEntrenadores() {
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
                        rs.getString("especialidad"),
                        rs.getString("DNI"),
                        rs.getString("email"),
                        rs.getString("telefono")

                );
                lista.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void insertarEntrenador(Entrenador entrenador) {
        String sql = "INSERT INTO Entrenadores (nombre, apellidos, DNI, email, telefono, especialidad) VALUES (?,?,?,?,?,?)";

        try {
            Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, entrenador.getNombre());
            ps.setString(2, entrenador.getApellidos());
            ps.setString(6, entrenador.getEspecialidad());
            ps.setString(3, entrenador.getDNI());
            ps.setString(4, entrenador.getEmail());
            ps.setString(5, entrenador.getTelefono());


            int rs = ps.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();

        }
    };

    public void eliminarEntrenador(Entrenador entrenador) {

        String sql = "DELETE FROM Entrenadores WHERE idEntrenador = ?";

        try {
            Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, entrenador.getIdEntrenador());

            int rs = ps.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();

        }
    }


    public void actualizarEntrenador(Entrenador entrenador) {

        String sql = "UPDATE Entrenadores SET nombre=?, apellidos=?, DNI=?, email=?, telefono=?, especialidad=? WHERE idEntrenador = ?";

        try {
            Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);


            ps.setString(1, entrenador.getNombre());
            ps.setString(2, entrenador.getApellidos());
            ps.setString(3, entrenador.getDNI());
            ps.setString(4, entrenador.getEmail());
            ps.setString(5, entrenador.getTelefono());
            ps.setString(6, entrenador.getEspecialidad());
            ps.setInt(7, entrenador.getIdEntrenador());

            int rs = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
};
