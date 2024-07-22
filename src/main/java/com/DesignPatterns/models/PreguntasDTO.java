package com.DesignPatterns.models;

import java.util.List;

public class PreguntasDTO {
    private int id;
    private String pregunta;
    private int tipo;
    private String foto;
    private int nivel;
    private List<RespuestasDTO> respuestas;


    public PreguntasDTO() {}
    public PreguntasDTO(int idPregunta, String pregunta, int tipo, String imagenUrl, int nivel) {
        this.id = idPregunta;
        this.pregunta = pregunta;
        this.tipo = tipo;
        this.foto = imagenUrl;
        this.nivel = nivel;        
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getNivel() {return nivel;}

    public void setNivel(int nivel) {this.nivel = nivel;}

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public List<RespuestasDTO> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<RespuestasDTO> respuestas) {
        this.respuestas = respuestas;
    }
}
