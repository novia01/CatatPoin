/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sistemcatatpoinkeaktifan;

/**
 *
 * @author acer
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class DatabaseConnection {
    
    public static Connection getConnection(){
        try{
            Class.forName("org.sqlite.JDBC");
            //ubah dengan letak db di laptop masing2
            Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\LENOVO\\Documents\\KULIAH\\SEM 5\\RPL\\SistemCatatPoinKeaktifan\\db\\database.db");
            return conn;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
