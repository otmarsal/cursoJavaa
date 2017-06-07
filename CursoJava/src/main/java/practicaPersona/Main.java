/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicaPersona;

import java.util.List;

/**
 *
 * @author gcoprea
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        PersonaDao pd = Factoria.get();
        
        Persona p1 = new Persona("Persona1","Apellido1",21,"Hombre");
        Persona p2 = new Persona("Persona2","Apellido2",18,"Mujer");
        pd.nueva(p1);
        pd.nueva(p2);
        
        List<Persona> listP;
        listP = pd.todos();
        System.out.println(listP);
    }
    
}
