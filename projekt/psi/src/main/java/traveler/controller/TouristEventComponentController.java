package traveler.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String addTouristEventComponentForm(Model model, TouristEventComponentCommand touristEventComponentCommand) {
		model.addAttribute("touristEventComponentCommand", touristEventComponentCommand);
		model.addAttribute("types", TouristEventComponentType.values());
		return "addTouristEventComponent";
	}
	
	@RequestMapping(value = "/dodaj-skladnik", method = RequestMethod.POST)
	public String addTouristEventComponent(Model model, @Valid TouristEventComponentCommand touristEventComponentCommand, BindingResult result) {
		if(result.hasErrors()){
			return addTouristEventComponentForm(model, touristEventComponentCommand);
		}
		touristEventComponentService.addTouristEventComponent(touristEventComponentCommand);
		return "redirect:/lista-skladnikow";
	}
	
	@RequestMapping(value = "/edytuj-skladnik", method = RequestMethod.POST, params = { "touristEventComponentId" })
	public String editTouristEventComponentForm(Model model, @RequestParam("touristEventComponentId") Long touristEventComponentId) {
		TouristEventComponentCommand touristEventComponentCommand = touristEventComponentService.getTouristEventComponentCommand(touristEventComponentId);
		return addTouristEventComponentForm(model, touristEventComponentCommand);
	}
	
	@RequestMapping(value = "/edytuj-skladnik", method = RequestMethod.POST)
	public String editTouristEventComponent(Model model, @Valid TouristEventComponentCommand touristEventComponentCommand, BindingResult result) {
		if (result.hasErrors()) {
			return addTouristEventComponentForm(model, touristEventComponentCommand);
		}
		touristEventComponentService.updateCatalog(touristEventComponentCommand);
		return "redirect:/lista-skladnikow";
	}
	
}
