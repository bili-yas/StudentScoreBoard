package adminAndStudentsLogin;

import java.util.Scanner;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.io.BufferedWriter;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;

class Admin extends JPanel{
	final String USERNAME = "admin";
	final String PASSWORD = "1892";
//	boolean validInput = false;
	
	public Admin() {
        JLabel adminLabel = new JLabel("<html>Welcome, Admin!<br><br>Admin Functions:<br></html>");
//      JLabel function = new JLabel("Adimin Funtions");
        JButton addFunctionButton = new JButton("<html><br>Add Student<br></html>");
        JButton delFunctionButton = new JButton("<html><br>Delete Student<br></html>");
        JButton viewDetailFunctionButton = new JButton("<html><br>View Student's Details<br></html>");

        add(adminLabel);
//      add(function);
        add(addFunctionButton);
        add(delFunctionButton);
        add(viewDetailFunctionButton);
        setPreferredSize(new Dimension(600, 690));
        setLayout(new FlowLayout(FlowLayout.CENTER));
        
        addFunctionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Add Student button clicked");
                addStudentDialog();
            }
        });

        delFunctionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Delete Student button clicked");
            }
        });

        viewDetailFunctionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("View Student's Details button clicked");
            }
        });
    }
	
	boolean adminValidate(String inputUsername, String inputPassword) {
		if( inputUsername.equalsIgnoreCase(USERNAME) && inputPassword.equalsIgnoreCase(PASSWORD)) {
			System.out.println("Sure");
			return true;
		}
		else {
			System.out.println("Invalid input");
            JOptionPane.showMessageDialog(this, "<html><font color='red'>Invalid username or password</font></html>", "Error", JOptionPane.INFORMATION_MESSAGE);

			return false;
		} 
	}
	
    private void addStudentDialog() {
        // Create a dialog for adding a student
        JDialog dialog = new JDialog();
        dialog.setTitle("Add Student");
        dialog.setSize(400, 300);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(this);

        // Create a form panel with input fields
        JPanel formPanel = new JPanel(new GridLayout(5, 2));

        JTextField usernameField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);
        JTextField nameField = new JTextField(20);
        JTextField classField = new JTextField(20);
        JTextField marksField = new JTextField(20);

        formPanel.add(new JLabel("Username:"));
        formPanel.add(usernameField);
        formPanel.add(new JLabel("Password:"));
        formPanel.add(passwordField);
        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Class:"));
        formPanel.add(classField);
        formPanel.add(new JLabel("Marks:"));
        formPanel.add(marksField);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the entered student details
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String name = nameField.getText();
                String studentClass = classField.getText();
                String marks = marksField.getText();
                
                if (username.isEmpty() || password.isEmpty() || name.isEmpty() || studentClass.isEmpty() || marks.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "<html><font color='red'>Please fill in all fields.</font></html>", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    // Store the details in a file (you can customize the file path)
                    addStudentDetails(username, password, name, studentClass, marks);
                    dialog.dispose();
                }
//                dialog.dispose();
            }
        });

        // Add form fields and the "Add" button to the dialog
        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(addButton, BorderLayout.SOUTH);

        // Make the dialog visible
        dialog.setVisible(true);
    }

		
	
	void addStudentDetails(String username, String password, String name, String studentClass, String marks) {
		 try (BufferedWriter writer = new BufferedWriter(new FileWriter("studentDetails.txt", true))) {
	            // Append the student details to a text file (you can customize the file format)
	            writer.write(username+","+password+","+name+","+studentClass+","+marks);
//	            writer.write("Password: " + password + "\n");
//	            writer.write("Name: " + name + "\n");
//	            writer.write("Class: " + studentClass + "\n");
//	            writer.write("Marks: " + marks + "\n");
	            writer.write("\n"); // Add a separator between entries
	            JOptionPane.showMessageDialog(this, "<html><font color='green'>Student added successfully!</font></html>", "Success", JOptionPane.INFORMATION_MESSAGE);

	        } catch (IOException e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(this, "<html><font color='red'>An error occured while adding student</font></html>", "Error", JOptionPane.INFORMATION_MESSAGE);

	        }
	}
}


class Student  extends JPanel{
	String USERNAME = "S";
	String PASSWORD = "1";
	String marks;
	Student(){
		JLabel studentLabel = new JLabel("<html>Welcome, Student!!<br>Please click the button to view result<br></html>");
		JButton showResult = new JButton("Show Reslut");
        add(studentLabel);
//      add(function);
        add(showResult);
        setPreferredSize(new Dimension(600, 690));
        setLayout(new FlowLayout(FlowLayout.CENTER));
        
        showResult.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int mark = Integer.parseInt(marks);
				System.out.println(marks);
				
				if(mark >= 90) {
					JLabel studentMark = new JLabel("<html><br>You Scored:"+mark+"</html><br>"+mark);
					JLabel studentResult = new JLabel("<html><br>Result: <font color = green>Pass, Outstanding.<br></html>");
					JLabel studentGPA = new JLabel("<html><br>GPA: 4.0, A+</html>");
					
					add(studentMark);
					add(studentResult);
					add(studentGPA);
					revalidate();
	                repaint();
				}
				else if(mark >= 80 && mark <= 89) {
					JLabel studentMark = new JLabel("<html><br>You Scored:"+mark+"</html><br>"+mark);
					JLabel studentResult = new JLabel("<html><br>Result: <font color = green>Pass, Excellent.<br></html>");
					JLabel studentGPA = new JLabel("<html><br>GPA: 3.6, A</html>");
					
					add(studentMark);
					add(studentResult);
					add(studentGPA);
					revalidate();
	                repaint();
				}
				
				else if(mark >= 70 && mark <= 79) {
					JLabel studentMark = new JLabel("<html><br>You Scored:"+mark+"</html><br>"+mark);
					JLabel studentResult = new JLabel("<html><br>Result: <font color = green>Pass, Very Good<br></html>");
					JLabel studentGPA = new JLabel("<html><br>GPA: 3.2, B+</html>");
					
					add(studentMark);
					add(studentResult);
					add(studentGPA);
					revalidate();
	                repaint();
				}
				
				else if(mark >= 60 && mark <= 69) {
					JLabel studentMark = new JLabel("<html><br>You Scored: "+mark+"</html><br>"+mark);
					JLabel studentResult = new JLabel("<html><br>Result: <font color = green>Pass, Good<br></html>");
					JLabel studentGPA = new JLabel("<html><br>GPA: 2.8, B</html>");
					
					add(studentMark);
					add(studentResult);
					add(studentGPA);
					revalidate();
	                repaint();
				}
				
				else if(mark >= 50 && mark <= 59) {
					JLabel studentMark = new JLabel("<html><br>You Scored:"+mark+"</html><br>"+mark);
					JLabel studentResult = new JLabel("<html><br>Result: <font color = green>Pass, Satisfactory<br></html>");
					JLabel studentGPA = new JLabel("<html><br>GPA: 2.4, C+</html>");
					
					add(studentMark);
					add(studentResult);
					add(studentGPA);
					revalidate();
	                repaint();
				}
				
				else if(mark >= 40 && mark <= 49) {
					JLabel studentMark = new JLabel("<html><br>You Scored:"+mark+"</html><br>"+mark);
					JLabel studentResult = new JLabel("<html><br>Result: <font color = green>Pass, Acceptable<br></html>");
					JLabel studentGPA = new JLabel("<html><br>GPA: 2.0, C</html>");
					
					add(studentMark);
					add(studentResult);
					add(studentGPA);
					revalidate();
	                repaint();
				}
				
				else {
					JLabel studentMark = new JLabel("<html><br>You Scored:"+mark+"</html><br>"+mark);
					JLabel studentResult = new JLabel("<html><br>Result: <font color = green>Pass, Basic<br></html>");
					JLabel studentGPA = new JLabel("<html><br>GPA: 1.6, C</html>");
					
					add(studentMark);
					add(studentResult);
					add(studentGPA);
					revalidate();
	                repaint();
				}	
			}
        });
 	}
	
	
	boolean studentValidate(String inputUsername, String inputPassword) {
		try (Scanner scanner = new Scanner(new File("studentDetails.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                String storedUsername = parts[0];
                String storedPassword = parts[1];
                String storedMarks = parts[4];
                marks = storedMarks;
                
                if (inputUsername.equals(storedUsername) && inputPassword.equals(storedPassword)) {
                    return true; // Username and password match
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		JOptionPane.showMessageDialog(this, "<html><font color='red'>Invalid username or password</font></html>", "Error", JOptionPane.INFORMATION_MESSAGE);
		System.out.println("Invalid input");
		return false;
	}
}

public class MainClass{
	public static void main(String[] args) {
		JFrame frame;
		JPanel mainPanel;
		JButton loginButton;
		String[] roles = {"Admin","Student"}; 
		JComboBox<String> roleBox = new JComboBox<>(roles);
		 String userRole;
		
		frame = new JFrame();
		frame.setTitle("Welcome to Harvard!!");  
        frame.setSize(900, 900);     
        frame.setLocationRelativeTo(null);  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        ImageIcon image = new ImageIcon("harvard-university.png");
		JLabel label = new JLabel();
		label.setBounds(0, 0, 800, 700);
		label.setIcon(image);
		
		JLabel l = new JLabel("Username:");
		JLabel lb = new JLabel("Password:");
		JLabel role = new JLabel("Role:");
		
		JTextField t = new JTextField(16);
		JPasswordField tx = new JPasswordField(16);
		
		String userName = t.getText();
		char[] pass = tx.getPassword();
		String password = new String(pass);
		
		loginButton = new JButton("Log in");
		
	

//		userRole = (String) roleBox.getSelectedItem();
//		if(userRole.equals("Admin")) {
//			Admin a = new Admin();
//			a.adminValidate(userName, password);
//		}
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(Box.createRigidArea(new Dimension(0, 200))); 
		panel.add(l);
		panel.add(t);
		panel.add(lb);
		panel.add(tx);
		panel.add(role);
		panel.add(roleBox);
		panel.add(loginButton);
		
		Admin a = new Admin();
		Student s = new Student();
//		JPanel adminPanel = new JPanel();
//		JLabel adminLabel = new JLabel("Welcome admin!!");
//		adminPanel.add(adminLabel);
//		
		mainPanel = new JPanel(new  FlowLayout(FlowLayout.RIGHT));
		mainPanel.add(panel);
		mainPanel.add(a);
		mainPanel.add(s);
		a.setVisible(false);
		s.setVisible(false);
		
		 loginButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                String username = t.getText();
	                char[] passwordChars = tx.getPassword();
	                String password = new String(passwordChars);
	                String selectedRole = (String) roleBox.getSelectedItem();

	                // Example: Print the entered data
	                System.out.println("Username: " + username);
	                System.out.println("Password: " + password);
	                System.out.println("Role: " + selectedRole);

	                if (selectedRole.equals("Admin")) {
	                	if(username.isEmpty() || password.isEmpty()) {
	                        JOptionPane.showMessageDialog(frame, "<html><font color='red'>Please fill in all fields.</font></html>", "Warning", JOptionPane.WARNING_MESSAGE);  
	                		return;
	                	}else {
	                		boolean isvalidated = a.adminValidate(username, password);
	                		if(isvalidated) {
	                			panel.setVisible(false);
			                	a.setVisible(true);
			                	
			                	frame.revalidate();
			                	frame.repaint();
			                	
			                	a.setBackground(Color.LIGHT_GRAY);
			                	a.setLayout(new FlowLayout(FlowLayout.LEFT));
//			                    Admin a = new Admin();
	                		}
	                	}
	                }
	                else{
	                	if(username.isEmpty() || password.isEmpty()) {
	                        JOptionPane.showMessageDialog(frame, "<html><font color='red'>Please fill in all fields.</font></html>", "Warning", JOptionPane.WARNING_MESSAGE);  
	                		return;
	                	}else {
	                		boolean isvalidated = s.studentValidate(username, password);
	                		if(isvalidated) {
	                			panel.setVisible(false);
			                	s.setVisible(true);
			                	
			                	frame.revalidate();
			                	frame.repaint();
			                	
			                	s.setBackground(Color.LIGHT_GRAY);
			                	s.setLayout(new FlowLayout(FlowLayout.LEFT));
	                		}
	                	}
	                }
	            }
	        });

		
		frame.add(label);
		frame.add(mainPanel);
		frame.setVisible(true);
		
	}
}
