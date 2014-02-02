package traveler.service;

import java.util.List;

import traveler.command.CountryCommand;
import traveler.model.Country;

public interface CountryService {

	List<Country> listCountries();

	List<Country> listCountries(String condition);
	
	void addCountry(CountryCommand countryCommand);
	
	void removeCountry(Long countryId);

	Country getCountry(Long countryId);

	CountryCommand getCountryCommand(Long countryId);

	void updateCountry(CountryCommand countryCommand);

}
