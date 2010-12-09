import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.*;

import java.sql.*;
import java.sql.Date;
import java.util.*;
public class BankingSystem extends JFrame{
	
	JLabel FirstName,LastName,LoginID,Address,City,DOB,PostalCode,Saving,Chq;
	JTextField First_Name,Last_Name,Login_ID,TAddress,TCity,TDOB,Postal_Code,TSaving,Tchq;
	JButton Save,Add;
	
	public BankingSystem(){
		FirstName = new JLabel("First Name");
		LastName = new JLabel("Last Name");
		LoginID = new JLabel("Login ID");
		Address = new JLabel("Address");
		City = new JLabel("City");
		DOB = new JLabel("Date Of Birth(ddmmyyyy)");
		PostalCode = new JLabel("Pastal Code");
		Saving = new JLabel("Saving");
		Chq = new JLabel("Chequing");
		
		
		First_Name = new JTextField(20);
		Last_Name = new JTextField(20);
		Login_ID = new JTextField(20);
		TAddress = new JTextField(20);
		TCity = new JTextField(20);
		TDOB = new JTextField(20);
		Postal_Code = new JTextField(20);
		TSaving = new JTextField(20);
		Tchq = new JTextField(20);
		
		Save = new JButton("Exit");
		Add = new JButton("Add");
		Add.addActionListener(new ButtonListener());
		Save.addActionListener(new ButtonListener());
		JPanel jp = new JPanel();
		jp.setLayout(new GridLayout(10,2));
		jp.add(FirstName);
		jp.add(First_Name);
		jp.add(LastName);
		jp.add(Last_Name);
		jp.add(LoginID);
		jp.add(Login_ID);
		jp.add(DOB);
		jp.add(TDOB);
		jp.add(Address);
		jp.add(TAddress);
		jp.add(City);
		jp.add(TCity);
		jp.add(PostalCode);
		jp.add(Postal_Code);
		jp.add(Saving);
		jp.add(TSaving);
		jp.add(Chq);
		jp.add(Tchq);
		jp.add(Add);
		jp.add(Save);
		add(jp);
	}
	public static void main(String []args){
		BankingSystem MasterFrame = new BankingSystem();
		MasterFrame.setSize(500, 400);
	    MasterFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    MasterFrame.setLocationRelativeTo(null);
	    MasterFrame.pack();
	    MasterFrame.setVisible(true);
	    MasterFrame.setTitle("This is MyFrame");
	}
	
public class ButtonListener implements ActionListener
{
	File AddRecord,PinNumber;
	  Random randomGe = new Random();
	  
	public void actionPerformed (ActionEvent e){
		if(e.getSource()==Add){
		try{
			String Nu = TDOB.getText();
			char fn = First_Name.getText().charAt(0);
			char ln = Last_Name.getText().charAt(Last_Name.getText().length()-1);
			double unacc = Math.random()*10000;
			int un = (int) unacc;
			String AccountNumber = Integer.toString(un)+fn+Nu+ln;
			double pinNo = Math.random()*10000;
			int pin = (int) pinNo;
			//TODO Pin adding
			PinNumber = new File("C:\\pinnumber.txt");
			FileWriter fw1 = new FileWriter(PinNumber,true);
			PrintWriter pw1 = new PrintWriter(fw1);
			pw1.println(pin);
			pw1.close();			
			//TODO normal record
			AddRecord = new File("C:\\Records.txt");
			FileWriter fw = new FileWriter (AddRecord,true);
			PrintWriter pw = new PrintWriter(fw);
			pw.print(First_Name.getText()+"\t");
			pw.print(Last_Name.getText()+"\t");
			pw.print(Login_ID.getText()+"\t");
			pw.print(TAddress.getText()+"\t");
			pw.print(TCity.getText()+"\t");
			pw.print(TDOB.getText()+"\t");
			pw.print(Postal_Code.getText()+"\t");
			pw.print(TSaving.getText()+"\t");
			pw.print(Tchq.getText()+"\t");
			pw.print(AccountNumber+"\t");
			pw.print(pin);
			pw.println();			
			pw.close();
			File CheckT = new File("C:\\Check.txt");
			FileReader fr = new FileReader(CheckT);
			BufferedReader br = new BufferedReader(fr);
			String checkStatus = br.readLine();
			Connection con;
	        Class.forName("com.mysql.jdbc.Driver");
	        String path = "jdbc:mysql://localhost:3306/FindQu";
	        String user = "root";
	        String password = "montu";
	        con = DriverManager.getConnection (path, user, password);
	        Statement st = con.createStatement ();
			if(checkStatus.equalsIgnoreCase("false")){
				FileWriter wf = new FileWriter(CheckT);
				PrintWriter pww = new PrintWriter(wf);
				pww.println("true");
				pww.close();				
		        String SQLQ =" CREATE TABLE IF NOT EXISTS Banking (AccountNumber varchar(12), FirstName varchar(15), LastName varchar(15), LoginID varchar(30), DOB varchar(8), Address varchar(30), City varchar(10), PostalCode varchar(7), PinNumber varchar(5), Saving decimal(8,2),chq decimal(8,2));";
				st.executeUpdate(SQLQ);
			}
			//TODO SQLUPdate
			
			
			
			String SQLQ = "insert into Banking (AccountNumber, FirstName, LastName, LoginID, DOB, Address, City, PostalCode, PinNumber, Saving,chq) values (" +
            "\"" + AccountNumber + "\"" + "," +
            "\"" + First_Name.getText() + "\"" + "," +
            "\"" + Last_Name.getText() + "\"" + "," +
            "\"" + Login_ID.getText() + "\"" + "," +
            "\"" + TDOB.getText()+ "\"" + "," +
            "\"" + TAddress.getText() + "\"" + "," +
            "\"" + TCity.getText() + "\"" + "," +
            "\"" + Postal_Code.getText() + "\"" + "," +
            "\"" + pin + "\"" + "," +
            "\"" + TSaving.getText() + "\"" + "," +
            "\"" + Tchq.getText()+ "\""  +
            ")";
			st.executeUpdate(SQLQ);
			
			
		}
		catch(IOException ee){
			System.out.println(ee);
			
		}
		catch(ClassNotFoundException ee){System.out.println(ee);}
		catch(SQLException ee){System.out.println(ee);}
		
		}
		if(e.getSource()==Save)
			System.exit(0);
	}
}

}

