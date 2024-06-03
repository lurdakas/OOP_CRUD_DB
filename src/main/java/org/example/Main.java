package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;


public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Connection con = connect();
        String query = "SELECT * FROM authors";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while ((rs.next())){
                System.out.println(rs.getLong("id") + " " + rs.getString("name") + " " + rs.getString("surname"));
            }
        }catch (Exception e){
            System.out.println("kaboom!");
        }


    }

    public static Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
        }catch (Exception e){
            System.out.println("Failed to connect to database");
        }

        return connection;
    }

}