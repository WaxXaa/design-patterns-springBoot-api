package com.DesignPatterns.services.admin;

import com.DesignPatterns.Conexion.Conexion;
import com.DesignPatterns.models.Respuestas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RespuestasService {
    Connection conn;

    public int createRespuesta(String respuesta, boolean correcta, int pregunta) {
        int resultado = 0;
        try {
            conn = Conexion.connectar();
            assert conn != null;
            Statement stmt = conn.createStatement();
            String query = "CALL CrearRespuesta('" + respuesta + ", " + correcta + ", " + pregunta + ")";
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

    public Respuestas readRespuesta(int idRespuesta) {
        Respuestas respuesta = null;
        try {
            conn = Conexion.connectar();
            String query = "CALL ObtenerRespuesta(" + idRespuesta + ")";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                String respuestaText = rs.getString("respuesta");
                boolean correcta = rs.getBoolean("correcta");
                int pregunta = rs.getInt("pregunta");
                respuesta = new Respuestas(idRespuesta, respuestaText, correcta, pregunta);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return respuesta;
    }

    public int updateRespuesta(int idRespuesta, String respuesta, boolean correcta, int pregunta) {
        int resultado = 0;
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL ActualizarRespuesta(" + idRespuesta + ", '" + respuesta + "', " + correcta + ", " + pregunta + ")";
            resultado = statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultado;
    }

    public int deleteRespuesta(int idRespuesta) {
        int resultado = 0;
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL EliminarRespuesta(" + idRespuesta + ")";
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
