package traveler.command;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

@Data
public class TouristEventCommand {
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String description;
	
	@NotEmpty
	private String operator;
	
	@Min(value=1)
	@NotNull
	private Integer peopleLimit;
	
	@NotNull
	private MultipartFile statue;
	
	@NotNull
	private List<MultipartFile> photos;
	
	@Min(value=1)
	@NotNull
	private Long countryId;
	
	@Min(value=1)
	@NotNull
	private Long cityId;
	
	@Min(value=1)
	@NotNull
	private Long hotelId;
	
	@NotNull
	@Size(min=3,max=30)
	private List<Long> touristEventComponentIds;
	
}
