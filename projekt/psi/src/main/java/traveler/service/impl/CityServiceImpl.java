package traveler.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import traveler.command.CityCommand;
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
	@Cacheable(cacheName = "cities")
	public List<City> listCities() {
		return cityRepository.getAll();
	}

	@Override
	public CityCommand getCityCommand(Long cityId) {
		City city = getCity(cityId);
		CityCommand cityCommand = mapperFacade.getObject().map(city, CityCommand.class);
		cityCommand.setCountryId(city.getCountry().getId());
		return cityCommand;
	}

	@Override
	public City getCity(Long cityId) {
		return cityRepository.get(cityId);
	}

	@Override
	@Cacheable(cacheName = "cities")
	public List<City> listCities(String condition) {
		return cityRepository.getAllByPartString("name", condition);
	}

	@Override
	@TriggersRemove(cacheName = "cities", removeAll = true)
	public void addCity(CityCommand cityCommand) {
		City city = mapperFacade.getObject().map(cityCommand, City.class);
		city.setCountry(countryRepository.get(cityCommand.getCountryId()));
		cityRepository.save(city);
	}

	@Override
	@TriggersRemove(cacheName = "cities", removeAll = true)
	public void removeCity(Long cityId) {
		City city = getCity(cityId);
		cityRepository.delete(city);
	}

	@Override
	public List<City> listCities(Long countryId) {
		return cityRepository.getAllBy("country.id", countryId);
	}

	@Override
	@TriggersRemove(cacheName = "cities", removeAll = true)
	public void updateCity(CityCommand cityCommand) {
		City city = mapperFacade.getObject().map(cityCommand, City.class);
		city.setCountry(countryRepository.get(cityCommand.getCountryId()));
		cityRepository.update(city);
	}

}
