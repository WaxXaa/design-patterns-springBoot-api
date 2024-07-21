package com.DesignPatterns.models;

public class RespuestasDTO {
    private int id;
    private String respuesta;
    private String foto;
    private Boolean correcta;
    private int orden;
    private boolean cierto;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Boolean getCorrecta() {
        return correcta;
    }

    public void setCorrecta(Boolean correcta) {
        this.correcta = correcta;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public boolean isCierto() {
        return cierto;
    }

    public void setCierto(boolean cierto) {
        this.cierto = cierto;
    }
}
