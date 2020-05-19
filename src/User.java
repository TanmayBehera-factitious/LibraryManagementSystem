import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class User extends Account {
	private Scanner sc = new Scanner(System.in);
	private Connection con;
	private static String username;
	Statement stmt;
	String Query;
	
	private int choice;
	
	User(String usr) throws SQLException{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");  
			  
			stmt = con.createStatement();
			username = usr;
		
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}  
		
		
		do {
			mainUserPage();
			switch(choice){
				case 1: showIssuedBooks(); break;
				case 2: ReturnBook(); break;
				case 3: break;
				default : System.out.println("Invalid Choice\nPlease Enter Again");
			}
		}while(choice != 3);
	}
	
	private void mainUserPage() {
		System.out.println("MAIN MENU");
		System.out.println("1. Show Issued Books");
		System.out.println("2. Return Book");
		System.out.println("3. Quit");
		
		System.out.print("Enter your choice: ");
		choice = sc.nextInt();
	}

	private void ReturnBook() {
		String bookName, dateToReturn;
		long fine, value;
		sc = new Scanner(System.in);
		
		System.out.print("Enter Book Name: ");
		bookName = sc.nextLine();
		
		Query = String.format("SELECT * FROM BOOK_DETAILS WHERE BOOK_NAME = '%s' && USERNAME = '%s'", bookName, username);
		try {
			ResultSet rs = stmt.executeQuery(Query);
			
			rs.next();
			dateToReturn = rs.getString(5);
			fine = fineCalculator(dateToReturn);
			System.out.print("You have to pay the fine to return the book, Your fine amount:");
			System.out.println(fine);
			System.out.println("Book will be only be returned if you enter the fine value:");
			value = sc.nextLong();
			while(true) {	
				if(value == fine) {
					System.out.println("Book Returned");
					break;
				}
				System.out.println("You have provided the  wrong fine value");
			}
			
			Query = String.format("DELETE FROM BOOK_DETAILS WHERE BOOK_NAME = '%s' && USERNAME = '%s'", bookName, username);
			stmt.executeUpdate(Query);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void showIssuedBooks() {
		Query = String.format("SELECT * FROM BOOK_DETAILS WHERE STATUS = 'Issued' && USERNAME = '%s'", username);
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