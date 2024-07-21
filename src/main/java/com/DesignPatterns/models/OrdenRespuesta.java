package com.DesignPatterns.models;

public class OrdenRespuesta {
    private int idOrden;
    private String respuesta;
    private int pregunta;
    private String imagenUrl;
    private int orden;

    public OrdenRespuesta() {}

    public OrdenRespuesta(int idOrden, String respuesta, int pregunta, String imagenUrl, int orden) {
        this.idOrden = idOrden;
        this.respuesta = respuesta;
        this.pregunta = pregunta;
        this.imagenUrl = imagenUrl;
        this.orden = orden;
    }

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public int getPregunta() {
        return pregunta;
    }

    public void setPregunta(int pregunta) {
        this.pregunta = pregunta;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }
}
