/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * @author HP-PC
 */
public class Empleador {
    private String id;
    private String nombre;
    private String clave;
    private ArrayList<Oferta> ofertas; //El empleador genera ofertas

    public Empleador(String nombre, String clave, ArrayList<Oferta> ofertas) {
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.clave = clave;
        this.ofertas = ofertas;
    }

    public Empleador() {
        this.id = UUID.randomUUID().toString();
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    @Override
    public String toString() {
        return "emp"+","+this.id+","+this.nombre+","+this.clave;
    }
    
}
