package com.mycompany.ofertas;

import java.io.Serializable;

public class Aspirante implements Serializable{
    private int id;
    private String nombre;
    private String clave;
    private int edad;

    public Aspirante() {
    }

    public Aspirante(int id, String nombre, String clave, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.clave = clave;
        this.edad = edad;
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

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }  
}
