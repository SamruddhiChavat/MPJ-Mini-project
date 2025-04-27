/*package com.Accio;

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
*/

package com.Accio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection = null;

    // Method to get a database connection
    public static Connection getConnection() {
        // If connection is already established, return it
        if (connection != null) {
            return connection;
        }

        // Fetching database details from environment variables
        String url = System.getenv("DATABASE_URL"); // This can be passed through Docker or system environment variables
        String user = System.getenv("PGUSER"); // Environment variable for DB user
        String password = System.getenv("PGPASSWORD"); // Environment variable for DB password

        // Ensure environment variables are set (optional safety check)
        if (url == null || user == null || password == null) {
            System.err.println("❌ Missing required environment variables for database connection.");
            return null;
        }

        try {
            // Load PostgreSQL JDBC driver
            Class.forName("org.postgresql.Driver");

            // Establish the connection
            connection = DriverManager.getConnection(url, user, password);
            connection.setAutoCommit(true); // Optional, depending on your needs

            System.out.println("✅ Connected to PostgreSQL using environment variables!");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("❌ Failed to connect to PostgreSQL:");
            e.printStackTrace();
        }

        return connection;
    }
}
