/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.UUID;

/**
 *
 * @author HP-PC
 */
public class Solicitud {
    String id;
    Aspirante aspirante;
    String nombreOferta;

    public Solicitud(Aspirante aspirante, String nombreOferta) {
        this.id = UUID.randomUUID().toString();
        this.aspirante = aspirante;
        this.nombreOferta = nombreOferta;
    } 
}
