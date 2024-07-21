package com.DesignPatterns.models;

import java.util.List;

public class PreguntasDTO {
    private int id;
    private String pregunta;
    private String foto;
    private int nivel;
    private int tipo;
    private List<Respuesta> respuestas;


    public PreguntasDTO() {}
    public PreguntasDTO(int idPregunta, String pregunta, String imagenUrl, int nivel, int tipo) {
        this.id = idPregunta;
        this.pregunta = pregunta;
        this.foto = imagenUrl;
        this.nivel = nivel;
        this.tipo = tipo;
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

    public List<Respuesta> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<Respuesta> respuestas) {
        this.respuestas = respuestas;
    }
}
