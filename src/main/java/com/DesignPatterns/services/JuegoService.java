package com.DesignPatterns.services;

import com.DesignPatterns.Conexion.Conexion;
import com.DesignPatterns.exceptions.DataBaseException;
import com.DesignPatterns.models.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JuegoService {
    Connection conn;
    public List<Etapas> obtenerEtapas() throws Exception{
        List<Etapas> listaEtapas = new ArrayList<>();
        try{
            conn = Conexion.connectar();
            assert conn != null;
            Statement stm = conn.createStatement();
            String query = "SELECT * FROM Etapas;";
            ResultSet res = stm.executeQuery(query);
            while (res.next()){
                Etapas etapa = new Etapas(
                        res.getInt("id_etapa"),
                        res.getString("nombre"),
                        res.getString("descripcion"),
                        res.getString("image_url")
                );
                listaEtapas.add(etapa);
            }
            conn.close();
            stm.close();
            return listaEtapas;
        }catch (SQLException e){
            throw new DataBaseException(e.getMessage());
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    public List<Etapas> obtenerEtapasNiveles() throws Exception {
        try {
            conn = Conexion.connectar();
            assert conn != null;
            Statement stm = conn.createStatement();
            String query = "SELECT e.id_etapa, e.nombre AS NombreEtapa, e.descripcion AS DescripcionEtapa, "
                    + "n.id_nivel, n.nombre AS NombreNivel, n.descripcion AS DescripcionNivel, n.exp "
                    + "FROM Etapas e "
                    + "JOIN Niveles n ON e.id_etapa = n.etapa "
                    + "ORDER BY e.id_etapa, n.id_nivel;";
            ResultSet res = stm.executeQuery(query);

            Map<Integer, Etapas> etapasMap = new HashMap<>();

            while (res.next()) {
                int idEtapa = res.getInt("id_etapa");
                Etapas etapa = etapasMap.get(idEtapa);

                if (etapa == null) {
                    etapa = new Etapas(idEtapa, res.getString("NombreEtapa"), res.getString("DescripcionEtapa"), res.getString("image_url"));
                    etapa.setNiveles(new ArrayList<>());
                    etapasMap.put(idEtapa, etapa);
                }

                Niveles nivel = new Niveles(res.getInt("id_nivel"), res.getString("NombreNivel"), res.getString("DescripcionNivel"), res.getInt("exp"));


                etapa.getNiveles().add(nivel);
            }
            conn.close();
            stm.close();
            return new ArrayList<>(etapasMap.values());

        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    public List<PreguntasDTO> obtenerPreguntas(int idNivel) throws Exception{
        try {
            conn = Conexion.connectar();
            assert conn != null;
            String query = "SELECT\n" +
                    "    p.id_pregunta,\n" +
                    "    p.pregunta AS Pregunta,\n" +
                    "    p.tipo AS TipoPregunta,\n" +
                    "    p.imagen_url AS ImagenPregunta,\n" +
                    "    r.id_respuesta AS IdRespuesta,\n" +
                    "    r.respuesta AS Respuesta,\n" +
                    "    r.correcta AS Correcta\n" +
                    "FROM\n" +
                    "    Preguntas p\n" +
                    "    LEFT JOIN Respuestas r ON p.id_pregunta = r.pregunta\n" +
                    "WHERE p.nivel = "+idNivel+";";
            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(query);
            Map<Integer, PreguntasDTO> preguntasMap = new HashMap<>();
            while (res.next()) {
                int idPregunta = res.getInt("id_pregunta");
                PreguntasDTO pregunta = preguntasMap.get(idPregunta);
                if(pregunta == null) {
                    System.out.println("entro al ciclo");
                    pregunta = new PreguntasDTO();
                    pregunta.setId(idPregunta);
                    pregunta.setPregunta(res.getString("Pregunta"));
                    pregunta.setTipo(res.getInt("TipoPregunta"));
                    pregunta.setFoto(res.getString("ImagenPregunta"));
                    pregunta.setRespuestas(new ArrayList<>());
                }
                RespuestasDTO respuesta = RespuestasFactory.crearRespuesta(res);
                pregunta.getRespuestas().add(respuesta);
                preguntasMap.put(pregunta.getId(), pregunta);

            }
            conn.close();
            stm.close();
            return new ArrayList<>(preguntasMap.values());
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }


    public int actualizarExpUsuario(int idUsuario) throws Exception{
        int resultado = 0;
        try {
        conn = Conexion.connectar();
        Statement stm = conn.createStatement();
        String query = "CALL incrementar_Exp("+idUsuario+");";
        resultado = stm.executeUpdate(query);
        return resultado;
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
