package traveler.controller;

import lombok.Data;
import traveler.model.TouristEventComponentType;

@Data
public class TouristEventComponentCommand {
	
	private String name;
	
	private String description;
	
	private TouristEventComponentType type;
	
}
