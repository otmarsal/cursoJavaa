/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.singleton;

/**
 *
 * @author omarsal
 */

public class Persona {
    
    
    private String nom="abc";
    private int edat;
    
    
    private static Persona p = new Persona();
    
    private Persona(){
        this.edat = 10;
    }    //poniendo private no se podra hacer "new Persona()" en otras clases
    
    /**
     * Funcio per instanciar l'objecte
     */
    public static Persona getInstance(){
        return p;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getEdat() {
        return edat;
    }

    public void setEdat(int edat) {
        this.edat = edat;
    }

    public static Persona getP() {
        return p;
    }

    public static void setP(Persona p) {
        Persona.p = p;
    }
    
    
    
    
    
    
    
}
