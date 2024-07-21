package com.DesignPatterns.models;

public class Respuesta {
    private int id;
    private String respuesta;
    private String foto;
    private boolean correcta;
    private int pregunta;

    public Respuesta() {}

    public Respuesta(int idRespuesta, String respuesta, String imagenUrl, boolean correcta, int pregunta) {
        this.id = idRespuesta;
        this.respuesta = respuesta;
        this.foto = imagenUrl;
        this.correcta = correcta;
        this.pregunta = pregunta;
    }

    public int getId() {
        return id;
    }

    public void setId(int idRespuesta) {
        this.id = idRespuesta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String imagenUrl) {
        this.foto = imagenUrl;
    }

    public boolean getCorrecta() {
        return correcta;
    }

    public void setCorrecta(boolean correcta) {
        this.correcta = correcta;
    }

    public int getPregunta() {
        return pregunta;
    }

    public void setPregunta(int pregunta) {
        this.pregunta = pregunta;
    }
}
