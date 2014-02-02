package traveler.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import traveler.command.TouristEventCommand;
import traveler.command.TouristEventFilterCommand;
import traveler.service.CityService;
import traveler.service.CountryService;
import traveler.service.HotelService;
import traveler.service.TouristEventComponentService;
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

	@Inject
	private TouristEventComponentService touristEventComponentService;

	@RequestMapping("/lista-imprez-turystycznych")
	public String listTouristEvents(Model model, TouristEventFilterCommand touristEventFilterCommand) {
		model.addAttribute("touristEvents", touristEventService.listTouristEventsWithRelatedData(touristEventFilterCommand));
		return "listTouristEvents";
	}

	@RequestMapping(value = "/dodaj-impreze-turystyczna", method = RequestMethod.GET)
	public String addTouristEventForm(Model model, TouristEventCommand touristEventCommand) {
		model.addAttribute("touristEventCommand", touristEventCommand);
		model.addAttribute("countries", countryService.listCountries());
		model.addAttribute("cities", cityService.listCities());
		model.addAttribute("hotels", hotelService.listHotels());
		model.addAttribute("touristEventComponents", touristEventComponentService.listTouristEventComponent());
		return "addTouristEvent";
	}

	@RequestMapping(value = "/dodaj-impreze-turystyczna", method = RequestMethod.POST)
	public String addTouristEvent(HttpServletRequest request, Model model, @Valid TouristEventCommand touristEventCommand, BindingResult result) throws Exception {
		if (result.hasErrors()) {
			return addTouristEventForm(model, touristEventCommand);
		}
		touristEventService.addTouristEvent(touristEventCommand, request.getSession().getServletContext().getRealPath("/"));
		return "redirect:/lista-imprez-turystycznych";
	}

	@RequestMapping(value = "/usun-impreze-turystyczna", method = RequestMethod.POST)
	public String removeTouristEvent(Model model, Long touristEventId) throws Exception {
		touristEventService.removeTouristEvent(touristEventId);
		return "redirect:/lista-imprez-turystycznych";
	}

	@RequestMapping(value = "/edytuj-impreze-turystyczna", method = RequestMethod.POST, params = { "touristEventId" })
	public String editTouristEventForm(Model model, @RequestParam("touristEventId") Long touristEventId) throws Exception {
		TouristEventCommand touristEventCommand = touristEventService.getTouristEventCommand(touristEventId);
		return addTouristEventForm(model, touristEventCommand);
	}
	
	@RequestMapping(value = "/edytuj-impreze-turystyczna", method = RequestMethod.POST)
	public String editTouristEvent(HttpServletRequest request, Model model, @Valid TouristEventCommand touristEventCommand, BindingResult result) {
		if (result.hasErrors()) {
			return addTouristEventForm(model, touristEventCommand);
		}
		touristEventService.updateTouristEventCommand(touristEventCommand, request.getSession().getServletContext().getRealPath("/"));
		return "redirect:/lista-imprez-turystycznych";
	}

	@RequestMapping(value = "/publikuj-lub-ukryj-impreze-turystyczna", method = RequestMethod.POST)
	public String publishOrHideTouristEvent(@RequestParam("touristEventId") Long touristEventId) throws Exception {
		touristEventService.publishOrHideTouristEvent(touristEventId);
		return "redirect:/lista-imprez-turystycznych";
	}

}
