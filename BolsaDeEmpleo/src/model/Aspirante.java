/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.UUID;
import model.enums.Capacidad;
import model.enums.Sector;

/**
 *
 * @author HP-PC
 */
public class Aspirante {
    private String id;
    private int nSubs;
    private String nombre;
    private String clave;
    private int edad;
    private Sector sector1;
    private Sector sector2;
    private ArrayList<Capacidad> capacidades;

    public Aspirante() {
        this.id = UUID.randomUUID().toString();
        this.sector1 = Sector.NINGUNO;
        this.sector2 = Sector.NINGUNO;
        this.nSubs = 0 ;
        this.capacidades = new ArrayList<>();
    }

    public Aspirante(int nSubs, String nombre, String clave, int edad, Sector sector1, Sector sector2, ArrayList<Capacidad> capacidades) {
        this.id = UUID.randomUUID().toString();
        this.nSubs = nSubs;
        this.nombre = nombre;
        this.clave = clave;
        this.edad = edad;
        this.sector1 = sector1;
        this.sector2 = sector2;
        this.capacidades = capacidades;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getnSubs() {
        return nSubs;
    }

    public void setnSubs(int nSubs) {
        this.nSubs = nSubs;
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

    public ArrayList<Capacidad> getCapacidades() {
        return capacidades;
    }

    public void setCapacidades(ArrayList<Capacidad> capacidades) {
        this.capacidades = capacidades;
    }

    @Override
    public String toString() {
        return "asp"+","+this.id+","+this.nombre+","+this.clave+","+this.edad;
    }
    
}
