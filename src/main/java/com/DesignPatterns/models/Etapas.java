package com.DesignPatterns.models;

import java.util.ArrayList;
import java.util.List;

public class Etapas {
    private int id;
    private String nombre;
    private String descripcion;
    private ArrayList<Niveles> niveles;

    public Etapas(int id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    public Etapas(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        descripcion = descripcion;
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

    public ArrayList<Niveles> getNiveles() {
        return niveles;
    }

    public void setNiveles(ArrayList<Niveles> niveles) {
        this.niveles = niveles;
    }
}
