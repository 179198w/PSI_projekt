package traveler.service.impl;

import static traveler.utils.FileUtils.md5DigestOfFile;
import static traveler.utils.FileUtils.saveFile;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import traveler.controller.command.TouristEventCommand;
import traveler.model.Hotel;
import traveler.model.Operator;
import traveler.model.TouristEvent;
import traveler.repository.HotelRepository;
import traveler.repository.OperatorRepository;
import traveler.repository.TouristEventRepository;
import traveler.service.TouristEventService;
import traveler.utils.MapperFacadeFactoryBean;

@Service
public class TouristEventServiceImpl implements TouristEventService {

	private static final String RELATIVE_FILE_URL = "files/";

	private static final String RELATIVE_FILE_PATH = "resources/files/";

	@Inject
	private TouristEventRepository touristEventRepository;
	
	@Inject
	private MapperFacadeFactoryBean mapperFacade;
	
	@Inject
	private OperatorRepository operatorRepository;
	
	@Inject
	private HotelRepository hotelRepository;
	
	@Override
	public List<TouristEvent> listTouristEvents() {
		return touristEventRepository.getAllWithOuterJoin("catalogs", "hotel", "hotel.city", "operator");
	}

	@Override
	public void addTouristEvent(TouristEventCommand touristEventCommand, String rootPath) {
		TouristEvent touristEvent = mapperFacade.getObject().map(touristEventCommand, TouristEvent.class);
		
		if (touristEventCommand.getOperator() != null) {
			Operator operator = operatorRepository.getBy("name", touristEventCommand.getOperator());
			if (operator == null) {
				operator = new Operator();
				operator.setName(touristEventCommand.getOperator());
				operatorRepository.save(operator);
			}
			touristEvent.setOperator(operator);
		}
		
		if (touristEventCommand.getHotelId() != null) {
			Hotel hotel = hotelRepository.get(touristEventCommand.getHotelId());
			touristEvent.setHotel(hotel);
		}
		
		MultipartFile statue = touristEventCommand.getStatue();
		if (!statue.isEmpty()) {
			try {
				String filename = md5DigestOfFile(statue);
				saveFile(statue, rootPath + RELATIVE_FILE_PATH + filename);
				touristEvent.setStatueUrl(RELATIVE_FILE_URL + filename);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		
		touristEventRepository.save(touristEvent);
	}

	@Override
	public void removeTouristEvent(Long touristEventId) {
		TouristEvent touristEvent = touristEventRepository.get(touristEventId);
		touristEventRepository.delete(touristEvent);
	}
	
}
