package com.mycompany.ofertas;

import java.io.Serializable;
import java.util.ArrayList;

public class Empleador implements Serializable {
    private int id;
    private String nombre;
    private String clave;
    private ArrayList<Oferta> ofertas;

    public Empleador() {
    }

    public Empleador(int id, String nombre, String clave, ArrayList<Oferta> ofertas) {
        this.id = id;
        this.nombre = nombre;
        this.clave = clave;
        this.ofertas = ofertas;
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

    public ArrayList<Oferta> getOfertas() {
        return ofertas;
    }

    public void setOfertas(ArrayList<Oferta> ofertas) {
        this.ofertas = ofertas;
    }
}
