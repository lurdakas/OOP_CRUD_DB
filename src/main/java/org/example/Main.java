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
//        Author.create("Testas4", "Testaitis4");
        Author.delete(74);
        Author b = Author.findById(65);
        System.out.println(b);
        b.setName("Maja");
        b.update();
        System.out.println(b);

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

