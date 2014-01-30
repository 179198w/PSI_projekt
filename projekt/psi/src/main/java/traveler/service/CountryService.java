package traveler.service;

import java.util.List;

import traveler.controller.command.CountryCommand;
import traveler.model.Country;

public interface CountryService {

	List<Country> listCountries();

	void addCountry(CountryCommand countryCommand);

}
