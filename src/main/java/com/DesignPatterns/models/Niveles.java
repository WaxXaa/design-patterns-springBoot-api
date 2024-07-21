package com.DesignPatterns.models;

public class Niveles {
    private int id;
    private String nombre;
    private String Descripcion;
    private int exp;
    private int etapa;



    //query
    public Niveles(int id, String nombre, String descripcion, int exp) {
        this.id = id;
        this.nombre = nombre;
        this.Descripcion = descripcion;
        this.exp = exp;
    }

    //Crear y actualizar
    public Niveles(String nombre, String descripcion) {
        this.nombre = nombre;
        this.Descripcion = descripcion;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
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

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getEtapa() {return etapa;}

    public void setEtapa(int etapa) {this.etapa = etapa;}


}
