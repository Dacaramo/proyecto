package com.mycompany.ofertas;

import com.mycompany.ofertas.enums.Sector;
import java.io.Serializable;

public class Aspirante implements Serializable{
    private int id;
    private String nombre;
    private String clave;
    private int edad;
    private Sector sector1;
    private Sector sector2;
    ;

    public Sector getSector1() {
        return sector1;
    }

    public void setSector1(Sector sector1) {
        this.sector1 = sector1;
    }

    public Sector getSector2() {
        return sector2;
    }

    public void setSector2(Sector sector2) {
        this.sector2 = sector2;
    }
    

    public Aspirante() {
    }

    public Aspirante(int id, String nombre, String clave, int edad, Sector sector1, Sector sector2) {
        this.id = id;
        this.nombre = nombre;
        this.clave = clave;
        this.edad = edad;
        this.sector1 = sector1.NINGUNO;
        this.sector2 = sector2.NINGUNO;
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
