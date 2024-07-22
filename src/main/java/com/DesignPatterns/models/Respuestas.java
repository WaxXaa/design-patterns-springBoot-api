package com.DesignPatterns.models;

public class Respuestas {
    private int id;
    private String respuesta;
    private boolean correcta;
    private int pregunta;

    public Respuestas() {}

    public Respuestas(int idRespuesta, String respuesta, boolean correcta, int pregunta) {
        this.id = idRespuesta;
        this.respuesta = respuesta;
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
