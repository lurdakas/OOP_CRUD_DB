package org.example;

import org.example.models.Author;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;


public class Main {

    public static void main(String[] args) {
        System.out.println(Author.selectAll());
        Author a = Author.findById(69);
        System.out.println(a);
        a.setName("Test");
        a.setSurname("Testaitis");
        a.update();
        a = Author.findById(69);
        System.out.println(a);
//        Author.create("Testas2", "Testaitis2");
        Author.delete(71);

    }

        public static Connection connect(){
            Connection connection = null;
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");
            } catch (Exception e) {
                System.out.println("Failed to connect to database");
            }
            return connection;
        }




    }

