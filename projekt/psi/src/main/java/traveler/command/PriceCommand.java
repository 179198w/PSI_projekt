package traveler.command;

import java.util.List;

import lombok.Data;
import traveler.model.PriceType;

@Data
public class PriceCommand {

	private Long id;
	
	private Double adultValue;
	
	private Double childValue;

	private PriceType type;
	
	private Long touristEventId;
	
	private List<Long> periodIds;
	
	private Long touristEventComponentId;
	
}
