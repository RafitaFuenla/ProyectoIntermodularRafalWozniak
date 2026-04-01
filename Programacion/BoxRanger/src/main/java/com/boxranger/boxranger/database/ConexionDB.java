package com.boxranger.boxranger.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static final String url = "jdbc:mysql://127.0.0.1:3306/BoxRanger?useSSL=false&serverTimezone=UTC";
    private static final String usuario = "root";
    private static final String contrasena = "";

    public static Connection getConexion() {

        try {
            return
                   DriverManager.getConnection(url,usuario, contrasena);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }




    }
}
