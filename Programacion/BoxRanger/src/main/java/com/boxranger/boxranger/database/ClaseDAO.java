package com.boxranger.boxranger.database;

import com.boxranger.boxranger.modelo.Clase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAO (Data Access Object) para gestionar las operaciones
 * de base de datos relacionadas con las clases del box.
 */
public class ClaseDAO {

    /**
     * Obtiene todas las clases registradas en la base de datos.
     * @return lista de objetos Clase con todos los registros
     */
    public List<Clase> listarClases() {
        List<Clase> lista = new ArrayList<>();
        String sql = "SELECT * FROM Clases";

        // try-with-resources garantiza el cierre automático de conexión, statement y resultset
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

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
            throw new RuntimeException("Error al consultar las clases", e);
        }

        return lista;
    }

    /**
     * Inserta una nueva clase en la base de datos.
     * @param clase objeto Clase con los datos a insertar
     */
    public void insertarClase(Clase clase) {
        String sql = "INSERT INTO Clases (idEntrenador, nombre, hora_inicio, hora_fin, max_atletas) VALUES (?,?,?,?,?)";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, clase.getIdEntrenador());
            ps.setString(2, clase.getNombre());
            ps.setString(3, clase.getHora_inicio());
            ps.setString(4, clase.getHora_fin());
            ps.setInt(5, clase.getMax_atletas());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar la clase", e);
        }
    }

    /**
     * Elimina una clase de la base de datos según su ID.
     * @param clase objeto Clase a eliminar
     */
    public void eliminarClase(Clase clase) {
        String sql = "DELETE FROM Clases WHERE idClase = ?";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, clase.getIdClase());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar la clase", e);
        }
    }

    /**
     * Actualiza los datos de una clase existente en la base de datos.
     * @param clase objeto Clase con los datos actualizados
     */
    public void actualizarClase(Clase clase) {
        String sql = "UPDATE Clases SET idEntrenador=?, nombre=?, hora_inicio=?, hora_fin=?, max_atletas=? WHERE idClase = ?";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, clase.getIdEntrenador());
            ps.setString(2, clase.getNombre());
            ps.setString(3, clase.getHora_inicio());
            ps.setString(4, clase.getHora_fin());
            ps.setInt(5, clase.getMax_atletas());
            ps.setInt(6, clase.getIdClase());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar la clase", e);
        }
    }
}
