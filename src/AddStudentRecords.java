import java.awt.*;
import javax.swing.*;
import java.sql.*;
public class AddStudentRecords extends JFrame{
	JTextField firstname,lastname,StudentID,ica1,ica2,ica3,ica4,ica5,ica6;
	JTextField quiz1,quiz2,midterm,finalmark,project;
	JLabel first_name,last_name,studentID,ica_1,ica_2,ica_3,ica_4,ica_5,ica_6,quiz_1;
	JLabel quiz_2,mid_term,final_mark,projectl;

	Connection con;
	public AddStudentRecords()
        {
	JPanel frame1,frame2,frame3;
	frame1 = new JPanel();
	frame2 = new JPanel();
	frame3 = new JPanel();


        //TODO all Label
	first_name = new JLabel("First Name");
	last_name = new JLabel("Last Name");
	studentID = new JLabel("Student ID");
	ica_1 = new JLabel("ICA 1");
	ica_2 = new JLabel("ICA 2");
	ica_3 = new JLabel("ICA 3");
	ica_4 = new JLabel("ICA 4");
	ica_5 = new JLabel("ICA 5");
	ica_6 = new JLabel("ICA 6");
	quiz_1 = new JLabel("Quiz 1");
	quiz_2 = new JLabel("Quiz 2");
	mid_term = new JLabel("Midterm");
	final_mark = new JLabel("Final Mark");
	projectl = new JLabel("Project Mark");
	//TODO all TextField
	firstname = new JTextField(20);
	lastname = new JTextField(20);
	StudentID = new JTextField(20);
	ica1 = new JTextField(5);
	ica2 = new JTextField(5);
	ica3 = new JTextField(5);
	ica4 = new JTextField(5);
	ica5 = new JTextField(5);
	ica6 = new JTextField(5);
	quiz1 = new JTextField(5);
	quiz2 = new JTextField(5);
	midterm = new JTextField(5);
	finalmark = new JTextField(5);
	project = new JTextField(5);
	//TODO All button

	//TODO panel1
            frame1 = new JPanel();
            frame1.setLayout(new GridLayout(1,6,2,2));
            frame1.add(studentID);
            frame1.add(StudentID);
            frame1.add(first_name);
            frame1.add(firstname);
            frame1.add(last_name);
            frame1.add(lastname);
	//TODO panel 2
            frame2 = new JPanel(new GridLayout(3,4,2,2));
            frame2.add(ica_1);
            frame2.add(ica1);
            frame2.add(ica_2);
            frame2.add(ica2);
            frame2.add(ica_3);
            frame2.add(ica3);
            frame2.add(ica_4);
            frame2.add(ica4);
	//TODO panel 3

        //TODO add
            add(frame1);
            add(frame2);
	}

	public static void main(String []args){
		AddStudentRecords MasterFrame = new AddStudentRecords();
		MasterFrame.setLayout(new GridLayout(3,1,2,2));
                MasterFrame.setSize(500, 400);
	     MasterFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     MasterFrame.setLocationRelativeTo(null);
	     MasterFrame.pack();
	     MasterFrame.setVisible(true);
	     MasterFrame.setTitle("This is MyFrame");
		try
		{
		Connection con;
        Class.forName("com.mysql.jdbc.Driver");
        String path = "jdbc:mysql://localhost:3306/AllStudents";
        String user = "root";
        String password = "montu";
        con = DriverManager.getConnection (path, user, password);
        Statement st = con.createStatement ();
        String SQLQ ="drop table if exists studentRecords";
        st.executeUpdate(SQLQ);
        SQLQ ="create table StudentRecords(idNumber int(9) unique primary key, lastname varchar(15), firstname varchar(15),ica1 decimal(4,2),ica2 decimal(4,2),ica3 decimal(4,2),ica4 decimal(4,2),ica5 decimal(4,2),ica6 decimal(4,2),quiz1 decimal(4,2),quiz2 decimal(4,2),midTerm decimal(4,2),final decimal(4,2),project decimal(4,2));";

		}
		catch(ClassNotFoundException e)
		{System.out.println(e);	}
		catch(SQLException e){System.out.println(e);}
	}
// start action from here
}

