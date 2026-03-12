package com.udb.dao;

import com.udb.conf.Conexion;
import com.udb.model.TipoContratacion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TipoContratacion1 {

    // insertar un nuevo tipo de contratacion
    public boolean insertar(TipoContratacion tc) {
        String sql = "INSERT INTO TipoContratacion (tipoContratacion) VALUES (?)";
        try {
            Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tc.getTipoContratacion());
            int r = ps.executeUpdate();
            con.close();
            return r > 0;
        } catch (SQLException e) {
            System.out.println("Error insertar: " + e.getMessage());
            return false;
        }
    }

    // obtener todos los registros
    public List<TipoContratacion> listar() {
        List<TipoContratacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM TipoContratacion";
        try {
            Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TipoContratacion tc = new TipoContratacion();
                tc.setIdTipoContratacion(rs.getInt("idTipoContratacion"));
                tc.setTipoContratacion(rs.getString("tipoContratacion"));
                lista.add(tc);
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error listar: " + e.getMessage());
        }
        return lista;
    }

    // obtener un tipo por su id
    public TipoContratacion obtenerPorId(int id) {
        TipoContratacion tc = null;
        String sql = "SELECT * FROM TipoContratacion WHERE idTipoContratacion = ?";
        try {
            Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tc = new TipoContratacion();
                tc.setIdTipoContratacion(rs.getInt("idTipoContratacion"));
                tc.setTipoContratacion(rs.getString("tipoContratacion"));
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error obtener: " + e.getMessage());
        }
        return tc;
    }

    // actualizar un tipo de contratacion
    public boolean actualizar(TipoContratacion tc) {
        String sql = "UPDATE TipoContratacion SET tipoContratacion = ? WHERE idTipoContratacion = ?";
        try {
            Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tc.getTipoContratacion());
            ps.setInt(2, tc.getIdTipoContratacion());
            int r = ps.executeUpdate();
            con.close();
            return r > 0;
        } catch (SQLException e) {
            System.out.println("Error actualizar: " + e.getMessage());
            return false;
        }
    }

    // eliminar por id
    public boolean eliminar(int id) {
        String sql = "DELETE FROM TipoContratacion WHERE idTipoContratacion = ?";
        try {
            Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int r = ps.executeUpdate();
            con.close();
            return r > 0;
        } catch (SQLException e) {
            System.out.println("Error eliminar: " + e.getMessage());
            return false;
        }
    }
}