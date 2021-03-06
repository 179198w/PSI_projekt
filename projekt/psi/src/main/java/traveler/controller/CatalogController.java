package traveler.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import traveler.command.CatalogCommand;
import traveler.command.CatalogFilterCommand;
import traveler.service.CatalogService;
import traveler.service.TouristEventService;

@Controller
public class CatalogController {

	@Inject
	private CatalogService catalogService;
	
	@Inject
	private TouristEventService touristEventService;
	
	@RequestMapping("/lista-katalogow")
	public String listCatalogs(Model model, CatalogFilterCommand filterCommand) {
		model.addAttribute("catalogs", catalogService.listCatalogs(filterCommand));
		return "listCatalogs";
	}
	
	@RequestMapping(value = "/dodaj-katalog", method = RequestMethod.GET)
	public String addCatalogForm(Model model, CatalogCommand catalogCommand) {
		model.addAttribute("catalogCommand", catalogCommand);
		model.addAttribute("touristEvents", touristEventService.listTouristEvents());
		return "addCatalog";
	}
	
	@RequestMapping(value = "/dodaj-katalog", method = RequestMethod.POST)
	public String addCatalog(Model model, @Valid CatalogCommand catalogCommand, BindingResult result) {
		if (result.hasErrors()) {
			return addCatalogForm(model, catalogCommand);
		}
		catalogService.addCatalog(catalogCommand);
		return "redirect:/lista-katalogow";
	}
	
	@RequestMapping(value = "/edytuj-katalog", method = RequestMethod.POST, params = { "catalogId" })
	public String editCatalogForm(Model model, @RequestParam("catalogId") Long catalogId) {
		CatalogCommand catalogCommand = catalogService.getCatalogCommand(catalogId);
		return addCatalogForm(model, catalogCommand);
	}
	
	@RequestMapping(value = "/usun-katalog", method = RequestMethod.POST, params = { "catalogId" })
	public String removeCatalog(Model model, @RequestParam("catalogId") Long catalogId) {
		catalogService.removeCatalog(catalogId);
		return "redirect:/lista-katalogow";
	}
	
	@RequestMapping(value = "/edytuj-katalog", method = RequestMethod.POST)
	public String editCatalog(Model model, @Valid CatalogCommand catalogCommand, BindingResult result) {
		if (result.hasErrors()) {
			return addCatalogForm(model, catalogCommand);
		}
		catalogService.updateCatalog(catalogCommand);
		return "redirect:/lista-katalogow";
	}
	
}
