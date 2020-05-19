import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;


public class Login {
	private static Scanner sc = new Scanner(System.in);
	@SuppressWarnings("unused")
	private static Admin ad;
	@SuppressWarnings("unused")
	private static User usr;
	private static String username, password;
	private static boolean auth = true;
	
	public static void login() {
		try{
			
			// String username, password;
			System.out.println("Welcome to Prachin Pustak Bhanar.....");
			System.out.print("Enter username: ");
			username = sc.nextLine();
			
			System.out.print("Enter password: ");
			password = sc.nextLine();
			
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");  
			  
			Statement stmt = con.createStatement();  
			ResultSet rs = stmt.executeQuery("select * from accountinformation");  
			while(rs.next()) {
				if(rs.getString(1).equals(username) && rs.getString(2).equals(password)) {
					auth = false;
					if(rs.getString(3).equalsIgnoreCase("Admin"))
						ad = new Admin();
					else if(rs.getString(3).equalsIgnoreCase("User"))
						usr = new User(username);
					break;
				}
			}
			if(auth)
				System.out.println("Authentication Failed !!! Try Again");
			
			con.close();  
		}
		catch(Exception e){ 
			System.out.println(e);}  
		} 
	
	
}
