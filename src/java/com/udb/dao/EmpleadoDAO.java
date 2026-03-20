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
    
    public Empleado obtenerPorId(int id) {
    Empleado emp = null;
    String sql = "SELECT * FROM Empleados WHERE idEmpleado=?";

    try (Connection con = Conexion.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            emp = new Empleado();
            emp.setIdEmpleado(rs.getInt("idEmpleado"));
            emp.setNumeroDui(rs.getString("numeroDui"));
            emp.setNombrePersona(rs.getString("nombrePersona"));
            emp.setUsuario(rs.getString("usuario"));
            emp.setNumeroTelefono(rs.getString("numeroTelefono"));
            emp.setCorreoInstitucional(rs.getString("correoInstitucional"));
            emp.setFechaNacimiento(rs.getDate("fechaNacimiento"));
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return emp;
}
    
    public boolean actualizar(Empleado emp) {
    String sql = "UPDATE Empleados SET numeroDui=?, nombrePersona=?, usuario=?, numeroTelefono=?, correoInstitucional=?, fechaNacimiento=? WHERE idEmpleado=?";

    try (Connection con = Conexion.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, emp.getNumeroDui());
        ps.setString(2, emp.getNombrePersona());
        ps.setString(3, emp.getUsuario());
        ps.setString(4, emp.getNumeroTelefono());
        ps.setString(5, emp.getCorreoInstitucional());
        ps.setDate(6, emp.getFechaNacimiento());
        ps.setInt(7, emp.getIdEmpleado());

        return ps.executeUpdate() > 0;

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}
    
    public boolean eliminar(int id) {
    String sql = "DELETE FROM Empleados WHERE idEmpleado=?";

    try (Connection con = Conexion.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, id);
        return ps.executeUpdate() > 0;

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}
    
    // ====== MÉTODOS ADAPTADOS PARA EL SERVLET ======

public List<Empleado> listar() {
    return listarEmpleados();
}

public boolean insertar(Empleado emp) {
    return agregarEmpleado(emp);
}
    
    
}