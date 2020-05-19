import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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
}
