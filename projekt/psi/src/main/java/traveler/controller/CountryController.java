package traveler.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
}
