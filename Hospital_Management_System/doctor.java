package Hospital_Management_System;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class doctor {
    public void FetchDoctor() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "kavita@012");

            String query = "select * from doctor";

            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            System.out.println("*** Doctor Data ***");
            System.out.println("| ID | Name               | Specialization      | ");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name=rs.getString("name");
                String specialization=rs.getString("specialization");

                System.out.printf("| %-2s | %-18s | %-19s |\n", id,name,specialization);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public boolean getDoctorById(int id) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "kavita@012");

            String query = "select * from doctor where id=?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return false;
    }
}
