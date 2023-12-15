package Hospital_Management_System;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class HospitalSystem {
    public static void main(String[] args) {
        Scanner sc1=new Scanner(System.in);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "kavita@012");

            patient p1=new patient();
            doctor d1=new doctor();

            while (true) {
                String head="****** ||  HOSPITAL MANAGEMENT SYSTEM || ******";
                char a[]=head.toCharArray();
                for (int i=0;i<a.length;i++)
                {
                    Thread.sleep(80);
                    System.out.print(a[i]);
                }

                System.out.println("\n1 : Add Patient");
                System.out.println("2 : View Patient");
                System.out.println("3 : View Doctor");
                System.out.println("4 : Book Appointment");
                System.out.println("5 : Exit");

                System.out.println("Enter Your Choice");
                int choice=sc1.nextInt();

                switch (choice) {
                    case 1 :
                        p1.addPatient();
                        System.out.println();
                        break;

                    case 2 :
                        p1.FetchPatient();
                        System.out.println();
                        break;
                    case 3 :
                        d1.FetchDoctor();
                        System.out.println();
                        break;

                    case 4 :
                        bookAppointment(p1,d1);
                        System.out.println();
                        break;
                    case 5 :
                        String end="Thank You...!!!";
                        char b[]=end.toCharArray();
                        for (int i=0;i<b.length;i++)
                        {
                            Thread.sleep(100);
                            System.out.print(b[i]);
                        }
                        return;
                    default :
                        System.out.println("Invalid choice....!!");
                        break;
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    static void bookAppointment(patient p1, doctor d1)
    {
        Scanner sc1=new Scanner(System.in);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "kavita@012");

            System.out.println("Enter Patient ID");
            int id=sc1.nextInt();

            System.out.println("Enter Doctor ID");
            int drid=sc1.nextInt();

            System.out.println("Enter Appointment Date (YYYY-MM-DD)");
            String date=sc1.next();

            if (p1.getPatientById(id) && d1.getDoctorById(drid))
            {
                if (checkAvailability(drid, date))
                {
                    String appointmentQuery="insert into appointments(patient_id,doctor_id, appointment_id) values(?,?,?)";

                    PreparedStatement ps=con.prepareStatement(appointmentQuery);

                    ps.setInt(1,id);
                    ps.setInt(2,drid);
                    ps.setString(3,date);

                    int status = ps.executeUpdate();

                    if (status>0)
                    {
                        System.out.println("Appointment Booked");
                    }
                    else
                    {
                        System.out.println("Failed...Try Again...!!");
                    }
                }
                else
                {
                    System.out.println("doctor not available");
                }
            }
            else
            {
                System.out.println("doctor and patient doesnt exist");
            }
        } catch (Exception e)
        {
            System.out.println(e);
        }
    }
    public static boolean checkAvailability(int doctorId, String appointmentDate)
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "kavita@012");

            String query="select count(*) from appointments where doctor_id=? and appointment_id=?";

            PreparedStatement ps=con.prepareStatement(query);

            ps.setInt(1,doctorId);
            ps.setString(2,appointmentDate);

            ResultSet rs=ps.executeQuery();

            if (rs.next())
            {
                int count=rs.getInt(1);
                if (count==0)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
}
