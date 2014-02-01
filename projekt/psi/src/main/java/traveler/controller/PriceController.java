package traveler.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import traveler.service.PriceService;

@Controller
public class PriceController {

	@Inject
	private PriceService priceService;
	
	@RequestMapping("/lista-cen")
	public String listPrices(Model model) {
		model.addAttribute("prices", priceService.listPricesWithRelatedData());
		return "listPrices";
	}
	
}
