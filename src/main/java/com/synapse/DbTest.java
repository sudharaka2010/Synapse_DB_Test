package com.synapse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbTest {

    private static final String DB_HOST = "13.234.242.165";
    private static final String DB_NAME = "a1";
    private static final String DB_USER = "iDevice";
    private static final String DB_PASS = "iDevice_123456";

    private static final String JDBC_URL =
            "jdbc:mysql://" + DB_HOST + ":3306/" + DB_NAME +
                    "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    public static void main(String[] args) {

        System.out.println("=== Synapse DB Connection Test ===");

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

            System.out.println("Database connected successfully!");

            // SQL must be a STRING
            String sql = "SHOW TABLES";

            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            System.out.println("\nTables in database:");
            int count = 0;

            while (rs.next()) {
                System.out.println("• " + rs.getString(1));
                count++;
            }

            if (count == 0) {
                System.out.println("(No tables found)");
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            System.out.println("Connection failed");
            System.out.println("Error: " + e.getMessage());
        }
    }
}
