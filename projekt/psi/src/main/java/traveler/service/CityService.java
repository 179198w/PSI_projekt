package traveler.service;

import java.util.List;

import traveler.controller.command.CityCommand;
import traveler.model.City;

public interface CityService {

	List<City> listCities();
	
	List<City> listCities(String condition);

	void addCountry(CityCommand cityCommand);
	
	void removeCity(Long cityId);

}
