package com.DesignPatterns.models;

public class Usuario_set_get {
    private int id;
    private String nombre;
    private String apellido;
    private String email;
    private String contra;
    private int puntaje;

    public Usuario_set_get(int id, String nombre, String apellido, String email, String contra, int puntaje) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
    }
    public Usuario_set_get(String nombre, String apellido, String email, String contra, int puntaje) {
        this.id = id;
    }
    public Usuario_set_get(int id, String nombre, String apellido, String email, String contra) {
        this.id = id;
    }
}
