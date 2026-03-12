package com.udb.conf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase para gestionar los parámetros de conexión a MySQL.
 */
public class Conexion {
    private static final String URL = "jdbc:mysql://localhost:3306/rrhh_udb2?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root"; // Cambiar por tu usuario
    private static final String PASSWORD = ""; // Cambiar por tu contraseña

    public static Connection getConnection() {
        Connection con = null;
        try {
            // Cargar el driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error de conexión a la BD: " + e.getMessage());
        }
        return con;
    }
}