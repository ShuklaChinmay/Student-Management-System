import java.sql.*;
import java.util.Scanner;

public class Student_Management_System {
    public static void main(String...k)throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Student_Management_System", "root", "chinmay@2004");
        int ch;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("\nWelcome To Your Banking System\n");
            System.out.println("1. Create a new Student Profile \n" +
                    "2. Update Student Profile\n" +
                    "3. Delete Student Profile\n" +
                    "4. View All Student List\n"+
                    "5. Exit");
            System.out.print("Enter your choice = ");
            ch = sc.nextInt();
            sc.nextLine();

            switch(ch){
                case 1:  // Create a new Student Profile
                    String q = create_profile(sc);
                    PreparedStatement pst = con.prepareStatement(q);

                    int i = pst.executeUpdate();
                    if(i > 0){
                        System.out.print("\t\t New Student account has been Created\n");
                    }
                    break;

                case 2:  // Update Student Profile
                    System.out.print("Enter Your Roll Number = ");
                    int Roll_Number = sc.nextInt();
                    sc.nextLine();

                    String a = update_profile(sc);
                    PreparedStatement ptv = con.prepareStatement(a);
                    switch (a) {
                        case "update Students set name = ? where Roll_Number = ?":
                            System.out.print("Enter the new Name: ");
                            String newName = sc.nextLine();
                            ptv.setString(1, newName);
                            break;
                        case "update Students set Address = ? where Roll_Number = ?":
                            System.out.print("Enter the new Address : ");
                            String newCity = sc.nextLine();
                            ptv.setString(1, newCity);
                            break;
                        case "update Students set Phone_Number = ? where Roll_Number = ?":
                            System.out.print("Enter the new Mobile Number: ");
                            long newMobileNumber = sc.nextLong();
                            sc.nextLine();
                            ptv.setLong(1, newMobileNumber);
                            break;
                        case "update Students set College_Name = ? where Roll_Number = ?":
                            System.out.print("Enter the new College_Name : ");
                            String newCollege = sc.nextLine();
                            ptv.setString(1, newCollege);
                            break;
                        case "update Students set Branch = ? where Roll_Number = ?":
                            System.out.print("Enter the new Branch : ");
                            String Branch = sc.nextLine();
                            ptv.setString(1, Branch);
                            break;
                        case "update Students set Percentage = ? where Roll_Number = ?":
                            System.out.print("Enter the Percentage : ");
                            double Percentage = sc.nextDouble();
                            ptv.setString(1, String.valueOf(Percentage));
                            break;
                        case "update Students set Year = ? where Roll_Number = ?":
                            System.out.print("Enter correct Year : ");
                            int Year = sc.nextInt();
                            ptv.setString(1, String.valueOf(Year));
                            break;
                    }
                    ptv.setInt(2, Roll_Number);
                    int j = ptv.executeUpdate();
                    if(j > 0){
                        System.out.print("\t\t Your Profile has been Updated\n");
                    }
                    else{
                        System.out.println("Student Not Found");
                    }
                    break;

                case 3:     // Delete Student Profile
                    System.out.print("Enter the Roll Number = ");
                    int rno = sc.nextInt();
                    String delete = "delete from Students where Roll_Number = ?";
                    PreparedStatement del = con.prepareStatement(delete);
                    del.setInt(1,rno);

                    int l = del.executeUpdate();
                    if(l > 0){
                        System.out.print("\t\t Your account has been Deleted\n");
                    }
                    else{
                        System.out.println("Account Not Found");
                    }
                    break;

                case 4:      // View All Student List
                    StudentProfile(con);
                    break;

                case 5: // Exit
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Enter Valid Choice");
            }
        } while(ch != 5);
        sc.close();
    }

    static String create_profile(Scanner sc) {
        System.out.println("\n\tTo create a new profile fill the details given below :-\n");

        System.out.print("Enter your academic Roll_Number = ");
        int Roll_Number = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter your name = ");
        String Name = sc.nextLine();

        System.out.print("Enter the Address = ");
        String Address = sc.nextLine();

        System.out.print("Enter your mobile number = ");
        long Phone_Number = sc.nextLong();
        sc.nextLine();

        System.out.print("Enter your College name = ");
        String College_Name = sc.nextLine();

        System.out.print("Enter your Branch = ");
        String Branch = sc.nextLine();

        System.out.print("Enter the Percentage = ");
        double Percentage = sc.nextDouble();
        sc.nextLine();

        System.out.print("Enter your academic year (in integer) = ");
        int Year = sc.nextInt();
        sc.nextLine();

        String q  = "INSERT INTO Students( Roll_Number ,Name, Address, Phone_Number, College_Name , Branch , Percentage , Year) values('"+ Roll_Number +"','" + Name + "', '" + Address + "', '" + Phone_Number + "', '" + College_Name + "' , '" + Branch + "' ,'"+ Percentage +"' , '"+Year+"')";  //
        return q;
    }

    static String update_profile(Scanner sc){

        System.out.println("What you want to update :\n\t1. Name\n\t2. Address\n\t3. Phone_Number\n\t4. College_Name\n\t5. Branch\n\t6. Percentage\n\t7. Year");
        System.out.print("Enter Your Choice : ");
        int choice = sc.nextInt();
        sc.nextLine();
        String p = "";
        switch (choice) {
            case 1:
                p = "update Students set Name = ? where Roll_Number = ?";
                break;
            case 2:
                p = "update Students set Address = ? where Roll_Number = ?";
                break;
            case 3:
                p = "update Students set Phone_Number = ? where Roll_Number = ?";
                break;
            case 4:
                p = "update Students set College_Name = ? where Roll_Number = ?";
                break;
            case 5:
                p = "update Students set Branch = ? where Roll_Number = ?";
                break;
            case 6:
                p = "update Students set Percentage = ? where Roll_Number = ?";
                break;
            case 7:
                p = "update Students set Year = ? where Roll_Number = ?";
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
        return p;
    }


    static void StudentProfile(Connection con)throws SQLException {
        String query = "SELECT Roll_Number , Name, Address, Phone_Number, College_Name , Branch , Percentage , Year FROM Students";
        PreparedStatement pst = con.prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        System.out.println("\nAccount Holders List:");
        System.out.println("Roll_Number | Name           | Address            | Phone_Number | College_Name | Branch                  | Percentage | Year");
        while (rs.next()) {
            int Roll_Number = rs.getInt("Roll_Number");
            String Name = rs.getString("Name");
            String Address = rs.getString("Address");
            long Phone_Number = rs.getLong("Phone_Number");
            String College_Name = rs.getString("College_Name");
            String Branch = rs.getString("Branch");
            double Percentage = rs.getDouble("Percentage");
            int Year = rs.getInt("Year");
            System.out.printf(" %d | %s | %s | %d | %s | %s | %f | %d\n", Roll_Number,Name, Address, Phone_Number, College_Name , Branch , Percentage , Year);
        }
    }
}
