package traveler.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import traveler.controller.TouristEventCommand;
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
	public void addTouristEvent(TouristEventCommand touristEventCommand) {
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
		
		touristEventRepository.save(touristEvent);
	}

	@Override
	public void removeTouristEvent(Long touristEventId) {
		TouristEvent touristEvent = touristEventRepository.get(touristEventId);
		touristEventRepository.delete(touristEvent);
	}
	
}
