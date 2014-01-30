package traveler.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import traveler.service.HotelService;

@Controller
public class HotelController {

	@Inject
	private HotelService hotelService;
	
	@RequestMapping("/lista-hoteli")
	public String listHotels(Model model) {
		model.addAttribute("hotels", hotelService.listHotels());
		return "listHotels";
	}
	
}
