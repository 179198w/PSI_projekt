package traveler.controller.command;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class CountryCommand {
	
	
	@NotEmpty
	@Min(value=1)
	private String name;
	
}
