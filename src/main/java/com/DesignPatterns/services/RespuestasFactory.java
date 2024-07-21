package com.DesignPatterns.services;

import com.DesignPatterns.models.Respuesta;
import com.DesignPatterns.models.RespuestasDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RespuestasFactory {
    public static RespuestasDTO crearRespuesta(ResultSet res, int tipoPregunta) throws SQLException {
        RespuestasDTO respuesta = new RespuestasDTO();
        respuesta.setId(res.getInt("IdRespuesta"));
        respuesta.setFoto(res.getString("ImagenRespuesta"));
         switch (tipoPregunta) {
             case 1:
                 respuesta.setCorrecta(res.getBoolean("Correcta"));
                 respuesta.setRespuesta(res.getString("Respuesta"));
                 break;
             case 2:
                 respuesta.setOrden(res.getInt("Orden"));
                 respuesta.setRespuesta(res.getString("RespuestaOrdenada"));
                 break;
             case 3:
                 respuesta.setCierto(res.getBoolean("Correcta"));
                 respuesta.setRespuesta(res.getString("Respuesta"));
                break;
         }
         return respuesta;
    }
}
