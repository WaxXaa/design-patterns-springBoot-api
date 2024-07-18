package com.DesignPatterns.models;

public class Lecciones {
    private int id;
    private String nombre;
    private String descripcion;
    private String imagen;
    private String video;
    private Niveles nivel;

    public Lecciones(int id, String nombre, String descripcion, String imagen, String video, Niveles nivel) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.video = video;
        this.nivel = nivel;
    }
    public Lecciones(String nombre, String descricion, String imagen, String video, Niveles nivel) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.video = video;
        this.nivel = nivel;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public Niveles getNivel() {
        return nivel;
    }

    public void setNivel(Niveles nivel) {
        this.nivel = nivel;
    }
}
