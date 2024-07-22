package com.DesignPatterns.services.admin;

import com.DesignPatterns.Conexion.Conexion;
import com.DesignPatterns.models.Lecciones;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LeccionesService {
    Connection conn;

    public int createLeccion(String nombre, String descripcion, String video_url, int nivel) {
        int resultado = 0;
        try {
            conn = Conexion.connectar();
            assert conn != null;
            Statement stmt = conn.createStatement();
            String query = "CALL CrearLeccion('" + nombre + "', '" + descripcion + "', '" + video_url + "', " + nivel + ")";
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

    public Lecciones readLeccion(int idLeccion) {
        Lecciones leccion = null;
        try {
            conn = Conexion.connectar();
            String query = "CALL ObtenerLeccion(" + idLeccion + ")";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                String video_url = rs.getString("video_url");
                int nivel = rs.getInt("nivel");
                leccion = new Lecciones(idLeccion, nombre, descripcion, video_url, nivel);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return leccion;
    }

    public int updateLeccion(int idLeccion, String nombre, String descripcion, String video_url, int nivel) {
        int resultado = 0;
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL ActualizarLeccion(" + idLeccion + ", '" + nombre + "', '" + descripcion + "', '" + video_url + "', " + nivel + ")";
            resultado = statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultado;
    }

    public int deleteLeccion(int idLeccion) {
        int resultado = 0;
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL EliminarLeccion(" + idLeccion + ")";
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

