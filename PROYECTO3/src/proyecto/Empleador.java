/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import java.util.ArrayList;

/**
 *
 * @author juanc
 */
public class Empleador {
    private String id;
    private String nombre;
    private ArrayList<Oferta> ofertas;

    public Empleador() {
    }

    public Empleador(String id, String nombre, ArrayList<Oferta> ofertas) {
        this.id = id;
        this.nombre = nombre;
        this.ofertas = ofertas;
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

    public ArrayList<Oferta> getOfertas() {
        return ofertas;
    }

    public void setOfertas(ArrayList<Oferta> ofertas) {
        this.ofertas = ofertas;
    }
    
    
    
}
