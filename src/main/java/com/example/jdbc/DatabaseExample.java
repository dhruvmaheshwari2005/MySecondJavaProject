package com.example.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseExample {
    public static void main(String[] args) {
        String path = "jdbc:mysql://localhost/table";
        String userName = "root";
        String password = "";

        try(Connection connection = DriverManager.getConnection(path, userName, password)) {
            System.out.println(connection);
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}
