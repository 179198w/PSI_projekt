package traveler.command;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;

import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class PeriodEditCommand {

	private Long id;
	
	@NotNull
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private LocalDate from;

	@NotNull
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private LocalDate to;
	
	@NotNull
	@Min(value = 1)
	private Long touristEventId;
	
}
