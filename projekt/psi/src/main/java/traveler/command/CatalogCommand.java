package traveler.command;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class CatalogCommand {
	
	private Long id;
	
	@NotEmpty
	private String name;
	
	private List<Long> touristEventIds;
	
}
