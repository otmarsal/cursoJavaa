/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ejemplodao;

import static java.lang.System.out;
import java.util.Arrays;
import java.util.Scanner;
import static org.apache.commons.lang3.RandomStringUtils.*;

/**
 *
 * @author usuario
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Arrays.asList(random(16, true, true))
                .stream().map(s -> new Persona(s))
                .forEach(persona -> FactoriaPersonaDao.get().nueva(persona));
        
        
        
        FactoriaPersonaDao.get().todos().forEach(out::println);
        
        System.out.println("BORRAR TODO? S/N");
        Scanner sc = new Scanner(System.in);
        String e = sc.next();
        
        if(e.equalsIgnoreCase("S")){
            FactoriaPersonaDao.get().borrarTodo();
        }else if(e.equalsIgnoreCase("N")){
            
        }
    }

}
