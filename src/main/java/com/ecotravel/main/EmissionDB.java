package com.ecotravel.main;

import java.sql.*;

public class EmissionDB {
    String url="jdbc:sqlite:E:/Docs/Courses/CS1530/EcoTravel/db/emission.db";
    Connection c=null;

    EmissionDB() {
    }

    public Connection connect() {
        Connection c=null;
        try {
            c=DriverManager.getConnection(url);
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
        return c;
    }

    public double selectEmission(String make, String model) {
        String q="SELECT co2TailpipeGpm FROM emissions WHERE make = ? AND model = ?";
        double emission=-1;
        try {
            Connection c=this.connect();
            PreparedStatement ps=c.prepareStatement(q);
            ps.setString(1, make);
            ps.setString(2, model);
            ResultSet rs=ps.executeQuery();
            emission=rs.getDouble("co2TailpipeGpm");

        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
        return emission;
    }
/*
    public static void main(String[] args) {
        EmissionDB edb=new EmissionDB();
        double result=edb.selectEmission("Ferrari", "Testarossa");
        System.out.println("Ferrari Testarossa CO2 emission: " + result);
    }
*/
}
