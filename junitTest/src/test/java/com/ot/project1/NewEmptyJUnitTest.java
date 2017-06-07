/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ot.project1;

import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author omarsal
 */
public class NewEmptyJUnitTest {
    
    private Demo d;
    
    public NewEmptyJUnitTest() {
    }
    
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("BeforeClass");
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("AfterClass");
    }
    
    
    /**
     * INICIALIZAR LOS TESTS, ESTE METODO SE EJECUTA UNA VEZ POR CADA @TEST
     */
    @Before
    public void setUp() { 
        System.out.println("Before");
        d = new Demo();
    }
    
    /**
     * Coje la conexion y la cierra.
     */
    @After
    public void tearDown() {
        System.out.println("After");
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    
    /**
     * El metodo del Test, deberia lanzar una IOException
     */
     @Test(expected = IOException.class)
     public void getNombreTest() {
         System.out.println("Test Nombre");
         assertNull(d.getNombre());     
     }
     
     
     /**
      * Cuando JUNIT ejecuta el Test, tiene que hacerlo antes 3 segundos o falla. (timeout = 3000)
      */
     @Test(timeout = 3000)
     public void metodoIntegerTest() {
         System.out.println("Test Integer");
         assertEquals(new Integer(3), d.metodoInteger());
     }
}
