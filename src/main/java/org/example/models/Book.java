package org.example.models;


import org.example.Main;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Book {


    private long id;

    private String title;

    private String genre;

    private long author_id;

    public Book() {
    }

    public Book(long id, String title, String genre, long author_id) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.author_id = author_id;
    }

    public static ArrayList<Book> selectAll() {
        ArrayList<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books";
        try {
            Connection con = Main.connect();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while ((rs.next())) {
                Book book = new Book(rs.getLong("id"), rs.getString("title"), rs.getString("genre"), rs.getLong("author_id"));
                books.add(book);
            }
            con.close();
            stmt.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("Failed to retrieve books!");
        }
        return books;
    }

    public static void printBooks() {
        ArrayList<Book> books = Book.selectAll();
        for (Book book : books) {
            System.out.println(book);
        }
    }
    ///////////

    public static Book findById(long id){
        String query = "SELECT * FROM books where id = ?";
        Book book = null;
        try {
            Connection con = Main.connect();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setLong(1,id);
            ResultSet rs = pst.executeQuery();
            if ((rs.next())){
                book = new Book(rs.getLong("id"), rs.getString("title"), rs.getString("genre"), rs.getLong("author_id"));
            }
            con.close();
            pst.close();
            rs.close();
        } catch (Exception e){
            System.out.println("Failed to retrieve book");
        }
        return book;
    }
    public static void printBookID(Scanner sc){

        System.out.println("Enter Book Id");
        long id = sc.nextLong();
        Book books = findById(id);
        if (books != null) {
            System.out.println(findById(id));
        } else {
            System.out.println("No book found with Id: " + id);
        }

    }
    ////////////////////

    public static void create(String title, String genre, long author_id){
        String query = "INSERT INTO books (`title`,`genre`, `author_id`) VALUES (?,?,?)";
        try {
            Connection con = Main.connect();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, title);
            pst.setString(2, genre);
            pst.setLong(3, author_id);
            pst.executeUpdate();
            con.close();
            pst.close();
        }catch (Exception e){
            System.out.println("Failed to create a new book");
        }
    }


    public static void printNewBook(Scanner sc) {
        sc.nextLine();
        System.out.println("Enter New Book's Title:");
        String title = sc.nextLine();
        System.out.println("Enter New Book's Genre:");
        String genre = sc.nextLine();
        System.out.println("Enter Author ID:");
        long author_id = sc.nextLong();
        Book.create(title, genre, author_id);
        System.out.println("Book added.");
    }

    //////////////////////////


    public void update(){
        String query = "UPDATE `books` SET `title`= ?,`genre`= ?,`author_id`=? WHERE `id` = ?";
        try {
            Connection con = Main.connect();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, this.title);
            pst.setString(2, this.genre);
            pst.setLong(3, this.author_id);
            pst.setLong(4, this.id);
            pst.executeUpdate();
            con.close();
            pst.close();
        }catch (Exception e){
            System.out.println(e);
            System.out.println("Failed to update book");
        }
    }



    public static void updateBook(Scanner sc){
        System.out.println("Enter The Book Id That You Wish To Update");
        long id = sc.nextLong();
        sc.nextLine();
        Book book0 = Book.findById(id);

        if (id != 0) {
            System.out.println(book0);

            System.out.println("Enter New Title:");
            String title = sc.nextLine();
            System.out.println("Enter New Genre:");
            String genre = sc.nextLine();
            System.out.println("Enter New Author ID:");
            long author_id = sc.nextLong();
            book0.setTitle(title);
            book0.setGenre(genre);
            book0.setAuthor_id(author_id);
            book0.update();
            System.out.println("Book updated.");
        }
    }


    ///////////////////////


    public static void delete(long id){
        String query = "DELETE FROM `books` WHERE id = ?";
        try {
            Connection con = Main.connect();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setLong(1, id);
            pst.executeUpdate();
            con.close();
            pst.close();
        }catch (Exception e){
            System.out.println("Failed to delete book");
        }
    }


    public static void deleteBook(Scanner sc) {
        System.out.println("Enter Book ID to Delete:");
        long id = sc.nextLong();
        sc.nextLine();
        Book.delete(id);
        System.out.println("Book deleted.");
    }

///////////////////


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public long getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(long author_id) {
        this.author_id = author_id;
    }





    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", author_id=" + author_id +
                '}';
    }
}
