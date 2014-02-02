package traveler.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import traveler.command.CountryCommand;
import traveler.model.Country;
import traveler.repository.CountryRepository;
import traveler.service.CountryService;
import traveler.utils.MapperFacadeFactoryBean;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;

@Service
public class CountryServiceImpl implements CountryService {

	@Inject
	private CountryRepository countryRepository;

	@Inject
	private MapperFacadeFactoryBean mapperFacade;

	@Override
	@Cacheable(cacheName = "countries")
	public List<Country> listCountries() {
		return countryRepository.getAll();
	}

	@Override
	@Cacheable(cacheName = "countries")
	public List<Country> listCountries(String condition) {
		return countryRepository.getAllByPartString("name", condition);
	}

	@Override
	@TriggersRemove(cacheName = "countries", removeAll = true)
	public void addCountry(CountryCommand countryCommand) {
		Country country = mapperFacade.getObject().map(countryCommand,
				Country.class);
		countryRepository.save(country);
	}

	@Override
	@TriggersRemove(cacheName = { "countries", "hotels", "cities" }, removeAll = true)
	public void removeCountry(Long countryId) {
		countryRepository.delete(countryRepository.get(countryId));
	}

	@Override
	public Country getCountry(Long countryId) {
		return countryRepository.get(countryId);
	}

	@Override
	public CountryCommand getCountryCommand(Long countryId) {
		return mapperFacade.getObject().map(getCountry(countryId),
				CountryCommand.class);
	}

	@Override
	@TriggersRemove(cacheName = "countries", removeAll = true)
	public void updateCountry(CountryCommand countryCommand) {
		Country country = countryRepository.get(countryCommand.getId());
		mapperFacade.getObject().map(countryCommand, country);
		countryRepository.update(country);
	}

}
