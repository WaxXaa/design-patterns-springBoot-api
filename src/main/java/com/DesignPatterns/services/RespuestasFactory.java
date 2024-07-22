package com.DesignPatterns.services;

import com.DesignPatterns.models.Respuestas;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RespuestasFactory {
    public static Respuestas crearRespuesta(ResultSet res) throws SQLException {
        Respuestas respuesta = new Respuestas();
        respuesta.setId(res.getInt("IdRespuesta"));
        respuesta.setCorrecta(res.getBoolean("Correcta"));
        respuesta.setRespuesta(res.getString("Respuesta"));

         return respuesta;
    }
}
