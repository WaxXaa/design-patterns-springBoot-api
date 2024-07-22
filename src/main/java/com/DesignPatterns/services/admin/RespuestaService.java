package com.DesignPatterns.services.admin;

import com.DesignPatterns.Conexion.Conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RespuestaService {
    // Métodos CRUD para la tabla Respuestas
    Connection conn;

    public int createRespuesta(String respuesta, String imagenUrl, boolean correcta, boolean tipo, int pregunta) {
        int idRespuesta = -1;
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL CreateRespuesta('" + respuesta + "', '" + imagenUrl + "', " + correcta + ", " + tipo + ", " + pregunta + ")";
            statement.execute(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                idRespuesta = rs.getInt(1);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return idRespuesta;
    }

    public void readRespuesta(int idRespuesta) {
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL ReadRespuesta(" + idRespuesta + ")";
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                String respuesta = rs.getString("respuesta");
                String imagenUrl = rs.getString("imagenURL");
                boolean correcta = rs.getBoolean("correcta");
                boolean tipo = rs.getBoolean("tipo");
                int pregunta = rs.getInt("pregunta");
                System.out.println("Respuesta: " + respuesta + ", Imagen URL: " + imagenUrl + ", Correcta: " + correcta + ", Tipo: " + tipo + ", Pregunta: " + pregunta);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateRespuesta(int idRespuesta, String respuesta, String imagenUrl, boolean correcta, boolean tipo, int pregunta) {
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL UpdateRespuesta(" + idRespuesta + ", '" + respuesta + "', '" + imagenUrl + "', " + correcta + ", " + tipo + ", " + pregunta + ")";
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteRespuesta(int idRespuesta) {
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL DeleteRespuesta(" + idRespuesta + ")";
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
