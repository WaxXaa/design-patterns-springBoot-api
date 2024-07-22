package com.DesignPatterns.services;

import com.DesignPatterns.models.Respuesta;
import com.DesignPatterns.models.RespuestasDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RespuestasFactory {
    public static RespuestasDTO crearRespuesta(ResultSet res) throws SQLException {
        RespuestasDTO respuesta = new RespuestasDTO();
        respuesta.setId(res.getInt("IdRespuesta"));
        respuesta.setCorrecta(res.getBoolean("Correcta"));
        respuesta.setRespuesta(res.getString("Respuesta"));

         return respuesta;
    }
}
