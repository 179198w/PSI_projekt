package traveler.command;

import lombok.Data;
import traveler.model.PriceType;

@Data
public class PriceEditCommand {

	private Long id;
	
	private Double adultValue;
	
	private Double childValue;

	private PriceType type;
	
	private Long touristEventId;
	
	private Long periodId;
	
	private Long touristEventComponentId;
	
}
