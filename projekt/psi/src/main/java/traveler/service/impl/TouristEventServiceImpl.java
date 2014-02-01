package traveler.service.impl;

import static com.google.common.collect.Lists.newArrayList;
import static traveler.utils.FileUtils.md5DigestOfFile;
import static traveler.utils.FileUtils.saveFile;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import traveler.controller.command.TouristEventCommand;
import traveler.controller.command.TouristEventFilterCommand;
import traveler.model.Hotel;
import traveler.model.Operator;
import traveler.model.TouristEvent;
import traveler.model.TouristEventComponent;
import traveler.repository.HotelRepository;
import traveler.repository.OperatorRepository;
import traveler.repository.TouristEventComponentRepository;
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
	
	@Inject
	private TouristEventComponentRepository touristEventComponentRepository;

	@Override
	public List<TouristEvent> listTouristEventsWithRelatedData(TouristEventFilterCommand touristEventFilterCommand) {
		return touristEventRepository.getAllWithRelatedData(touristEventFilterCommand);
	}

	@Override
	public List<TouristEvent> listTouristEvents() {
		return touristEventRepository.getAll();
	}

	@Override
	@Transactional
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

		List<String> photosUrls = newArrayList();
		for (MultipartFile photo : touristEventCommand.getPhotos()) {
			if (!photo.isEmpty()) {
				try {
					String filename = md5DigestOfFile(photo);
					saveFile(photo, rootPath + RELATIVE_FILE_PATH + filename);
					photosUrls.add(RELATIVE_FILE_URL + filename);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
		touristEvent.setPhotoUrls(photosUrls);
		
		List<TouristEventComponent> touristEventComponents = newArrayList();
		for (Long touristEventComponentId : touristEventCommand.getTouristEventComponentIds()) {
			TouristEventComponent touristEventComponent = touristEventComponentRepository.get(touristEventComponentId);
			touristEventComponent.getTouristEvents().add(touristEvent);
			touristEventComponents.add(touristEventComponent);
		}
		touristEvent.setTouristEventComponents(touristEventComponents);
		
		touristEventRepository.save(touristEvent);
	}

	@Override
	public void removeTouristEvent(Long touristEventId) {
		TouristEvent touristEvent = touristEventRepository.get(touristEventId);
		touristEventRepository.delete(touristEvent);
	}

	@Override
	public void publishOrHideTouristEvent(Long touristEventId) {
		TouristEvent touristEvent = touristEventRepository.get(touristEventId);
		touristEvent.setVisible(!touristEvent.getVisible());
		touristEventRepository.update(touristEvent);
	}

}
