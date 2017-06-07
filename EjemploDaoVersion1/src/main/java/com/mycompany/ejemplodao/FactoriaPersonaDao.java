/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ejemplodao;

/**
 *
 * @author usuario
 */
public abstract class FactoriaPersonaDao {
    private static final PersonaDao DAO = new BdEnMemoria();
    public static PersonaDao get(){
        return DAO;
    }
}
