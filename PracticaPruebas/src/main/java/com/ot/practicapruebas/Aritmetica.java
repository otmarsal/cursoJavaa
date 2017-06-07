/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ot.practicapruebas;

import java.util.List;
import java.util.stream.Stream;

/**
 *
 * @author omarsal
 */
public class Aritmetica {

    public double media(List<Double> numero) {
        Double resultat = 0.0;
        if (numero == null) {
            throw new IllegalArgumentException();
        } else if (numero.isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            Long cont = numero.stream().filter(n -> n < 0.0).count();

            if (cont > 0) {
                throw new RuntimeException();
            }else{
                resultat = numero.stream().reduce(0.0, (a,b) -> a + b);
                return resultat / numero.size();
            }            
        }
    }
}
