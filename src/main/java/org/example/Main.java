package org.example;

import org.example.models.Author;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
//        System.out.println(Author.selectAll());
//        Author a = Author.findById(69);
//        System.out.println(a);
//        a.setName("Test");
//        a.setSurname("Testaitis");
//        a.update();
//        a = Author.findById(69);
//        System.out.println(a);
//        Author.create("Testas4", "Testaitis4");
//        Author.delete(74);
//        Author b = Author.findById(65);
//        System.out.println(b);
//        b.setName("Maja");
//        b.update();
//        System.out.println(b);


        System.out.println();
        System.out.println("_______________");
        System.out.println();


        Scanner sc = new Scanner(System.in);

        while (true) {
            int item = sc.nextInt();
            sc.nextLine();
            switch (item) {
                case 1:
                    Author.selectAll();
                    System.out.println(Author.selectAll());
                    break;

                case 2:
                    System.out.println("Enter Authors Id");
                    long id = Long.parseLong(sc.nextLine());
                    Author author = Author.findById(id);
                    if (author != null) {
                        System.out.println(Author.findById(id));
                    }else {
                        System.out.println("No author found with Id: " + id);
                    }
                    break;

                case 3:
                    System.out.println("Enter New Authors Name");
                    String autor = sc.nextLine();
                    System.out.println("Enter New Authors Surname");
                    String autorius = sc.nextLine();
                    Author.create(autor , autorius);
                    System.out.println("Authors Added");
                    break;
                case 4:
                    Author.update();


                    break;






                case 5:
                    Author.delete(67);
                    break;
                case 6:
                    System.exit(0);
            }
        }






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

