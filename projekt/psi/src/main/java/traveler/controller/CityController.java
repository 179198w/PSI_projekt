package traveler.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import traveler.command.CityCommand;
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
	public String addCityForm(Model model, CityCommand cityCommand) {
		model.addAttribute("countries", countryService.listCountries());
		return "addCity";
	}

	@RequestMapping(value = "/dodaj-miasto", method = RequestMethod.POST)
	public String addCity(Model model, @Valid CityCommand cityCommand, BindingResult result) {
		if (result.hasErrors()) {
			return addCityForm(model, cityCommand);
		}
		cityService.addCity(cityCommand);
		return "redirect:/lista-miast";
	}

	@RequestMapping(value = "/edytuj-miasto", method = RequestMethod.POST)
	public String addCity(Model model, Long cityId) {
		CityCommand city = cityService.getCity(cityId);
		model.addAttribute("cityCommand", city);
		model.addAttribute("countries", countryService.listCountries());
		return "addCity";
	}

	@RequestMapping(value = "/usun-miasto", method = RequestMethod.POST)
	public String removeCountry(Model model, Long cityId) throws Exception {
		cityService.removeCity(cityId);
		return "redirect:/lista-miast";
	}

	@RequestMapping(value = "/szukaj-miasto", method = RequestMethod.POST)
	public String searchCities(Model model, String search) {
		if (!search.isEmpty()) {
			model.addAttribute("cities", cityService.listCities(search));
		} else {
			model.addAttribute("cities", cityService.listCities());
		}
		return "listCities";
	}

}
