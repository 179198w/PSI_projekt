package traveler.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import traveler.model.Country;
import traveler.repository.CountryRepository;
import traveler.service.CountryService;

import com.googlecode.ehcache.annotations.Cacheable;

@Service
public class CountryServiceImpl implements CountryService {

	@Inject
	private CountryRepository countryRepository;

	@Override
	@Cacheable(cacheName="countries")
	public List<Country> listCountries() {
		return countryRepository.getAll();
	}
	
}
