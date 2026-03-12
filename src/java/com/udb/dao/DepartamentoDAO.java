package com.udb.dao;

import com.udb.conf.Conexion;
import com.udb.model.Departamento;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartamentoDAO {

    public boolean insertar(Departamento departamento) {
        String sql = "INSERT INTO Departamento (nombreDepartamento, descripcionDepartamento) VALUES (?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, departamento.getNombreDepartamento());
            ps.setString(2, departamento.getDescripcionDepartamento());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al insertar departamento: " + e.getMessage());
            return false;
        }
    }

    public List<Departamento> listar() {
        List<Departamento> lista = new ArrayList<>();
        String sql = "SELECT * FROM Departamento";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Departamento d = new Departamento();
                d.setIdDepartamento(rs.getInt("idDepartamento"));
                d.setNombreDepartamento(rs.getString("nombreDepartamento"));
                d.setDescripcionDepartamento(rs.getString("descripcionDepartamento"));
                lista.add(d);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar departamentos: " + e.getMessage());
        }

        return lista;
    }

    public Departamento obtenerPorId(int id) {
        Departamento d = null;
        String sql = "SELECT * FROM Departamento WHERE idDepartamento = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    d = new Departamento();
                    d.setIdDepartamento(rs.getInt("idDepartamento"));
                    d.setNombreDepartamento(rs.getString("nombreDepartamento"));
                    d.setDescripcionDepartamento(rs.getString("descripcionDepartamento"));
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener departamento: " + e.getMessage());
        }

        return d;
    }

    public boolean actualizar(Departamento departamento) {
        String sql = "UPDATE Departamento SET nombreDepartamento = ?, descripcionDepartamento = ? WHERE idDepartamento = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, departamento.getNombreDepartamento());
            ps.setString(2, departamento.getDescripcionDepartamento());
            ps.setInt(3, departamento.getIdDepartamento());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar departamento: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM Departamento WHERE idDepartamento = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar departamento: " + e.getMessage());
            return false;
        }
    }
}