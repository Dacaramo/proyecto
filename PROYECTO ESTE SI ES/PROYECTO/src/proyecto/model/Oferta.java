package com.mycompany.ofertas;

import com.mycompany.ofertas.enums.Sector;
import java.io.Serializable;
import java.util.UUID;

public class Oferta implements Serializable{
    private String id;
    private String nombre;
    private Sector sector;
    private int edadRequerida;
    private float sueldo;
    private Empleador empleador;
    private ArrayList<Capacidad> capacidadesRequeridas;

    public Oferta(String nombre, Sector sector, int edadRequerida, float sueldo, Empleador empleador, ArrayList<Capacidad> capacidadesRequeridas) {
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.sector = sector;
        this.edadRequerida = edadRequerida;
        this.sueldo = sueldo;
        this.empleador = empleador;
        this.capacidadesRequeridas = requisitos;
    }

    public ArrayList<Capacidad> getCapacidadesRequeridas() {
        return capacidadesRequeridas;
    }

    public void setCapacidadesRequeridas(ArrayList<Capacidad> capacidadesRequeridas) {
        this.capacidadesRequeridas = capacidadesRequeridas;
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

    public Empleador getEmpleador() {
        return empleador;
    }

    public void setEmpleador(Empleador empleador) {
        this.empleador = empleador;
    }  
}
