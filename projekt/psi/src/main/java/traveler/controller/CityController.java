package traveler.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import traveler.controller.command.CityCommand;
import traveler.service.CityService;
import traveler.service.CountryService;

@Controller
public class CityController {

	@Inject
	private CityService cityService;
	
	@Inject
	private CountryService countryService;
	
	@RequestMapping("/lista-miast")
	public String listCities(Model model) {
		model.addAttribute("cities", cityService.listCities());
		return "listCities";
	}
	
	@RequestMapping(value = "/dodaj-miasto", method = RequestMethod.GET)
	public String addCityForm(Model model) {
		model.addAttribute("command", new CityCommand());
		model.addAttribute("countries", countryService.listCountries());
		return "addCity";
	}
	
	@RequestMapping(value = "/dodaj-miasto", method = RequestMethod.POST)
	public String addCity(Model model, CityCommand cityCommand) {
		cityService.addCountry(cityCommand);
		return listCities(model);
	}
	
}
