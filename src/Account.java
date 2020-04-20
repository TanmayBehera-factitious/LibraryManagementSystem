import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Account {
	
	abstract public void showIssuedBooks();
	
	public long fineCalculator(String dateToReturn) {
		long fine;
		LocalDate ld = LocalDate.now();
		LocalDate dtr = LocalDate.parse(dateToReturn);
		fine = dtr.until(ld, ChronoUnit.DAYS) * 10;
		if(fine <= 0)
			return 0;
		return fine;
	}
	
	public boolean userVerify(String username) {
		boolean auth = true;
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
			Statement stmt;
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from accountinformation"); 
			
			while(rs.next()) {
				if(rs.getString(1).equals(username)) {
					auth = false;
					break;
				}
			}	
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return auth;
	}
}
