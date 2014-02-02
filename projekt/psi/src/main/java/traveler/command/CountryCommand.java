package traveler.command;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class CountryCommand {
	
	private Long id;
	
	@NotEmpty
	private String name;
	
}
