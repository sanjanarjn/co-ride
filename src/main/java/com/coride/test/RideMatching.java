package com.coride.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RideMatching {

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/your_database_name";
        String dbUser = "your_username";
        String dbPassword = "your_password";
        
        // Assuming you have a Ride class with appropriate attributes
        // and getters and setters for id, polyline, start_time

        // Replace :search_polyline with your actual polyline parameter
        String searchPolyline = "Your encoded search polyline";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            String query = "SELECT id, polyline, start_time, ST_Length(ST_Intersection(polyline, ST_GeomFromText(?))) / ST_Length(ST_GeomFromText(?)) AS overlap_ratio " +
                           "FROM rides " +
                           "WHERE ST_Intersects(polyline, ST_GeomFromText(?)) " +
                           "ORDER BY overlap_ratio DESC";


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
