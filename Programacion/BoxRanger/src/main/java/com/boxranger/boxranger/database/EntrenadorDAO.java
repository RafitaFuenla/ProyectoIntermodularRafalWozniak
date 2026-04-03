package com.boxranger.boxranger.database;

import com.boxranger.boxranger.modelo.Entrenador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAO (Data Access Object) para gestionar las operaciones
 * de base de datos relacionadas con los entrenadores del box.
 */
public class EntrenadorDAO {

    /**
     * Obtiene todos los entrenadores registrados en la base de datos.
     * @return lista de objetos Entrenador con todos los registros
     */
    public List<Entrenador> listarEntrenadores() {
        List<Entrenador> lista = new ArrayList<>();
        String sql = "SELECT * FROM Entrenadores";

        try {
            Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Recorre cada fila del resultado y crea un objeto Entrenador
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

    /**
     * Inserta un nuevo entrenador en la base de datos.
     * @param entrenador objeto Entrenador con los datos a insertar
     */
    public void insertarEntrenador(Entrenador entrenador) {
        String sql = "INSERT INTO Entrenadores (nombre, apellidos, DNI, email, telefono, especialidad) VALUES (?,?,?,?,?,?)";

        try {
            Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, entrenador.getNombre());
            ps.setString(2, entrenador.getApellidos());
            ps.setString(3, entrenador.getDNI());
            ps.setString(4, entrenador.getEmail());
            ps.setString(5, entrenador.getTelefono());
            ps.setString(6, entrenador.getEspecialidad());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Elimina un entrenador de la base de datos según su ID.
     * @param entrenador objeto Entrenador a eliminar
     */
    public void eliminarEntrenador(Entrenador entrenador) {
        String sql = "DELETE FROM Entrenadores WHERE idEntrenador = ?";

        try {
            Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, entrenador.getIdEntrenador());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Actualiza los datos de un entrenador existente en la base de datos.
     * @param entrenador objeto Entrenador con los datos actualizados
     */
    public void actualizarEntrenador(Entrenador entrenador) {
        String sql = "UPDATE Entrenadores SET nombre=?, apellidos=?, DNI=?, email=?, telefono=?, especialidad=? WHERE idEntrenador=?";

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

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}