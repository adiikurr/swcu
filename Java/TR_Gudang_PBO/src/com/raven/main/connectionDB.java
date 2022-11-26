package com.raven.main;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectionDB {
    public static Connection con;
    public static Statement stm;
    
    public static void config(){
            try {
                String url="jdbc:mysql://localhost/db_gudang";
                String user="root";
                String pass="";
                Class.forName("com.mysql.cj.jdbc.Driver");
                con=DriverManager.getConnection(url, user, pass);
                stm=(Statement) con.createStatement();
                System.out.println("Berhasil");
                
            } catch (Exception e) {
                System.out.println("Koneksi Gagal");
            }


    }
    


}






    