package traveler.controller.command;

import lombok.Data;

@Data
public class CityCommand {

	private String name;
	
	private Long countryId;
	
}
