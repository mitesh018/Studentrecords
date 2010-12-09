import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.io.*;
public class Grades extends JFrame
      {
          JTextField [] field = new JTextField [14];
          String [] labelString = new String [14];
          JLabel [] label = new JLabel [14];
          JButton add2Table = new JButton ();

          public Grades ()
                {
					setBounds (20, 20, 500, 500);
					setLocationRelativeTo (null);
					setTitle ("Grades Entry Screen");
					setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
					setLayout (new BorderLayout ());
					JPanel gradesPanel = new GradesPanel ();
					add (gradesPanel, BorderLayout.CENTER);
					JPanel buttonPanel = new ButtonPanel ();
					add (buttonPanel, BorderLayout.SOUTH);
					setVisible (true);
				}
		  public static void main (String [] args)
		        {
                   JFrame gradesFrame = new Grades ();
				}
           class GradesPanel extends JPanel
                {
					public GradesPanel ()
					      {
							setLayout (new GridLayout (14, 2));
							labelString [0] = "ID Number: ";
							labelString [1] = "Last Name: ";
							labelString [2] = "First Name: ";
							labelString [3] = "ICA 1:";
							labelString [4] = "ICA 2:";
							labelString [5] = "ICA 3:";
							labelString [6] = "ICA 4:";
							labelString [7] = "ICA 5:";
							labelString [8] = "ICA 6:";
							labelString [9] = "Quiz 1:";
							labelString [10] = "Quiz 2:";
							labelString [11] = "Mid Term:";
							labelString [12] = "Final:";
							labelString [13] = "Project:";
					        for (int i = 0; i < 14; i ++)
                               {
				                  field [i] = new JTextField (5);
				                  label [i] = new JLabel (labelString [i]);
				                  label [i].setHorizontalAlignment (JLabel.LEFT);
				                  add (label [i]);
				                  add (field [i]);
							   }
					      }
				}
           class ButtonPanel extends JPanel
                {
					public ButtonPanel ()
					      {
							add2Table = new JButton ("Add Record");
							add2Table.addActionListener (new ButtonListener ());
							add (add2Table);
					      }
			        private class ButtonListener implements ActionListener
			               {
                               String trueOrFalse;
                               int id;
                               String lName, fName;
                               double marksICA1, marksICA2, marksICA3, marksICA4, marksICA5, marksICA6, marksQuiz1, marksQuiz2, marksMidTerm, marksFinal, marksProject;
                               String path, user, password, sqlStatement;
                               Connection con;
                               Statement st;
                               File tableStatus;

							   public void actionPerformed (ActionEvent e)
							         {
										try
										     {
										          tableStatus = new File ("tableStatus.txt");
										          FileReader fr = new FileReader (tableStatus);
										          BufferedReader br = new BufferedReader (fr);
										          trueOrFalse = br.readLine ();
										          if (trueOrFalse.equalsIgnoreCase ("true"))
														br.close ();
										          else
										            {
													    br.close ();
													    tableStatus = new File ("tableStatus.txt");
										                FileWriter fw = new FileWriter (tableStatus);
										                PrintWriter pw = new PrintWriter (fw);
										                pw.println ("true");
										                pw.close ();
													}
									         }
									    catch (IOException ioe)
									         {
												 System.out.println (ioe.getMessage ());
											 }
										if (trueOrFalse.equalsIgnoreCase ("false"))
										  {
												try
													 {
														Class.forName ("com.mysql.jdbc.Driver");
														path = "jdbc:mysql://localhost/AllStudents";
														user = "root";
														password = "";
														con = DriverManager.getConnection (path, user, password);
														st = con.createStatement ();
														sqlStatement = "CREATE TABLE IF NOT EXISTS studentRecords (IDNumber int(9), lastName varchar(15), firstName varchar(15), ica1 decimal (4, 2), ica2 decimal (4, 2), ica3 decimal (4, 2), ica4 decimal (4, 2), ica5 decimal (4, 2), ica6 decimal (4, 2), quiz1 decimal (4, 2), quiz2 decimal (4, 2), midTermExam decimal (4, 2), finalExam decimal (4, 2), project decimal (4, 2))";
														st.executeUpdate (sqlStatement);
													 }
												catch (ClassNotFoundException cnfe)
													 {
														  System.out.println ("Error is probably with the class path.");
													 }
												catch (SQLException sqle)
													 {
														 System.out.println ("Error is probably with your SQL statements.");
													 }
										  }
										try
										     {
											   Class.forName ("com.mysql.jdbc.Driver");
										       id = Integer.parseInt (field [0].getText ());
										       lName = field [1].getText ();
										       fName = field [2].getText ();
										       marksICA1 = Double.parseDouble (field [3].getText ());
										       marksICA2 = Double.parseDouble (field [4].getText ());
										       marksICA3 = Double.parseDouble (field [5].getText ());
										       marksICA4 = Double.parseDouble (field [6].getText ());
										       marksICA5 = Double.parseDouble (field [7].getText ());
										       marksICA6 = Double.parseDouble (field [8].getText ());
										       marksQuiz1 = Double.parseDouble (field [9].getText ());
										       marksQuiz2 = Double.parseDouble (field [10].getText ());
										       marksMidTerm = Double.parseDouble (field [11].getText ());
										       marksFinal = Double.parseDouble (field [12].getText ());
										       marksProject = Double.parseDouble (field [13].getText ());

                                               sqlStatement = "insert into studentRecords (IDNumber, lastName, firstName, ica1, ica2, ica3, ica4, ica5, ica6, quiz1, quiz2, midTermExam, finalExam, project) values (" +
                                               "\"" + id + "\"" + "," +
                                               "\"" + lName + "\"" + "," +
                                               "\"" + fName + "\"" + "," +
                                               "\"" + marksICA1 + "\"" + "," +
                                               "\"" + marksICA2 + "\"" + "," +
                                               "\"" + marksICA3 + "\"" + "," +
                                               "\"" + marksICA4 + "\"" + "," +
                                               "\"" + marksICA5 + "\"" + "," +
                                               "\"" + marksICA6 + "\"" + "," +
                                               "\"" + marksQuiz1 + "\"" + "," +
                                               "\"" + marksQuiz2 + "\"" + "," +
                                               "\"" + marksMidTerm + "\"" + "," +
                                               "\"" + marksFinal + "\"" + "," +
                                               "\"" + marksProject + "\"" +
                                               ")";
                                            st.executeUpdate (sqlStatement);
                                            for (int i = 0; i < 14; i ++)
                                               field [i].setText ("");

									       }
									  catch (SQLException sqle)
									       {
									   		  System.out.println ("Error is probably with your SQL statements.");
										   }
									  catch (ClassNotFoundException cnfe)
									       {
											  System.out.println ("Error is probably with the class path.");
										   }

									}



										 /*get stuff from fields
										 put into an SQL statement;
										 st.executeUpdate (sqlStatement);
										 copy stuff to text file;*/
									 }
						   }
			    }