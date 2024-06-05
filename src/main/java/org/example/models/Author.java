package org.example.models;

import org.example.Main;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class Author {
    private long id;
    private String name;
    private String surname;


    public Author() {
    }

    public Author(long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }



    public static ArrayList<Author> selectAll() {
        ArrayList<Author> authors = new ArrayList<>();
        String query = "SELECT * FROM authors";
        try {
            Connection con = Main.connect();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while ((rs.next())) {
                Author aut = new Author(rs.getLong("id"), rs.getString("name"), rs.getString("surname"));
                authors.add(aut);
            }
            con.close();
            stmt.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("Failed to retrieve authors!");
        }
        return authors;
    }

    public static void printAuthors(){
        ArrayList<Author> authors = Author.selectAll();
        for (Author author : authors){
            System.out.println(author);
        }
    }

    /////////////////////

    public static Author findById(long id){
        String query = "SELECT * FROM authors where id = ?";
        Author aut = null;
        try {
            Connection con = Main.connect();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setLong(1,id);
            ResultSet rs = pst.executeQuery();
            while ((rs.next())){
                aut = new Author(rs.getLong("id"), rs.getString("name"), rs.getString("surname"));
            }
            con.close();
            pst.close();
            rs.close();
        }catch (Exception e){
            System.out.println("Failed to retrieve author");
        }
        return aut;
    }
    public static void printID(Scanner sc){

        System.out.println("Enter Authors Id");
        long id = sc.nextLong();
        Author author = findById(id);
        if (author != null) {
            System.out.println(findById(id));
        } else {
            System.out.println("No author found with Id: " + id);
        }

    }

    /////////////////////////


    public static void create(String name, String surname){
        String query = "INSERT INTO `authors` (`name`,`surname`) VALUES (?,?)";
        try {
            Connection con = Main.connect();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, name);
            pst.setString(2, surname);
            pst.executeUpdate();
            con.close();
            pst.close();
        }catch (Exception e){
            System.out.println("Failed to create author");
        }
    }

    public static void printNewAuthor(Scanner sc){
        sc.nextLine();
        System.out.println("Enter New Authors Name");
        String autor = sc.nextLine();
        System.out.println("Enter New Authors Surname");
        String autorius = sc.nextLine();
        Author.create(autor, autorius);
        System.out.println("Authors Added");

    }

    ////////////////////


    public void update(){
        String query = "UPDATE `authors` SET `name`=?,`surname`=? WHERE id = ?";
        try {
            Connection con = Main.connect();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, this.name);
            pst.setString(2, this.surname);
            pst.setLong(3, this.id);
            pst.executeUpdate();
            con.close();
            pst.close();
        }catch (Exception e){
            System.out.println(e);
            System.out.println("Failed to update author");
        }
    }
    public static void updateAuthor(Scanner sc){
        System.out.println("Enter The Authors Id That You Wish To Update");
        long id = sc.nextLong();
        sc.nextLine();
        Author aut0 = findById(id);
        System.out.println(Author.findById(id));

        if (id != 0) {
            System.out.println("Enter New Authors Name");
            String autor1 = sc.nextLine();
            System.out.println("Enter New Authors Surname");
            String autor2 = sc.nextLine();
            aut0.setName(autor1);
            aut0.setSurname(autor2);
            aut0.update();
            System.out.println("Author updated " + findById(id));
        } else {
            System.out.println("No Author Found with such Id");
        }
    }


    /////////////////////

    public static void delete(long id){
        String query = "DELETE FROM `authors` WHERE id = ?";
        try {
            Connection con = Main.connect();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setLong(1, id);
            pst.executeUpdate();
            con.close();
            pst.close();
        }catch (Exception e){
            System.out.println("Failed to delete author");
        }
    }

    public static void deleteAuthor(Scanner sc){
        System.out.println("Enter Authors Id You Wish To Delete");
        long id = sc.nextLong();
        sc.nextLine();
        Author autor1 = findById(id);
        System.out.println(Author.findById(id));
        Author.delete(id);
        System.out.println("Author Has Been Deleted");

    }

    /////////////////////////////////

    public static void authorByName(){
        String query = "";
    }




    public static void findAuthorByName(){
        System.out.println("Enter Cahracters To Find Authors Name");

    }

//////////////////////////////////////////


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    @Override
    public String toString() {
        return "Authors{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';







    }
}
