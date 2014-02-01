package traveler.command;

import lombok.Data;

import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class PeriodFilterCommand {

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate from;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate to;

}
