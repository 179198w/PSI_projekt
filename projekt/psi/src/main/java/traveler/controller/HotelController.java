package traveler.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import traveler.controller.command.CityCommand;
import traveler.controller.command.CountryCommand;
import traveler.controller.command.HotelCommand;
import traveler.service.CityService;
import traveler.service.CountryService;
import traveler.service.HotelService;

@Controller
public class HotelController {

	@Inject
	private HotelService hotelService;

	@Inject
	private CityService cityService;

	@Inject
	private CountryService countryService;

	@RequestMapping("/lista-hoteli")
	public String listHotels(Model model) {
		model.addAttribute("hotels", hotelService.listHotels());
		return "listHotels";
	}

	@RequestMapping(value = "/dodaj-hotel", method = RequestMethod.GET)
	public String addHotelForm(Model model) {
		model.addAttribute("hotelCommand", new HotelCommand());
		model.addAttribute("countries", cityService.listCities());
		model.addAttribute("cities", countryService.listCountries());
		return "addHotel";
	}

	@RequestMapping(value = "/dodaj-hotel", method = RequestMethod.POST)
	public String addCity(Model model, @Valid HotelCommand hotelCommand,
			BindingResult result) {
		if (result.hasErrors()) {
			return "addHotel";
		}
		hotelService.addHotel(hotelCommand);
		return "redirect:/lista-hoteli";
	}

}
