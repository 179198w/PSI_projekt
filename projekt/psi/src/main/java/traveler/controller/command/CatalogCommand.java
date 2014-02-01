package traveler.controller.command;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class CatalogCommand {
	
	@NotEmpty
	private String name;
	
	private List<Long> touristEvents;
	
}
