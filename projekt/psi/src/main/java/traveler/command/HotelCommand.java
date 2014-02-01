package traveler.command;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class HotelCommand {

	@NotEmpty
	private String name;
	
	@NotEmpty
	private String address;
	
	private String description;
	
	private Integer numerOfStars;
	
	@NotNull
	@Min(value=1)
	private Long countryId;
	
	@NotNull
	@Min(value=1)
	private Long cityId;
	
}
