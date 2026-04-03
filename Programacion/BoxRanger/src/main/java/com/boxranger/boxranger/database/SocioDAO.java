package com.boxranger.boxranger.database;

import com.boxranger.boxranger.modelo.Socio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAO (Data Access Object) para gestionar las operaciones
 * de base de datos relacionadas con los socios del box.
 */
public class SocioDAO {

    /**
     * Obtiene todos los socios registrados en la base de datos.
     * @return lista de objetos Socio con todos los registros
     */
    public List<Socio> listarSocios() {
        List<Socio> lista = new ArrayList<>();
        String sql = "SELECT * FROM Socios";

        // try-with-resources garantiza el cierre automático de conexión, statement y resultset
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Socio s = new Socio(
                        rs.getInt("idSocio"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("DNI"),
                        rs.getString("email"),
                        rs.getString("telefono"),
                        rs.getString("fecha_alta"),
                        rs.getString("fecha_baja")
                );
                lista.add(s);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar los socios", e);
        }

        return lista;
    }

    /**
     * Inserta un nuevo socio en la base de datos.
     * La fecha de alta es obligatoria. La fecha de baja puede ser nula.
     * @param socio objeto Socio con los datos a insertar
     */
    public void insertarSocio(Socio socio) {
        String sql = "INSERT INTO Socios (nombre, apellidos, DNI, email, telefono, fecha_alta, fecha_baja) VALUES (?,?,?,?,?,?,?)";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, socio.getNombre());
            ps.setString(2, socio.getApellidos());
            ps.setString(3, socio.getDNI());
            ps.setString(4, socio.getEmail());
            ps.setString(5, socio.getTelefono());

            // La fecha de alta es obligatoria
            if (socio.getFecha_alta() == null || socio.getFecha_alta().isBlank()) {
                throw new SQLException("La fecha de alta es obligatoria");
            } else {
                ps.setString(6, socio.getFecha_alta());
            }

            // La fecha de baja es opcional, puede ser NULL
            if (socio.getFecha_baja() == null || socio.getFecha_baja().isBlank()) {
                ps.setNull(7, Types.DATE);
            } else {
                ps.setString(7, socio.getFecha_baja());
            }

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar el socio", e);
        }
    }

    /**
     * Elimina un socio de la base de datos según su ID.
     * @param socio objeto Socio a eliminar
     */
    public void eliminarSocio(Socio socio) {
        String sql = "DELETE FROM Socios WHERE idSocio = ?";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, socio.getIdSocio());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar el socio", e);
        }
    }

    /**
     * Actualiza los datos de un socio existente en la base de datos.
     * @param socio objeto Socio con los datos actualizados
     */
    public void actualizarSocio(Socio socio) {
        String sql = "UPDATE Socios SET nombre=?, apellidos=?, DNI=?, email=?, telefono=?, fecha_alta=?, fecha_baja=? WHERE idSocio = ?";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, socio.getNombre());
            ps.setString(2, socio.getApellidos());
            ps.setString(3, socio.getDNI());
            ps.setString(4, socio.getEmail());
            ps.setString(5, socio.getTelefono());

            // La fecha de alta es obligatoria
            if (socio.getFecha_alta() == null || socio.getFecha_alta().isBlank()) {
                throw new SQLException("La fecha de alta es obligatoria");
            } else {
                ps.setString(6, socio.getFecha_alta());
            }

            // La fecha de baja es opcional, puede ser NULL
            if (socio.getFecha_baja() == null || socio.getFecha_baja().isBlank()) {
                ps.setNull(7, Types.DATE);
            } else {
                ps.setString(7, socio.getFecha_baja());
            }

            ps.setInt(8, socio.getIdSocio());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el socio", e);
        }
    }
}
