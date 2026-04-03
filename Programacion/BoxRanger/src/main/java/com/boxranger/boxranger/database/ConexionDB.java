package com.boxranger.boxranger.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase encargada de gestionar la conexión con la base de datos MySQL.
 * Utiliza JDBC para establecer la conexión mediante los parámetros configurados.
 */
public class ConexionDB {

    /** URL de conexión a la base de datos BoxRanger */
    private static final String url = "jdbc:mysql://127.0.0.1:3306/BoxRanger?useSSL=false&serverTimezone=UTC";

    /** Usuario de la base de datos */
    private static final String usuario = "root";

    /** Contraseña de la base de datos */
    private static final String contrasena = "";

    /**
     * Establece y devuelve una conexión activa con la base de datos.
     * @return objeto Connection si la conexión es exitosa, null si falla
     */
    public static Connection getConexion() {
        try {
            return DriverManager.getConnection(url, usuario, contrasena);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}