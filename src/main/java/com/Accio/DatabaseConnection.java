package com.Accio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection != null) {
            return connection;
        }

        String url = "jdbc:postgresql://ep-sweet-darkness-a1eeey1w-pooler.ap-southeast-1.aws.neon.tech:5432/neondb?sslmode=require";
        String user = "neondb_owner";
        String password = "npg_PUz9eHdR8Scl";

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            connection.setAutoCommit(true); // Optional
            System.out.println("✅ Connected to Vercel PostgreSQL!");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("❌ Failed to connect to PostgreSQL:");
            e.printStackTrace();
        }

        return connection;
    }
}
