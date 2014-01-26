package traveler.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.googlecode.ehcache.annotations.Cacheable;

import traveler.model.Hotel;
import traveler.repository.HotelRepository;
import traveler.service.HotelService;

@Service
public class HotelServiceImpl implements HotelService {

	@Inject
	private HotelRepository hotelRepository;

	@Override
	@Cacheable(cacheName="hotels")
	public List<Hotel> listHotels() {
		return hotelRepository.getAll();
	}
	
}
