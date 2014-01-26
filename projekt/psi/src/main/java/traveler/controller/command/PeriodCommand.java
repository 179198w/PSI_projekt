package traveler.controller.command;

import java.util.List;

import lombok.Data;

import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class PeriodCommand {

	@DateTimeFormat(pattern="dd-MM-yyyy")
	private LocalDate from;

	@DateTimeFormat(pattern="dd-MM-yyyy")
	private LocalDate to;
	
	private Boolean repeatPeriod;
	
	private Integer repeatCount;
	
	private Integer periodSpace;
	
	private String periodSpaceType;
	
	private List<Long> touristEvents;
	
}
