package traveler.controller;

import java.util.List;

import lombok.Data;

import org.springframework.web.multipart.MultipartFile;

@Data
public class TouristEventCommand {
	
	private String name;
	
	private String description;
	
	private String operator;
	
	private Integer peopleLimit;
	
	private MultipartFile statue;
	
	private List<MultipartFile> photos;
	
	private Long countryId;
	
	private Long cityId;
	
	private Long hotelId;
	
	private List<String> touristEventComponentIds;
	
}
