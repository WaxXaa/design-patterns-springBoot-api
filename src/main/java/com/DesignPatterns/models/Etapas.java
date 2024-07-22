package com.DesignPatterns.models;

import java.util.ArrayList;

public class Etapas {
    private int id;
    private String nombre;
    private String descripcion;
    private String imagen_url;
    private ArrayList<Niveles> niveles;

    public Etapas(int id, String nombre, String descripcion, String imagen_url) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen_url = imagen_url;
    }
    public Etapas(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImage_url() {
        return imagen_url;
    }

    public void setImage_url(String image_url) {
        this.imagen_url = image_url ;
    }



    public ArrayList<Niveles> getNiveles() {
        return niveles;
    }

    public void setNiveles(ArrayList<Niveles> niveles) {
        this.niveles = niveles;
    }
}
