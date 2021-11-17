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

/**
 *
 * @author HP-PC
 */
public class Solicitud implements Serializable {
    String id;
    String aspirante;
    String nombreOferta;
    ArrayList<Capacidad> capacidades = new ArrayList<>();

    public Solicitud(String id, String aspirante, String nombreOferta) {
        this.id = UUID.randomUUID().toString();;
        this.aspirante = aspirante;
        this.nombreOferta = nombreOferta;
    }
 

    

    public Solicitud() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAspirante() {
        return aspirante;
    }

    public void setAspirante(String aspirante) {
        this.aspirante = aspirante;
    }

    public String getNombreOferta() {
        return nombreOferta;
    }

    public void setNombreOferta(String nombreOferta) {
        this.nombreOferta = nombreOferta;
    }

    public ArrayList<Capacidad> getCapacidades() {
        return capacidades;
    }

    public void setCapacidades(ArrayList<Capacidad> capacidades) {
        this.capacidades = capacidades;
    }

    @Override
    public String toString() {
        String tos = this.id+","+this.aspirante+","+this.nombreOferta+","+this.capacidades.size()+",";
        for(Capacidad ca: this.capacidades){
            tos += ca.toString()+",";
        }
        return tos;
    }
    
    
    
    
    
}
