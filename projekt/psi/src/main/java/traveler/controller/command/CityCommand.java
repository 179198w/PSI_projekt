package traveler.controller.command;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class CityCommand {

	private Long id;
	
	@NotEmpty
	private String name;
	
	@NotNull
	@Min(value=1)
	private Long countryId;
	
}
