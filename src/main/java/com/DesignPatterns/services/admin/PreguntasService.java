package com.DesignPatterns.services.admin;

import com.DesignPatterns.Conexion.Conexion;
import com.DesignPatterns.models.PreguntasDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PreguntasService {
    Connection conn;

    public int createPregunta(String pregunta, int tipo, String imagen_url, int nivel) {
        int resultado = 0;
        try {
            conn = Conexion.connectar();
            assert conn != null;
            Statement stmt = conn.createStatement();
            String query = "CALL CrearPregunta('" + pregunta + "', " + tipo + ", '" + imagen_url + "', " + nivel + ")";
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

    public PreguntasDTO readPregunta(int idPregunta) {
        PreguntasDTO pregunta = null;
        try {
            conn = Conexion.connectar();
            String query = "CALL ObtenerPregunta(" + idPregunta + ")";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                String preguntaText = rs.getString("pregunta");
                int tipo = rs.getInt("tipo");
                String imagen_url = rs.getString("imagen_url");
                int nivel = rs.getInt("nivel");
                pregunta = new PreguntasDTO(idPregunta, preguntaText, tipo, imagen_url, nivel);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return pregunta;
    }

    public int updatePregunta(int idPregunta, String pregunta, int tipo, String imagen_url, int nivel) {
        int resultado = 0;
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL ActualizarPregunta(" + idPregunta + ", '" + pregunta + "', " + tipo + ", '" + imagen_url + "', " + nivel + ")";
            resultado = statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultado;
    }

    public int deletePregunta(int idPregunta) {
        int resultado = 0;
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL EliminarPregunta(" + idPregunta + ")";
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
