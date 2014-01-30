package traveler.service;

import java.util.List;

import traveler.controller.command.CityCommand;
import traveler.model.City;

public interface CityService {

	List<City> listCities();

	void addCountry(CityCommand cityCommand);

}
