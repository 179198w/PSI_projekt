package traveler.service;

import java.util.List;

import traveler.command.CityCommand;
import traveler.model.City;

public interface CityService {

	List<City> listCities();
	
	List<City> listCities(String condition);

	void addCity(CityCommand cityCommand);
	
	CityCommand getCityCommand(Long id);
	
	void removeCity(Long cityId);

	List<City> listCities(Long countryId);

	City getCity(Long cityId);

	void updateCity(CityCommand cityCommand);

}
