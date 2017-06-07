/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicaPersona;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gcoprea
 */
public class Factoria implements PersonaDao {
    
    List<Persona> personas = new ArrayList<>();

    @Override
    public Persona nueva(Persona p) {
       personas.add(p);
       return p;
    }

    @Override
    public List<Persona> todos() {
        return personas;
    }
    
    public static PersonaDao get(){
        return new Factoria();
    }
    
}
