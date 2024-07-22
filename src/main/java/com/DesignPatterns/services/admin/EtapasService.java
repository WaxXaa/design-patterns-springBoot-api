package com.DesignPatterns.services.admin;

import com.DesignPatterns.Conexion.Conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EtapasService {
    Connection conn;
    public int createEtapa(String nombre, String descripcion) {
        int resultado = 0;
        try {
            conn = Conexion.connectar();
            assert conn != null;
            Statement stmt = conn.createStatement();
            String query = "CALL CreateEtapa('" + nombre + "', '" + descripcion + "')";
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

    public void readEtapa(int idEtapa) {
        try {
            conn = Conexion.connectar();
            String query = "CALL ReadEtapa(" + idEtapa + ")";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                System.out.println("Nombre: " + nombre + ", Descripción: " + descripcion);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateEtapa(int idEtapa, String nombre, String descripcion) {
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL UpdateEtapa(" + idEtapa + ", '" + nombre + "', '" + descripcion + "')";
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteEtapa(int idEtapa) {
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL DeleteEtapa(" + idEtapa + ")";
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
