package Hospital_Management_System;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
public class patient {
     Scanner sc1=new Scanner(System.in);
    public void addPatient()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","kavita@012");

            String query="insert into patient(name,age,gender) values(?,?,?)";

            PreparedStatement ps=con.prepareStatement(query);

            System.out.println("Enter Patient Name");
            String name=sc1.next();

            System.out.println("Enter Patient Age");
            int age=sc1.nextInt();

            System.out.println("Enter Patient Gender");
            String gender=sc1.next();

            ps.setString(1,name);
            ps.setInt(2,age);
            ps.setString(3,gender);

            int status =ps.executeUpdate();

            if (status>0)
            {
                System.out.println("Patient Data Added Successfully");
            }
            else
            {
                System.out.println("Failed....Try Again...");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    public void FetchPatient() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "kavita@012");

            String query = "select * from patient";

            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            System.out.println("*** Patient Data ***");
            System.out.println("| ID | Name               | Age    | Gender    |");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name=rs.getString("name");
                int age=rs.getInt("age");
                String gender=rs.getString("gender");

                System.out.printf("| %-2s | %-18s | %-6s | %-9s |\n", id, name, age, gender);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public boolean getPatientById(int id) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "kavita@012");

            String query = "select * from patient where id=?";

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
