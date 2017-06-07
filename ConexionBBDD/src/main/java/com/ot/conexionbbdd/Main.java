/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ot.conexionbbdd;

import java.io.IOException;
import java.io.InputStream;
import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.apache.commons.lang3.RandomStringUtils.*;

/**
 *
 * @author omarsal
 */
public class Main {

    private static String sqlSelectAll = "";
    private static String sqlDeleteAll = "";
    private static Statement stmt = null;
    private static Connection con = null;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Main().cargarPropiedades();
        try {

            // TODO code application logic here
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/curso", "app", "app");
            //System.out.println("TE HAS CONECTADO A LA BASE DE DATOS");

            stmt = con.createStatement();

            int e = 0;
            int cont = 1;
            String nombre;
            Long id;
            while (e != 8) {
                System.out.println("INSERTAR - 1 \nUPDATEAR - 2 \nBORRAR - 3 \nLISTAR - 4 \nINSERTAR BUCLE - 5 \nBORRAR TODO - 6 \nTRANSACCION CON BATCH - 7 \nSALIR - 8");
                Scanner sc = new Scanner(System.in);
                e = sc.nextInt();
                String query = "select id from persona where id=(SELECT max(id) FROM PERSONA)";
                ResultSet rs = stmt.executeQuery(query);
                Long maxid = 0l;
                while (rs.next()) {
                    maxid = rs.getLong("ID");
                }

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
                    rs = stmt.executeQuery(sqlSelectAll);
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
                    rs = stmt.executeQuery(sqlSelectAll);
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
                    rs = stmt.executeQuery(sqlSelectAll);
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
                    stmt.executeUpdate(sqlDeleteAll);

                } else if (e == 7) {
                    new Main().insercionEnModoBatch();
                }
                cont++;
                System.out.println("------------------------");
            }
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cargarPropiedades() {
        try {
            InputStream is = getClass()
                    .getClassLoader()
                    .getResourceAsStream(
                            "propiedades.properties");
            Properties p = new Properties();
            p.load(is);
            sqlSelectAll = p.getProperty("consultaPersona");
            sqlDeleteAll = p.getProperty("consultaDeletePersona");
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void insercionEnModoBatch() {
        PreparedStatement st = null;
        try {
            final String ordenSQL = "INSERT INTO PERSONA (ID,NOMBRE) VALUES(?,?)";
            final int veces = 5;
            System.out.println("Realizando inserci�n en modo batch...");
            st = con.prepareStatement(ordenSQL);
            con.setAutoCommit(false);
            for (int i = 0; i < veces; i++) {
                st.setFloat(1, 94949l);
                st.setString(2, "XYZ");
                st.addBatch();
            }
            int[] filas = st.executeBatch();
            con.commit();
            con.setAutoCommit(true);
            for (int i = 0; i < filas.length; i++) {
                System.out.println("Filas afectadas: " + filas[i]);
            }
        } catch (BatchUpdateException b) {
            System.out.println("Filas afectadas por las �rdenes ejecutadas correctamente: ");
            int[] filas = b.getUpdateCounts();
            for (int i = 0; i < filas.length; i++) {
                System.out.print(filas[i] + "  ");
            }
            System.out.println();
        } catch (SQLException e) {
            System.out.println("Error al insertar.");
            try {
                con.rollback();
            } catch (SQLException ex) {
                System.out.println("Error al intentar realizar un rollback.");
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
