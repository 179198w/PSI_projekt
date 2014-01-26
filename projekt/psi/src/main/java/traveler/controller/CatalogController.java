package traveler.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import traveler.service.CatalogService;

@Controller
public class CatalogController {

	@Inject
	private CatalogService catalogService;
	
	@RequestMapping("/lista-katalogow")
	public String listCatalogs(Model model) {
		model.addAttribute("catalogs", catalogService.listCatalogs());
		return "listCatalogs";
	}
	
}
