/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ot.conexionbbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.apache.commons.lang3.RandomStringUtils.*;

/**
 *
 * @author omarsal
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            Statement stmt = null;
            Connection con = null;

            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/curso", "app", "app");
            //System.out.println("TE HAS CONECTADO A LA BASE DE DATOS");

            stmt = con.createStatement();

            int e = 0;
            int cont = 1;
            String nombre;
            Long id;
            while (e != 7) {
                System.out.println("INSERTAR - 1 \nUPDATEAR - 2 \nBORRAR - 3 \nLISTAR - 4 \nINSERTAR BUCLE - 5 \nBORRAR - 6 \nSALIR - 7");
                Scanner sc = new Scanner(System.in);
                e = sc.nextInt();
                String query = "select id from persona where id=(SELECT max(id) FROM PERSONA)";
                ResultSet rs = stmt.executeQuery(query);
                rs.next();
                Long maxid = rs.getLong("ID");

                maxid++;

                System.out.println("------------------------");
                if (e == 1) {

                    System.out.println("Nombre:");
                    nombre = sc.next();
                    String insertTableSQL = "INSERT INTO PERSONA VALUES (" + maxid + ", '" + nombre + "')";
                    stmt.executeUpdate(insertTableSQL);
                    System.out.println("AÑADIDO CORRECTAMENTE");
                    //System.out.println("TE HAS DESCONECTADO A LA BASE DE DATOS");
                } else if (e == 2) {
                    query = "SELECT * FROM PERSONA";
                    rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        nombre = rs.getString("NOMBRE");
                        id = rs.getLong("ID");
                        System.out.println("NOMBRE: " + nombre + " - ID: " + id);
                    }

                    System.out.println("------------------------");
                    System.out.println("MODIFICAR ID:");
                    id = sc.nextLong();
                    System.out.println("NOMBRE:");
                    nombre = sc.next();
                    String updateTableSQL = "UPDATE PERSONA SET NOMBRE = '" + nombre + "' WHERE ID = " + id + "";
                    stmt.executeUpdate(updateTableSQL);
                    System.out.println("NOMBRE MODIFICADO CORRECTAMENTE");
                } else if (e == 3) {
                    query = "SELECT * FROM PERSONA";
                    rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        nombre = rs.getString("NOMBRE");
                        id = rs.getLong("ID");
                        System.out.println("NOMBRE: " + nombre + " - ID: " + id);
                    }
                    System.out.println("------------------------");
                    System.out.println("BORRAR ID:");
                    id = sc.nextLong();
                    String deleteTableSQL = "DELETE FROM PERSONA WHERE ID = " + id + "";
                    stmt.executeUpdate(deleteTableSQL);
                    System.out.println("BORRADO CORRECTAMENTE");
                } else if (e == 4) {
                    query = "SELECT * FROM PERSONA";
                    rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        nombre = rs.getString("NOMBRE");
                        id = rs.getLong("ID");
                        System.out.println("NOMBRE: " + nombre + " - ID: " + id);
                    }
                } else if (e == 5) {
                    System.out.println("CANTIDAD A INSERTAR:");
                    int count = sc.nextInt();

                    for (int i = 0; i < count; i++) {
                        String s = "insert into persona values (?,?)";
                        PreparedStatement ps = con.prepareStatement(s);
                        ps.setLong(1, System.nanoTime());
                        ps.setString(2, random(16, true, true));
                        ps.executeUpdate();
                    }

                    //System.out.println("AÑADIDO CORRECTAMENTE");
                } else if (e == 6) {
                    
                        String deleteTableSQL = "DELETE FROM PERSONA";
                        stmt.executeUpdate(deleteTableSQL);
                   

                    //System.out.println("AÑADIDO CORRECTAMENTE");
                }
                cont++;
                System.out.println("------------------------");
            }
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
