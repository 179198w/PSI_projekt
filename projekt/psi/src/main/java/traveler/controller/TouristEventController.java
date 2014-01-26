package traveler.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import traveler.controller.command.TouristEventCommand;
import traveler.service.CityService;
import traveler.service.CountryService;
import traveler.service.HotelService;
import traveler.service.TouristEventService;

@Controller
public class TouristEventController {
	
	@Inject
	private TouristEventService touristEventService;
	
	@Inject
	private CountryService countryService;
	
	@Inject
	private CityService cityService;
	
	@Inject
	private HotelService hotelService;
	
	@RequestMapping("/lista-imprez-turystycznych")
	public String listTouristEvents(Model model) {
		model.addAttribute("touristEvents", touristEventService.listTouristEvents());
		return "listTouristEvents";
	}
	
	@RequestMapping(value = "/dodaj-impreze-turystyczna", method = RequestMethod.GET)
	public String addTouristEventForm(Model model) {
		model.addAttribute("command", new TouristEventCommand());
		model.addAttribute("countries", countryService.listCountries());
		model.addAttribute("cities", cityService.listCities());
		model.addAttribute("hotels", hotelService.listHotels());
		return "addTouristEvent";
	}
	
	@RequestMapping(value = "/dodaj-impreze-turystyczna", method = RequestMethod.POST)
	public String addTouristEvent(HttpServletRequest request, Model model, TouristEventCommand touristEventCommand) throws Exception {
		touristEventService.addTouristEvent(touristEventCommand, request.getSession().getServletContext().getRealPath("/"));
		return listTouristEvents(model);
	}
	
	@RequestMapping(value = "/usun-impreze-turystyczna", method = RequestMethod.POST)
	public String removeTouristEvent(Model model, Long touristEventId) throws Exception {
		touristEventService.removeTouristEvent(touristEventId);
		return listTouristEvents(model);
	}
	
	@RequestMapping(value = "/edytuj-impreze-turystyczna", method = RequestMethod.POST)
	public String editTouristEvent(Model model, Long touristEventId) throws Exception {
		
		return "editTouristEvent";
	}
	
}
