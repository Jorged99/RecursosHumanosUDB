package com.udb.dao;

import com.udb.conf.Conexion;
import com.udb.model.Empleado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {
    
    // Consultas SQL preparadas
    private static final String SQL_SELECT = "SELECT * FROM Empleados";
    private static final String SQL_INSERT = "INSERT INTO Empleados (numeroDui, nombrePersona, usuario, numeroTelefono, correoInstitucional, fechaNacimiento) VALUES (?, ?, ?, ?, ?, ?)";

    /**
     * Lista todos los empleados de la base de datos.
     */
    public List<Empleado> listarEmpleados() {
        List<Empleado> lista = new ArrayList<>();
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_SELECT);
             ResultSet rs = stmt.executeQuery()) {
             
            while (rs.next()) {
                Empleado emp = new Empleado();
                emp.setIdEmpleado(rs.getInt("idEmpleado"));
                emp.setNumeroDui(rs.getString("numeroDui"));
                emp.setNombrePersona(rs.getString("nombrePersona"));
                emp.setUsuario(rs.getString("usuario"));
                emp.setNumeroTelefono(rs.getString("numeroTelefono"));
                emp.setCorreoInstitucional(rs.getString("correoInstitucional"));
                emp.setFechaNacimiento(rs.getDate("fechaNacimiento"));
                lista.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    /**
     * Inserta un nuevo empleado usando parámetros de forma segura.
     */
    public boolean agregarEmpleado(Empleado emp) {
        boolean rowInserted = false;
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_INSERT)) {
             
            // ¡Seguridad ante inyección SQL!
            stmt.setString(1, emp.getNumeroDui());
            stmt.setString(2, emp.getNombrePersona());
            stmt.setString(3, emp.getUsuario());
            stmt.setString(4, emp.getNumeroTelefono());
            stmt.setString(5, emp.getCorreoInstitucional());
            stmt.setDate(6, emp.getFechaNacimiento());
            
            rowInserted = stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowInserted;
    }
}