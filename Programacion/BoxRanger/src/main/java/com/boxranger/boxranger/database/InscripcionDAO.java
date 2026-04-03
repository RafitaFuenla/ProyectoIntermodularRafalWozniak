package com.boxranger.boxranger.database;

import com.boxranger.boxranger.modelo.Inscripcion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAO (Data Access Object) para gestionar las operaciones
 * de base de datos relacionadas con las inscripciones de socios a clases.
 * La tabla Inscripciones actúa como tabla intermedia entre Socios y Clases
 * para representar la relación muchos a muchos entre ambas entidades.
 */
public class InscripcionDAO {

    /**
     * Obtiene todas las inscripciones registradas en la base de datos.
     * @return lista de objetos Inscripcion con todos los registros
     */
    public List<Inscripcion> listarInscripciones() {
        List<Inscripcion> lista = new ArrayList<>();
        String sql = "SELECT * FROM Inscripciones";

        try {
            Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Recorre cada fila del resultado y crea un objeto Inscripcion
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

    /**
     * Inserta una nueva inscripción en la base de datos.
     * Un socio solo puede estar inscrito una vez en la misma clase
     * ya que la clave primaria es compuesta (idSocio + idClase).
     * @param inscripcion objeto Inscripcion con los datos a insertar
     */
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

    /**
     * Elimina una inscripción de la base de datos.
     * Se elimina por la clave compuesta (idSocio + idClase).
     * @param inscripcion objeto Inscripcion a eliminar
     */
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

    /**
     * Actualiza la fecha de inscripción de un registro existente.
     * @param inscripcion objeto Inscripcion con los datos actualizados
     */
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