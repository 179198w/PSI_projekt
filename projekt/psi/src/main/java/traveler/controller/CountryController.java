package traveler.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import traveler.command.CountryCommand;
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
	public String addCountryForm(Model model, CountryCommand countryCommand) {
		model.addAttribute("countryCommand", countryCommand);
		return "addCountry";
	}

	@RequestMapping(value = "/dodaj-panstwo", method = RequestMethod.POST)
	public String addCountry(Model model, @Valid CountryCommand countryCommand, BindingResult result) {
		if (result.hasErrors()) {
			return addCountryForm(model, countryCommand);
		}
		countryService.addCountry(countryCommand);
		return "redirect:/lista-panstw";
	}

	@RequestMapping(value = "/edytuj-panstwo", method = RequestMethod.POST, params = { "countryId" })
	public String editCountryForm(Model model, @RequestParam("countryId") Long countryId) {
		CountryCommand countryCommand = countryService.getCountryCommand(countryId);
		return addCountryForm(model, countryCommand);
	}

	@RequestMapping(value = "/edytuj-panstwo", method = RequestMethod.POST)
	public String editCountry(Model model, @Valid CountryCommand countryCommand, BindingResult result) {
		if (result.hasErrors()) {
			return addCountryForm(model, countryCommand);
		}
		countryService.updateCountry(countryCommand);
		return "redirect:/lista-panstw";
	}

	@RequestMapping(value = "/usun-panstwo", method = RequestMethod.POST)
	public String removeCountry(Model model, Long countryId) throws Exception {
		countryService.removeCountry(countryId);
		return "redirect:/lista-panstw";
	}

	@RequestMapping(value = "/szukaj-panstwo", method = RequestMethod.POST)
	public String searchCountry(Model model, String search) {
		if (!search.isEmpty()) {
			model.addAttribute("countries", countryService.listCountries(search));
		} else {
			model.addAttribute("countries", countryService.listCountries());
		}
		return "listCountries";
	}

}
