package traveler.controller.command;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;
import traveler.model.TouristEventComponentType;

@Data
public class TouristEventComponentCommand {
	
	@NotEmpty
	private String name;
	
	private String description;
	
	@NotNull
	private TouristEventComponentType type;
	
}
