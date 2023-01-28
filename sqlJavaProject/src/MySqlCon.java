import java.sql.*;
import java.util.*;

class MysqlCon{
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hila", "root", "Sukphi1!");
            Statement stmt = con.createStatement();
            //ResultSet rs=stmt.executeQuery("select * from highschool_friendships");
            //ResultSet rs=stmt.executeQuery("select * from highschool");
            //ResultSet rs=stmt.executeQuery("select * from ids_and_avgs");


            //highschool table
//            while(rs.next())
//                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)
//                + " " + rs.getString(4)+"  " + rs.getString(5)+"  " + rs.getString(6)+"  " +
//                        rs.getInt(7)+"  " + rs.getInt(8)+"  " + rs.getString(9)+"  "
//                + rs.getString(10)+"  " + rs.getInt(11) + " " + rs.getDouble(12) +
//                        " " + rs.getInt(13));

            //highschool_friendships table
//            while(rs.next())
//                System.out.println(rs.getInt(1)+"  "+rs.getInt(2) + " " + rs.getInt(3));

            //ids_and_avgs view
//            while(rs.next())
//                System.out.println(rs.getInt(1)+"  "+rs.getDouble(2));

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        int inputNumber;
        do
        {
            prints();
            inputNumber = scanner.nextInt();
            switch (inputNumber) {
                case 1:
                    input1();
                    break;
                case 2:
                    input2();
                    break;
                case 3:
                    input3();
                    break;
                case 4:
                    input4();
                    break;
                case 5:
                    int id5  = scanner.nextInt();
                    input5(id5);
                    break;
                case 6:
                    input6();
                    break;
                case 7:
                    int id7  = scanner.nextInt();
                    input7(id7);
                    break;
                default:
                    System.out.println("choose an number between 1 and 8");
            }
        }
        while (inputNumber!=8);
    }

    public static void prints()
    {
        System.out.println("select 1 to get the school's average grade");
        System.out.println("select 2 to get the school's male average grade");
        System.out.println("select 3 to get the school's famale average grade");
        System.out.println("select 4 to get the school's height average for people with a purple car and taller then 2m");
        System.out.println("select 5, inset an id and get that person's friends");
        System.out.println("select 6 and get the percentage of popular, regular, and lonely students");
        System.out.println("select 7, inset an id-card and get that person's average grade");
    }
    public static void input1()
    {
        try
        {
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hila","root","Sukphi1!");
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select AVG(grade_avg) AS 'school_avg' from ids_and_avgs");

            while(rs.next())
               System.out.println("the school's average grade is: " + rs.getDouble(1));
            con.close();
        }
        catch(Exception e){ System.out.println(e);}
    }
    public static void input2()
    {
        try
        {
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hila","root","Sukphi1!");
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select AVG(i.grade_avg) as 'male_avg' from ids_and_avgs as i \njoin highschool as h \non h.identification_card = i.identification_card" +
                    "\nwhere h.gender = 'male';");

            while(rs.next())
                System.out.println("the school's male average grade is: " + rs.getDouble(1));
            con.close();
        }
        catch(Exception e){ System.out.println(e);}
    }
    public static void input3()
    {
        try
        {
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hila","root","Sukphi1!");
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select AVG(i.grade_avg) as 'female_avg' from ids_and_avgs as i \njoin highschool as h \non h.identification_card = i.identification_card" +
                    "\nwhere h.gender = 'female';");

            while(rs.next())
                System.out.println("the school's female average grade is: " + rs.getDouble(1));
            con.close();
        }
        catch(Exception e){ System.out.println(e);}
    }
    public static void input4()
    {
        try
        {
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hila","root","Sukphi1!");
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select AVG(cm_height) as 'height_avg'  from highschool\n" +
                    "where has_car = 'true' and car_color = 'purple' and cm_height>200;");

            while(rs.next())
                System.out.println("the school's height average for people with a purple car and taller then 2m is: " + rs.getDouble(1));
            con.close();
        }
        catch(Exception e){ System.out.println(e);}
    }
    public static void input5(int id)
    {
        try
        {
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hila","root","Sukphi1!");
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select other_friend_id from highschool_friendships\n" +
                    "left join highschool\n" +
                    "on highschool.id = highschool_friendships.friend_id\n" +
                    "where highschool_friendships.friend_id = " + id);

            System.out.println("the student's friends are: ");
            while(rs.next())
                System.out.println(rs.getInt(1));
            con.close();
        }
        catch(Exception e){ System.out.println(e);}
    }
    public static void input6()
    {
        float count2 = 0;
        float count1 = 0;
        float count0 = 0;
        float countTotal = 0;
        try
        {
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hila","root","Sukphi1!");
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select friend_id, COUNT(other_friend_id) from highschool_friendships group by friend_id "+
                    "having COUNT(other_friend_id)>1 order by friend_id");
            while(rs.next()) {
                rs.getInt(1);
                count2++;
            }

            rs=stmt.executeQuery("select friend_id, COUNT(other_friend_id) from highschool_friendships group by friend_id "+
                    "having COUNT(other_friend_id)=1 order by friend_id");
            while(rs.next()) {
                rs.getInt(1);
                count1++;
            }

            rs=stmt.executeQuery("select COUNT(*) from highschool_friendships\n");
            while(rs.next()) {
                countTotal = rs.getInt(1);
            }
            count0 = countTotal - count1 - count2;
            count1 = count1/countTotal * 100;
            count2 = count2/countTotal * 100;
            count0 = count0/countTotal * 100;
            System.out.println("regular: " + count1 + "% \npopular: " + count2 + "% \nlonely: " + count0 + "%");
            con.close();
        }
        catch(Exception e){ System.out.println(e);}
    }
    public static void input7(int id)
    {
        try
        {
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hila","root","Sukphi1!");
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select grade_avg from ids_and_avgs\n" +
                    "where identification_card = " + id);

            System.out.println("the student's average grade is: ");
            while(rs.next())
                System.out.println(rs.getDouble(1));
            con.close();
        }
        catch(Exception e){ System.out.println(e);}
    }


}


