package com.udb.dao;

import com.udb.conf.Conexion;
import com.udb.model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContratacionDAO {

    public List<Contratacion> listar() {
        List<Contratacion> lista = new ArrayList<>();
        // Hacemos JOIN para traer los nombres de las otras tablas
        String sql = "SELECT c.idContratacion, c.fechaContracion, c.salario, c.estado, "
                   + "e.idEmpleado, e.nombrePersona, d.idDepartamento, d.nombreDepartamento, "
                   + "cg.idCargo, cg.cargo, tc.idTipoContratacion, tc.tipoContratacion "
                   + "FROM Contrataciones c "
                   + "INNER JOIN Empleados e ON c.idEmpleado = e.idEmpleado "
                   + "INNER JOIN Departamento d ON c.idDepartamento = d.idDepartamento "
                   + "INNER JOIN Cargos cg ON c.idCargo = cg.idCargo "
                   + "INNER JOIN TipoContratacion tc ON c.idTipoContratacion = tc.idTipoContratacion";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Contratacion c = new Contratacion();
                c.setIdContratacion(rs.getInt("idContratacion"));
                c.setFechaContracion(rs.getDate("fechaContracion"));
                c.setSalario(rs.getDouble("salario"));
                c.setEstado(rs.getBoolean("estado"));

                Empleado emp = new Empleado();
                emp.setIdEmpleado(rs.getInt("idEmpleado"));
                emp.setNombrePersona(rs.getString("nombrePersona"));
                c.setEmpleado(emp);

                Departamento depto = new Departamento();
                depto.setIdDepartamento(rs.getInt("idDepartamento"));
                depto.setNombreDepartamento(rs.getString("nombreDepartamento"));
                c.setDepartamento(depto);

                Cargo cargo = new Cargo();
                cargo.setIdCargo(rs.getInt("idCargo"));
                cargo.setCargo(rs.getString("cargo"));
                c.setCargo(cargo);

                TipoContratacion tipo = new TipoContratacion();
                tipo.setIdTipoContratacion(rs.getInt("idTipoContratacion"));
                tipo.setTipoContratacion(rs.getString("tipoContratacion"));
                c.setTipoContratacion(tipo);

                lista.add(c);
            }
        } catch (SQLException e) {
            System.err.println("Error listar contrataciones: " + e.getMessage());
        }
        return lista;
    }

    public void agregar(Contratacion c) {
        String sql = "INSERT INTO Contrataciones (idDepartamento, idEmpleado, idCargo, idTipoContratacion, fechaContracion, salario, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, c.getDepartamento().getIdDepartamento());
            ps.setInt(2, c.getEmpleado().getIdEmpleado());
            ps.setInt(3, c.getCargo().getIdCargo());
            ps.setInt(4, c.getTipoContratacion().getIdTipoContratacion());
            ps.setDate(5, c.getFechaContracion());
            ps.setDouble(6, c.getSalario());
            ps.setBoolean(7, c.isEstado());
            
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error agregar contratacion: " + e.getMessage());
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM Contrataciones WHERE idContratacion = ?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error eliminar contratacion: " + e.getMessage());
            return false;
        }
    }
}