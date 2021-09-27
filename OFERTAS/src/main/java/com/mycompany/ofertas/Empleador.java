/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ofertas;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author juanc
 */
public class Empleador implements Serializable {
    private int id;
    private String nombre;
    private String password;
    private ArrayList<Oferta> ofertas;

    public Empleador() {
    }

    public Empleador(int id, String nombre, String password, ArrayList<Oferta> ofertas) {
        this.id = id;
        this.nombre = nombre;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Oferta> getOfertas() {
        return ofertas;
    }

    public void setOfertas(ArrayList<Oferta> ofertas) {
        this.ofertas = ofertas;
    }

 
   

    
}
