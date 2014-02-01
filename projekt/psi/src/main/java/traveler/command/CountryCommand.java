package traveler.command;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class CountryCommand {
	
	
	@NotEmpty
	private String name;
	
}
