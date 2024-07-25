package com.DesignPatterns.services.admin;

import com.DesignPatterns.Conexion.Conexion;
import com.DesignPatterns.models.Etapas;

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
            String query = "CALL CrearEtapa('" + nombre + "', '" + descripcion + "')";
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

        public Etapas readEtapa(int idEtapa) {
        Etapas etapa = null;
        try {
            conn = Conexion.connectar();
            String query = "CALL ObtenerEtapa(" + idEtapa + ")";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                String image_url = rs.getString("image_url");
                etapa = new Etapas(idEtapa, nombre, descripcion,image_url );
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return etapa;
    }

    public int updateEtapa(int idEtapa, String nombre, String descripcion, String image_url) throws Exception {
        int resultado = 0;
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL ActualizarEtapa(" + idEtapa + ", '" + nombre + "', '" + descripcion + "','" + image_url + "')";
            resultado = statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            throw new Exception(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultado;
    }

    public int deleteEtapa(int idEtapa) {
        int resultado = 0;
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL EliminarEtapa(" + idEtapa + ")";
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
