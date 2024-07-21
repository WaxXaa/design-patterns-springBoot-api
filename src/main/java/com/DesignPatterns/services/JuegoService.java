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
                        res.getInt("id_etapas"),
                        res.getString("nombre"),
                        res.getString("descripcion")
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
                    etapa = new Etapas(idEtapa, res.getString("NombreEtapa"), res.getString("DescripcionEtapa"));
                    etapa.setNiveles(new ArrayList<>());
                    etapasMap.put(idEtapa, etapa);
                }

                Niveles nivel = new Niveles(res.getInt("id_nivel"), res.getString("NombreNivel"), res.getString("DescripciionNivel"), res.getInt("exp"));


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
                    "    r.imagenURL AS ImagenRespuesta,\n" +
                    "    r.correcta AS Correcta,\n" +
                    "    o.respuesta AS RespuestaOrdenada,\n" +
                    "    o.orden AS Orden\n" +
                    "FROM\n" +
                    "    Preguntas p\n" +
                    "    JOIN Tipo_Preguntas tp ON p.tipo = tp.id_tipo\n" +
                    "    LEFT JOIN Respuestas r ON p.id_pregunta = r.pregunta AND tp.tipo IN ('Escoge la respuesta correcta', 'Cierto y Falso')\n" +
                    "    LEFT JOIN Orden_Respuestas o ON p.id_pregunta = o.pregunta AND tp.tipo = 'Ordena la respuesta'\n" +
                    "WHERE\n" +
                    "    p.nivel = "+idNivel+";";
            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(query);
            Map<Integer, PreguntasDTO> preguntasMap = new HashMap<>();
            while (res.next()) {
                int idPregunta = res.getInt("id_pregunta");
                PreguntasDTO pregunta = preguntasMap.get(idPregunta);
                if(pregunta == null) {
                    pregunta = new PreguntasDTO();
                    pregunta.setId(idPregunta);
                    pregunta.setPregunta(res.getString("Pregunta"));
                    pregunta.setTipo(res.getInt("TipoPregunta"));
                    pregunta.setFoto(res.getString("ImagenPregunta"));
                    pregunta.setRespuestas(new ArrayList<>());
                }
                RespuestasDTO respuesta = RespuestasFactory.crearRespuesta(res,idPregunta);
                pregunta.getRespuestas().add(respuesta);
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


  /*  public int actualizarExpUsuario(int idUsuario){
        try {

        } catch (SQLException e) {
            throw new Exception();
        } catch (Exception e) {

        }
    }*/

}
