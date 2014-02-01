package traveler.service;

import java.util.List;

import traveler.controller.command.CountryCommand;
import traveler.model.City;
import traveler.model.Country;

public interface CountryService {

	List<Country> listCountries();

	List<Country> listCountries(String condition);
	
	void addCountry(CountryCommand countryCommand);
	
	void removeCountry(Long countryId);

}
