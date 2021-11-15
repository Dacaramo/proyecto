/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;
import model.enums.Capacidad;
import model.enums.Sector;

/**
 *
 * @author HP-PC
 */
public class Oferta implements Serializable {
    private String id;
    private String nombre;
    private Sector sector;
    private int edadRequerida;
    private float sueldo;
    private String Idempleador;
    private ArrayList<Capacidad> capacidadesRequeridas;

    public Oferta(String id, String nombre, Sector sector, int edadRequerida, float sueldo, String Idempleador, ArrayList<Capacidad> capacidadesRequeridas) {
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.sector = sector;
        this.edadRequerida = edadRequerida;
        this.sueldo = sueldo;
        this.Idempleador = Idempleador;
        this.capacidadesRequeridas = capacidadesRequeridas;
    }

    
    
    public Oferta() {
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

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public int getEdadRequerida() {
        return edadRequerida;
    }

    public void setEdadRequerida(int edadRequerida) {
        this.edadRequerida = edadRequerida;
    }

    public float getSueldo() {
        return sueldo;
    }

    public void setSueldo(float sueldo) {
        this.sueldo = sueldo;
    }

    public String getIdempleador() {
        return Idempleador;
    }

    public void setIdempleador(String Idempleador) {
        this.Idempleador = Idempleador;
    }

    public ArrayList<Capacidad> getCapacidadesRequeridas() {
        return capacidadesRequeridas;
    }

    public void setCapacidadesRequeridas(ArrayList<Capacidad> capacidadesRequeridas) {
        this.capacidadesRequeridas = capacidadesRequeridas;
    }
    

    
}
