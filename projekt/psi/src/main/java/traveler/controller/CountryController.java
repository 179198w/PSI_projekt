package traveler.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import traveler.controller.command.CountryCommand;
import traveler.service.CountryService;

@Controller
public class CountryController {

	@Inject
	private CountryService countryService;
	
	@RequestMapping("/lista-panstw")
	public String listCountries(Model model) {
		model.addAttribute("countries", countryService.listCountries());
		return "listCountries";
	}
	
	@RequestMapping(value = "/dodaj-panstwo", method = RequestMethod.GET)
	public String addCountryForm(Model model) {
		model.addAttribute("command", new CountryCommand());
		return "addCountry";
	}
	
	@RequestMapping(value = "/dodaj-panstwo", method = RequestMethod.POST)
	public String addCountry(Model model, CountryCommand countryCommand) {
		countryService.addCountry(countryCommand);
		return listCountries(model);
	}
	
}
