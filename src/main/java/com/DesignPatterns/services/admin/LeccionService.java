package com.DesignPatterns.services.admin;

import com.DesignPatterns.Conexion.Conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LeccionService {
    Connection conn;
    public int createLeccion(String nombre, String descripcion, String imagenUrl, String videoUrl, int nivel) {
        int idLeccion = -1;
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL CreateLeccion('" + nombre + "', '" + descripcion + "', '" + imagenUrl + "', '" + videoUrl + "', " + nivel + ")";
            statement.execute(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                idLeccion = rs.getInt(1);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return idLeccion;
    }

    public void readLeccion(int idLeccion) {
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL ReadLeccion(" + idLeccion + ")";
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                String imagenUrl = rs.getString("imagen_url");
                String videoUrl = rs.getString("video_url");
                int nivel = rs.getInt("nivel");
                System.out.println("Nombre: " + nombre + ", Descripción: " + descripcion + ", Imagen URL: " + imagenUrl + ", Video URL: " + videoUrl + ", Nivel: " + nivel);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateLeccion(int idLeccion, String nombre, String descripcion, String imagenUrl, String videoUrl, int nivel) {
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL UpdateLeccion(" + idLeccion + ", '" + nombre + "', '" + descripcion + "', '" + imagenUrl + "', '" + videoUrl + "', " + nivel + ")";
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteLeccion(int idLeccion) {
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL DeleteLeccion(" + idLeccion + ")";
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
