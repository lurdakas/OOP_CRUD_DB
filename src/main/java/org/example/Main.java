package org.example;


import org.example.models.Author;
import org.example.models.Book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;




public class Main {
    public static Scanner sc;
    public static void main(String[] args) {


        sc = new Scanner(System.in);
        while (true) {
            System.out.println("1. authors");
            System.out.println("2. books");
            System.out.println("3. search");
            System.out.println("4. exit");
            switch (sc.nextInt()) {
                case 1:
                    authorsMenu(sc);
                    break;
                case 2:
                    booksMenu(sc);
                    break;
                case 3:
                    searchMenu(sc);
                    break;
                case 4:
                    System.exit(0);
                    break;
            }
        }
    }
    public static void searchMenu(Scanner sc) {
        boolean search = true;
        while(search) {
            System.out.println("1. Search For Authors Name");
            System.out.println("2. Search For Authors Surname");
            switch(sc.nextInt()) {
                case 1:
//                    findAuthorByName(sc);
                    break;
                case 2:





            }
        }
    }

    public static void booksMenu(Scanner sc){
        boolean knygos = true;
        while(knygos){
            System.out.println("1. List Books");
            System.out.println("2. Find Book by ID");
            System.out.println("3. Add Book");
            System.out.println("4. Update Book");
            System.out.println("5. Delete Book");
            System.out.println("6. Exit to main menu");
            switch(sc.nextInt()){
                case 1:
                    Book.printBooks();
                    break;
                case 2:
                    Book.printBookID(sc);
                    break;
                case 3:
                    Book.printNewBook(sc);
                    break;
                case 4:
                    Book.updateBook(sc);
                    break;
                case 5:
                    Book.deleteBook(sc);
                    break;
                case 6:
                    knygos = false;
                    break;

                }
            }
        }
    public static void authorsMenu(Scanner sc){
        boolean autoriai = true;
        while(autoriai){
            System.out.println("1. List Authors");
            System.out.println("2. Find Author by ID");
            System.out.println("3. Add Author");
            System.out.println("4. Update Author");
            System.out.println("5. Delete Author");
            System.out.println("6. Exit to main menu");
            switch(sc.nextInt()){
                case 1:
                    Author.printAuthors();
                    break;
                case 2:
                    Author.printID(sc);
                    break;

                case 3:
                    Author.printNewAuthor(sc);
                    break;

                case 4:
                    Author.updateAuthor(sc);
                    break;

                case 5:
                    Author.deleteAuthor(sc);
                    break;

                case 6:
                    autoriai = false;
                    break;

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




