package traveler.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import traveler.command.HotelCommand;
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
	@Cacheable(cacheName = "hotels")
	public List<Hotel> listHotels() {
		return hotelRepository.getAll();
	}

	@Override
	@Cacheable(cacheName = "hotels")
	public List<Hotel> listHotels(String condition) {
		return hotelRepository.getAllByPartString("name", condition);
	}

	@Override
	@TriggersRemove(cacheName = "hotels", removeAll = true)
	public void addHotel(HotelCommand hotelCommand) {
		Hotel hotel = mapperFacade.getObject().map(hotelCommand, Hotel.class);
		hotel.setCity(cityRepository.get(hotelCommand.getCityId()));
		hotelRepository.save(hotel);
	}

	@Override
	@TriggersRemove(cacheName = "hotels", removeAll = true)
	public void removeHotel(Long hotelId) {
		hotelRepository.delete(hotelRepository.get(hotelId));
	}

	@Override
	public List<Hotel> listHotels(Long cityId) {
		return hotelRepository.getAllBy("city.id", cityId);
	}

	@Override
	public HotelCommand getHotelCommand(Long hotelId) {
		Hotel hotel = getHotel(hotelId);
		HotelCommand hotelCommand = mapperFacade.getObject().map(hotel, HotelCommand.class);
		hotelCommand.setCityId(hotel.getCity().getId());
		hotelCommand.setCountryId(hotel.getCity().getCountry().getId());
		return hotelCommand;
	}

	@Override
	public Hotel getHotel(Long hotelId) {
		return hotelRepository.get(hotelId);
	}

	@Override
	@TriggersRemove(cacheName = "hotels", removeAll = true)
	public void updateHotel(HotelCommand hotelCommand) {
		Hotel hotel = mapperFacade.getObject().map(hotelCommand, Hotel.class);
		hotel.setCity(cityRepository.get(hotelCommand.getCityId()));
		hotelRepository.update(hotel);
	}

}
