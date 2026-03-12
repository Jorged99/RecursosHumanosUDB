package com.udb.dao;

import com.udb.conf.Conexion;
import com.udb.model.Cargo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CargoDAO {

    public boolean insertar(Cargo cargo) {

        String sql = "INSERT INTO Cargos (cargo, descripcionCargo, jefatura) VALUES (?, ?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cargo.getCargo());
            ps.setString(2, cargo.getDescripcionCargo());
            ps.setBoolean(3, cargo.isJefatura());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al insertar cargo: " + e.getMessage());
            return false;
        }
    }

    public List<Cargo> listar() {

        List<Cargo> lista = new ArrayList<>();
        String sql = "SELECT * FROM Cargos";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Cargo c = new Cargo();

                c.setIdCargo(rs.getInt("idCargo"));
                c.setCargo(rs.getString("cargo"));
                c.setDescripcionCargo(rs.getString("descripcionCargo"));
                c.setJefatura(rs.getBoolean("jefatura"));

                lista.add(c);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar cargos: " + e.getMessage());
        }

        return lista;
    }

    public boolean eliminar(int id) {

        String sql = "DELETE FROM Cargos WHERE idCargo = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar cargo: " + e.getMessage());
            return false;
        }
    }

    public Cargo obtenerPorId(int id) {

        Cargo c = null;

        String sql = "SELECT * FROM Cargos WHERE idCargo = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    c = new Cargo();

                    c.setIdCargo(rs.getInt("idCargo"));
                    c.setCargo(rs.getString("cargo"));
                    c.setDescripcionCargo(rs.getString("descripcionCargo"));
                    c.setJefatura(rs.getBoolean("jefatura"));
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener cargo: " + e.getMessage());
        }

        return c;
    }

    public boolean actualizar(Cargo cargo) {

        String sql = "UPDATE Cargos SET cargo=?, descripcionCargo=?, jefatura=? WHERE idCargo=?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cargo.getCargo());
            ps.setString(2, cargo.getDescripcionCargo());
            ps.setBoolean(3, cargo.isJefatura());
            ps.setInt(4, cargo.getIdCargo());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar cargo: " + e.getMessage());
            return false;
        }
    }
}