package traveler.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import traveler.controller.command.CatalogCommand;
import traveler.service.CatalogService;
import traveler.service.TouristEventService;

@Controller
public class CatalogController {

	@Inject
	private CatalogService catalogService;
	
	@Inject
	private TouristEventService touristEventService;
	
	@RequestMapping("/lista-katalogow")
	public String listCatalogs(Model model) {
		model.addAttribute("catalogs", catalogService.listCatalogs());
		return "listCatalogs";
	}
	
	@RequestMapping(value = "/dodaj-katalog", method = RequestMethod.GET)
	public String addCatalogForm(Model model) {
		model.addAttribute("command", new CatalogCommand());
		model.addAttribute("touristEvents", touristEventService.listTouristEvents());
		return "addCatalog";
	}
	
	@RequestMapping(value = "/dodaj-katalog", method = RequestMethod.POST)
	public String addCatalog(Model model, CatalogCommand catalogCommand) {
		catalogService.addCatalog(catalogCommand);
		return listCatalogs(model);
	}
	
}
