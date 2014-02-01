package traveler.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import traveler.controller.command.CatalogCommand;
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
		model.addAttribute("cityCommand", new CityCommand());
		model.addAttribute("countries", countryService.listCountries());
		return "addCity";
	}
	
	@RequestMapping(value = "/dodaj-miasto", method = RequestMethod.POST)
	public String addCity(Model model,@Valid CityCommand cityCommand, BindingResult result) {
		if (result.hasErrors()) {
			return "addCity";
		}
		cityService.addCountry(cityCommand);
		return listCities(model);
	}
	
	@RequestMapping(value = "/usun-miasto", method = RequestMethod.POST)
	public String removeCountry(Model model, Long countryId) throws Exception {
		cityService.removeCity(countryId);
		return "redirect:/lista-miast";
	}
	
	
}
