package com.DesignPatterns.services.admin;

import com.DesignPatterns.Conexion.Conexion;
import com.DesignPatterns.models.Ayuda;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AyudaService {
    Connection conn;

    public List<Ayuda> readAyuda() throws Exception{
        List<Ayuda> lAyudas = new ArrayList<>();
        try {
            conn = Conexion.connectar();
            assert conn != null;
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM Ayuda;";
            ResultSet res=stmt.executeQuery(query);
            while (res.next()) {
                Ayuda ayuda = new Ayuda();
                ayuda.setId(res.getInt("id"));
                ayuda.setPregunta(res.getString("pregunta"));
                ayuda.setRespuesta(res.getString("respuesta"));
                lAyudas.add(ayuda);
            }
            conn.close();
            return lAyudas;
        } catch (SQLException e) {
            throw new Exception(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    
    }

    public int updateAyuda(int id, String pregunta, String respuesta) {
        int resultado = 0;
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL ActualizarAyuda(" + id + ", '" + pregunta + "', '" + respuesta + "')";
            resultado = statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultado;
    }

    public int deleteAyuda(int id) {
        int resultado = 0;
        try {
            conn = Conexion.connectar();
            Statement statement = conn.createStatement();
            String query = "CALL EliminarAyuda(" + id + ")";
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
