package com.boxranger.boxranger.database;

import com.boxranger.boxranger.modelo.Socio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SocioDAO {



    public List<Socio> listarSocios() {
        List<Socio> lista = new ArrayList<>();
        String sql = "SELECT * FROM Socios";

        try {
            Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

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
            e.printStackTrace();
        }

        return lista;
    }

    public void insertarSocio(Socio socio) {
        String sql = "INSERT INTO Socios (nombre, apellidos, DNI, email, telefono, fecha_alta, fecha_baja) VALUES (?,?,?,?,?,?,?)";

        try {
            Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, socio.getNombre());
            ps.setString(2, socio.getApellidos());
            ps.setString(3, socio.getDNI());
            ps.setString(4, socio.getEmail());
            ps.setString(5, socio.getTelefono());
            ps.setString(6, socio.getFecha_alta());
            ps.setString(7, socio.getFecha_baja());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarSocio(Socio socio) {
        String sql = "DELETE FROM Socios WHERE idSocio = ?";

        try {
            Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, socio.getIdSocio());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarSocio(Socio socio) {
        String sql = "UPDATE Socios SET nombre=?, apellidos=?, DNI=?, email=?, telefono=?, fecha_alta=?, fecha_baja=? WHERE idSocio = ?";

        try {
            Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, socio.getNombre());
            ps.setString(2, socio.getApellidos());
            ps.setString(3, socio.getDNI());
            ps.setString(4, socio.getEmail());
            ps.setString(5, socio.getTelefono());
            ps.setString(6, socio.getFecha_alta());
            ps.setString(7, socio.getFecha_baja());
            ps.setInt(8, socio.getIdSocio());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}