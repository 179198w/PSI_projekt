package traveler.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import traveler.command.HotelCommand;
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

	@RequestMapping("/ajax/lista-hoteli")
	public String ajaxListCities(Model model, @RequestParam("cityId") Long cityId) {
		model.addAttribute("hotels", hotelService.listHotels(cityId));
		return "ajaxListHotels";
	}
	
	@RequestMapping("/lista-hoteli")
	public String listHotels(Model model) {
		model.addAttribute("hotels", hotelService.listHotels());
		return "listHotels";
	}

	@RequestMapping(value = "/dodaj-hotel", method = RequestMethod.GET)
	public String addHotelForm(Model model, HotelCommand hotelCommand) {
		model.addAttribute("hotelCommand", hotelCommand);
		model.addAttribute("countries", countryService.listCountries());
		model.addAttribute("cities", cityService.listCities());
		return "addHotel";
	}

	@RequestMapping(value = "/dodaj-hotel", method = RequestMethod.POST)
	public String addCity(Model model, @Valid HotelCommand hotelCommand, BindingResult result) {
		if (result.hasErrors()) {
			return addHotelForm(model, hotelCommand);
		}
		hotelService.addHotel(hotelCommand);
		return "redirect:/lista-hoteli";
	}

	@RequestMapping(value = "/usun-hotel", method = RequestMethod.POST)
	public String removeCountry(Model model, Long hotelId) throws Exception {
		hotelService.removeHotel(hotelId);
		return "redirect:/lista-hoteli";
	}

	@RequestMapping(value = "/szukaj-hotel", method = RequestMethod.POST)
	public String searchHotels(Model model, String search) {
		if (!search.isEmpty()) {
			model.addAttribute("hotels", hotelService.listHotels(search));
		} else {
			model.addAttribute("hotels", hotelService.listHotels());
		}
		return "listHotels";
	}

}
