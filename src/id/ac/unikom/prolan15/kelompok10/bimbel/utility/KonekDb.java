/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.unikom.prolan15.kelompok10.bimbel.utility;
    
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class KonekDb {
    private static Connection koneksi;
    private final static String url = "jdbc:mysql://localhost/bimbel";
    private final static String username = "root";
    private final static String password = "";
    
    public static Connection getKoneksi() {
        if (koneksi == null) {
            try {
                //register library
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                //pembuatan koneksi
                koneksi = DriverManager.getConnection(url, username, password);
            } catch (SQLException ex) {
                Logger.getLogger(KonekDb.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return koneksi;
    }
}
