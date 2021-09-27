/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ofertas;

import java.io.Serializable;

/**
 *
 * @author juanc
 */
public class Aspirante implements Serializable{
    private int id;
    private String nombre;
    private String password;
    private int edad;

    public Aspirante() {
    }

    public Aspirante(int id, String nombre, String password, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    
   
    
    
}
