package traveler.controller.command;

import lombok.Data;

@Data
public class HotelCommand {

	private String name;
	
	private Long countryId;
	
	private Long cityId;
	
}
