package traveler.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import traveler.controller.command.CityCommand;
import traveler.model.City;
import traveler.repository.CityRepository;
import traveler.repository.CountryRepository;
import traveler.service.CityService;
import traveler.utils.MapperFacadeFactoryBean;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;

@Service
public class CityServiceImpl implements CityService {

	@Inject
	private CityRepository cityRepository;
	
	@Inject
	private CountryRepository countryRepository;

	@Inject
	private MapperFacadeFactoryBean mapperFacade;
	
	@Override
	@Cacheable(cacheName="cities")
	public List<City> listCities() {
		return cityRepository.getAll();
	}
	
	@Override
	@Cacheable(cacheName="cities")
	public List<City> listCities(String condition){
		return cityRepository.getAllByPartString("name", condition);
	}

	@Override
	@TriggersRemove(cacheName = "cities", removeAll = true)
	public void addCountry(CityCommand cityCommand) {
		City city = mapperFacade.getObject().map(cityCommand, City.class);
		city.setCountry(countryRepository.get(cityCommand.getCountryId()));
		cityRepository.save(city);
	}
	
	@Override
	@TriggersRemove(cacheName = "cities", removeAll = true)
	public void removeCity(Long cityId){
		City city=cityRepository.get(cityId);
		cityRepository.delete(city);
	}
	
}
