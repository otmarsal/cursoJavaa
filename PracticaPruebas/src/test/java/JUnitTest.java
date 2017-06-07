/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.ot.practicapruebas.Aritmetica;
import java.util.ArrayList;
import java.util.List;
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
public class JUnitTest {
    
    private Aritmetica a;
    
    public JUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("BeforeClass");
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("AfterClass");
    }
    
    @Before
    public void setUp() {
        System.out.println("Before");
        a = new Aritmetica();
    }
    
    @After
    public void tearDown() {
        System.out.println("After");
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test(expected = IllegalArgumentException.class)
     public void listaNula() {
        a.media(null);      
     }
     
     @Test(expected = IllegalArgumentException.class)
     public void listaVacia() {
        a.media(new ArrayList<Double>()); 
     }
     
     @Test(expected = RuntimeException.class)
     public void almenosUnValorNegativo() {
        List<Double> list = new ArrayList<Double>();
        list.add(2.0);
        list.add(3.0);
        list.add(-2.0);
        a.media(list);
     }
     
     @Test
     public void listaValoresCorrectos() {
        List<Double> list = new ArrayList<Double>();
        list.add(2.0);
        list.add(3.0);
        list.add(2.0);
        System.out.println(a.media(list));     
     }
}
