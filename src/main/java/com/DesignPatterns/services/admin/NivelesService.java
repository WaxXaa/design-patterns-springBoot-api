package com.DesignPatterns.services.admin;

import com.DesignPatterns.Conexion.Conexion;
import com.DesignPatterns.models.Niveles;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class NivelesService {
    Connection conn;

    public int createNivel(String nombre, String descripcion) {
        int resultado = 0;
        try {
            conn = Conexion.connectar();
            assert conn != null;
            Statement stmt = conn.createStatement();
            String query = "CALL CrearNivel('" + nombre + "', '" + descripcion + "')";
            stmt.execute(query);
            conn.close();
            return resultado;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public Niveles readNivel(int idNivel) {
        Niveles nivel = null;
        try {
            conn = Conexion.connectar();
            String query = "CALL ObtenerNivel(" + idNivel + ")";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                nivel = new Niveles(idNivel, nombre, descripcion, 0);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return nivel;
    }

    public int updateNivel(int idNivel, String nombre, String descripcion) {
        int resultado = 0;
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL ActualizarNivel(" + idNivel + ", '" + nombre + "', '" + descripcion + "')";
            resultado = statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultado;
    }

    public int deleteNivel(int idNivel) {
        int resultado = 0;
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL EliminarNivel(" + idNivel + ")";
            resultado = statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultado;
    }
}


