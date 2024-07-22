package com.DesignPatterns.services.admin;

import com.DesignPatterns.Conexion.Conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class NivelesService {
    Connection conn;
    public int createNivel(String nombre, String descripcion, int etapa, int exp) {
        int idNivel = -1;
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL CreateNivel('" + nombre + "', '" + descripcion + "', " + etapa + ", " + exp + ")";
            statement.execute(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                idNivel = rs.getInt(1);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return idNivel;
    }

    public void readNivel(int idNivel) {
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL ReadNivel(" + idNivel + ")";
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                int etapa = rs.getInt("etapa");
                int exp = rs.getInt("exp");
                System.out.println("Nombre: " + nombre + ", Descripción: " + descripcion + ", Etapa: " + etapa + ", EXP: " + exp);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateNivel(int idNivel, String nombre, String descripcion, int etapa, int exp) {
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL UpdateNivel(" + idNivel + ", '" + nombre + "', '" + descripcion + "', " + etapa + ", " + exp + ")";
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteNivel(int idNivel) {
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL DeleteNivel(" + idNivel + ")";
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
