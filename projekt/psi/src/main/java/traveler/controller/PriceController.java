package traveler.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import traveler.command.PriceCommand;
import traveler.model.PriceType;
import traveler.service.PeriodService;
import traveler.service.PriceService;
import traveler.service.TouristEventComponentService;
import traveler.service.TouristEventService;

@Controller
public class PriceController {

	@Inject
	private PriceService priceService;

	@Inject
	private TouristEventService touristEventService;
	
	@Inject
	private PeriodService periodService;
	
	@Inject
	private TouristEventComponentService touristEventComponentService;
	
	@RequestMapping("/lista-cen")
	public String listPrices(Model model) {
		model.addAttribute("prices", priceService.listPricesWithRelatedData());
		return "listPrices";
	}

	@RequestMapping(value = "/dodaj-cene", method = RequestMethod.GET)
	public String addPriceForm(Model model, PriceCommand priceCommand) {
		model.addAttribute("priceCommand", priceCommand);
		model.addAttribute("types", PriceType.values());
		model.addAttribute("touristEvents", touristEventService.listTouristEvents());
		model.addAttribute("periods", periodService.listPeriods());
		model.addAttribute("touristEventComponents", touristEventComponentService.listTouristEventComponent());
		return "addPrice";
	}

	@RequestMapping(value = "/dodaj-cene", method = RequestMethod.POST)
	public String addTouristEventComponent(Model model, @Valid PriceCommand priceCommand, BindingResult result) {
		if (result.hasErrors()) {
			return addPriceForm(model, priceCommand);
		}
		priceService.addPrice(priceCommand);
		return "redirect:/lista-cen";
	}

}
