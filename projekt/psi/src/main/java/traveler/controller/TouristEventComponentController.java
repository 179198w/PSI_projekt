package traveler.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import traveler.command.TouristEventComponentCommand;
import traveler.command.TouristEventComponentFilterCommand;
import traveler.model.TouristEventComponentType;
import traveler.service.TouristEventComponentService;

@Controller
public class TouristEventComponentController {

	@Inject
	private TouristEventComponentService touristEventComponentService;
	
	@RequestMapping("/lista-skladnikow")
	public String listTouristEventComponents(Model model, TouristEventComponentFilterCommand filterCommand) {
		model.addAttribute("touristEventComponents", touristEventComponentService.listTouristEventComponent(filterCommand));
		return "listTouristEventComponents";
	}
	
	@RequestMapping(value = "/dodaj-skladnik", method = RequestMethod.GET)
	public String addTouristEventComponentForm(Model model) {
		model.addAttribute("touristEventComponentCommand", new TouristEventComponentCommand());
		model.addAttribute("types", TouristEventComponentType.values());
		return "addTouristEventComponent";
	}
	
	@RequestMapping(value = "/dodaj-skladnik", method = RequestMethod.POST)
	public String addTouristEventComponent(Model model, @Valid TouristEventComponentCommand touristEventComponentCommand, BindingResult result) {
		if(result.hasErrors()){
			return "addTouristEventComponent";
		}
		touristEventComponentService.addTouristEventComponent(touristEventComponentCommand);
		return "redirect:/lista-skladnikow";
	}
	
}
