package traveler.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import traveler.repository.TouristEventRepository;
import traveler.service.TouristEventService;

@Service
public class TouristEventServiceImpl implements TouristEventService {

	@Inject
	private TouristEventRepository touristEventRepository;
	
}
