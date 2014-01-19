package traveler.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import traveler.model.City;

@Controller
public class HomepageController {

	@RequestMapping("/")
	public String homepage() {
		
		return "homepage";
	}
	
}
