package traveler.controller.command;

import java.util.List;

import lombok.Data;

@Data
public class CatalogCommand {
	
	private String name;
	
	private List<Long> touristEvents;
	
}
