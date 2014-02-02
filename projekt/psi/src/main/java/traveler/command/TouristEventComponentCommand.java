package traveler.command;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;
import traveler.model.TouristEventComponentType;

@Data
public class TouristEventComponentCommand {
	
	private Long id;
	
	@NotEmpty
	private String name;
	
	private String description;
	
	@NotNull
	private TouristEventComponentType type;
	
}
