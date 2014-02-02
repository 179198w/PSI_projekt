package traveler.command;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;
import traveler.model.PriceType;

@Data
public class PriceCommand {

	private Long id;
	
	@Max(value=100000)
	@Min(value=0)
	@NotNull
	private Double adultValue;
	
	@Max(value=100000)
	@Min(value=0)
	@NotNull
	private Double childValue;

	@NotNull
	private PriceType type;
	
	private Long touristEventId;
	
	@NotNull
	private List<Long> periodIds;
	
	private Long touristEventComponentId;
	
}
