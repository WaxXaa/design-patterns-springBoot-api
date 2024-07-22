package com.DesignPatterns.services.admin;

import com.DesignPatterns.Conexion.Conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PreguntaService {
    Connection conn;
    // Métodos CRUD para la tabla Preguntas

    public int createPregunta(String pregunta, String imagenUrl, int nivel, int tipo) {
        int idPregunta = -1;
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL CreatePregunta('" + pregunta + "', '" + imagenUrl + "', " + nivel + ", " + tipo + ")";
            statement.execute(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                idPregunta = rs.getInt(1);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return idPregunta;
    }

    public void readPregunta(int idPregunta) {
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL ReadPregunta(" + idPregunta + ")";
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                String pregunta = rs.getString("pregunta");
                String imagenUrl = rs.getString("imagen_url");
                int nivel = rs.getInt("nivel");
                int tipo = rs.getInt("tipo");
                System.out.println("Pregunta: " + pregunta + ", Imagen URL: " + imagenUrl + ", Nivel: " + nivel + ", Tipo: " + tipo);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updatePregunta(int idPregunta, String pregunta, String imagenUrl, int nivel, int tipo) {
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL UpdatePregunta(" + idPregunta + ", '" + pregunta + "', '" + imagenUrl + "', " + nivel + ", " + tipo + ")";
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deletePregunta(int idPregunta) {
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL DeletePregunta(" + idPregunta + ")";
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
