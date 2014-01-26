package traveler.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.googlecode.ehcache.annotations.Cacheable;

import traveler.model.City;
import traveler.repository.CityRepository;
import traveler.service.CityService;

@Service
public class CityServiceImpl implements CityService {

	@Inject
	private CityRepository cityRepository;

	@Override
	@Cacheable(cacheName="cities")
	public List<City> listCities() {
		return cityRepository.getAll();
	}
	
}
