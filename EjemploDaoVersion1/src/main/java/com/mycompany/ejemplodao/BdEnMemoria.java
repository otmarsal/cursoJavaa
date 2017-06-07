/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ejemplodao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
class BdEnMemoria implements PersonaDao {

    private final List<Persona> personas;

    private static Connection con = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;

    BdEnMemoria() {
        this.personas = new ArrayList<>();

        createConnection();
        shutdown();
    }

    @Override
    public Persona nueva(Persona p) {
        try {
            Objects.requireNonNull(p);
            assert Objects.isNull(p.getId());
            p.setId(System.nanoTime());

            //System.out.println("TE HAS CONECTADO A LA BASE DE DATOS");
            stmt = con.createStatement();

            String insertTableSQL = "INSERT INTO PERSONA VALUES (" + p.getId() + ", '" + p.getNombre() + "')";
            stmt.executeUpdate(insertTableSQL);
            //System.out.println("AÑADIDO CORRECTAMENTE");
            //con.close();
            personas.add(p);
        } catch (SQLException ex) {
            Logger.getLogger(BdEnMemoria.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    @Override
    public List<Persona> todos() {
        try {
            //return Collections.unmodifiableList(personas);
            String query = "", nombre = "";
            Long id = 0l;
            //System.out.println("------------------------");
            query = "SELECT * FROM PERSONA";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                nombre = rs.getString("NOMBRE");
                id = rs.getLong("ID");

                Persona p = new Persona(rs.getString("nombre"));
                p.setId(rs.getLong("id"));
                personas.add(p);
                //System.out.println("NOMBRE: " + nombre + " - ID: " + id);
            }
            //System.out.println("------------------------");
        } catch (SQLException ex) {
            Logger.getLogger(BdEnMemoria.class.getName()).log(Level.SEVERE, null, ex);
        }
        return personas;

    }

    private void createConnection() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/curso", "app", "app");
        } catch (SQLException ex) {
            Logger.getLogger(BdEnMemoria.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BdEnMemoria.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void shutdown() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                if (con != null) {
                    try {
                        con.close();
                        System.out.println("S'ha tancat");
                    } catch (SQLException ex) {
                        Logger.getLogger(BdEnMemoria.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }

        });
    }

    @Override
    public void borrarTodo() {
        try {
            String deleteTableSQL = "DELETE FROM PERSONA";
            stmt.executeUpdate(deleteTableSQL);
        } catch (SQLException ex) {
            Logger.getLogger(BdEnMemoria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
