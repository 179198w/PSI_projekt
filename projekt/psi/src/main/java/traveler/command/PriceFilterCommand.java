package traveler.command;

import lombok.Data;
import traveler.model.PriceType;

@Data
public class PriceFilterCommand {

	private PriceType type;
		
}
