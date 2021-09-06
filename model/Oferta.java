package model;

import model.enums.Sector;

public class Oferta {
    private String id;
    private String nombre;
    private Sector sector;
    private Empleador empleador;
    private int edadRequerida;
    private float sueldo;

    public Oferta() {
    }

    public Oferta(String id, String nombre, Sector sector, Empleador empleador, int edadRequerida, float sueldo) {
        this.id = id;
        this.nombre = nombre;
        this.sector = sector;
        this.empleador = empleador;
        this.edadRequerida = edadRequerida;
        this.sueldo = sueldo;
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

    public Empleador getEmpleador() {
        return empleador;
    }

    public void setEmpleador(Empleador empleador) {
        this.empleador = empleador;
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
}
