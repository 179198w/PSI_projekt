package traveler.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import traveler.controller.command.HotelCommand;
import traveler.model.Hotel;
import traveler.repository.CityRepository;
import traveler.repository.HotelRepository;
import traveler.service.HotelService;
import traveler.utils.MapperFacadeFactoryBean;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;

@Service
public class HotelServiceImpl implements HotelService {

	@Inject
	private HotelRepository hotelRepository;

	@Inject
	private CityRepository cityRepository;
	
	@Inject
	private MapperFacadeFactoryBean mapperFacade;
	
	@Override
	@Cacheable(cacheName="hotels")
	public List<Hotel> listHotels() {
		return hotelRepository.getAll();
	}

	@Override
	@TriggersRemove(cacheName = "hotels", removeAll = true)
	public void addHotel(HotelCommand hotelCommand) {
		Hotel hotel = mapperFacade.getObject().map(hotelCommand, Hotel.class);
		hotel.setCity(cityRepository.get(hotelCommand.getCityId()));
		hotelRepository.save(hotel);
	}
	
}
