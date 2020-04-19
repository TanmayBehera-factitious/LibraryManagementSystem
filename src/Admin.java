import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Admin extends Account{
	private Scanner sc = new Scanner(System.in);
	private Connection con;
	Statement stmt;
	String Query;
	
	private int choice;
	
	Admin() throws SQLException{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");  
			  
			stmt = con.createStatement();
		
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		
		do {
			mainAdminPage();
			switch(choice){
				case 1: addUsers(); break;
				case 2: showIssuedBooks(); break;
				case 3: issueBook(); break;
				case 4: removeUser(); break;
				case 5: break;
				default : System.out.println("Invalid Choice\nPlease Enter Again");
			}
		}while(choice != 5);
	}

	private void removeUser() throws SQLException {
		String username = sc.nextLine();
		
		Query = String.format("DELETE FROM BOOK_DETAILS WHERE USERNAME = '%d'", username);
		stmt.executeUpdate(Query);
	}
	
	private void issueBook() throws SQLException {
		String dateOfIssue, username, dateOfReturn, bookName;
		sc = new Scanner(System.in);
		
		System.out.print("Enter username: ");
		username = sc.nextLine();
		System.out.print("Enter Book Name: ");
		bookName = sc.nextLine();
		System.out.print("Enter date of issue(YYYY-MM-DD): ");
		dateOfIssue = sc.nextLine();
		System.out.print("Enter date of return(YYY-MM-DD): ");
		dateOfReturn = sc.nextLine();
		
		Query = String.format("INSERT INTO BOOK_DETAILS VALUES('%s', '%s', '%s', '%s', '%s')", username, bookName, "Issued", dateOfIssue, dateOfReturn);
		stmt.executeUpdate(Query);
	}
	
	private void addUsers() throws SQLException {
		String username, password, userType;
		sc = new Scanner(System.in);
		System.out.print("Enter username: ");
		username = sc.nextLine();
		System.out.print("Enter password: ");
		password = sc.nextLine();
		System.out.print("Enter user type: ");
		userType = sc.nextLine();
		
		Query = String.format("INSERT INTO AccountInformation VALUES('%s', '%s', '%s')", username, password, userType);
		stmt.executeUpdate(Query);
		
	}
	
	public void mainAdminPage() {
		System.out.println("MAIN MENU");
		System.out.println("1. Add Users");
		System.out.println("2. Shows Issued Books");
		System.out.println("3. Issue Book");
		System.out.println("4. Remove Users");
		System.out.println("5. Quit ");
		
		System.out.print("Enter your choice: ");
		choice = sc.nextInt();
	}

	@Override
	public void showIssuedBooks() {
		Query = "SELECT * FROM BOOK_DETAILS WHERE STATUS = 'Issued'";
		try {
			ResultSet rs = stmt.executeQuery(Query);	
			
			System.out.println(String.format("%s%20s%20s%20s%20s", "USERNAME", "BOOK_NAME", "STATUS", "DATE_OF_ISSUE", "DATE_OF_RETURN"));
			
			while(rs.next())
				System.out.println(String.format("%s%20s%20s%20s%20s", rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getDate(5)));	
		}
		catch(SQLException e) {
			e.printStackTrace();
		}	
	}
}
