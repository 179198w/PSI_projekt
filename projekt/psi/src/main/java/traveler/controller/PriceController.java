package traveler.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import traveler.command.PriceCommand;
import traveler.command.PriceEditCommand;
import traveler.command.PriceFilterCommand;
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
	public String listPrices(Model model, PriceFilterCommand priceFilterCommand) {
		model.addAttribute("prices",
				priceService.listPricesWithRelatedData(priceFilterCommand));
		model.addAttribute("types", PriceType.values());
		return "listPrices";
	}

	@RequestMapping(value = "/usun-cene", method = RequestMethod.POST)
	public String removePrice(Model model, Long priceId) throws Exception {
		priceService.removePrice(priceId);
		return "redirect:/lista-cen";
	}

	@RequestMapping(value = "/dodaj-cene", method = RequestMethod.GET)
	public String addPriceForm(Model model, PriceCommand priceCommand) {
		model.addAttribute("priceCommand", priceCommand);
		model.addAttribute("types", PriceType.values());
		model.addAttribute("touristEvents",
				touristEventService.listTouristEvents());
		model.addAttribute("periods", periodService.listPeriods());
		model.addAttribute("touristEventComponents",
				touristEventComponentService.listTouristEventComponent());
		return "addPrice";
	}

	@RequestMapping(value = "/dodaj-cene", method = RequestMethod.POST)
	public String addPrice(Model model, @Valid PriceCommand priceCommand,
			BindingResult result) {
		if (result.hasErrors()) {
			return addPriceForm(model, priceCommand);
		}
		priceService.addPrice(priceCommand);
		return "redirect:/lista-cen";
	}

	@RequestMapping(value = "/edytuj-cene", method = RequestMethod.POST, params = { "priceId" })
	public String editPriceForm(Model model,
			@RequestParam("priceId") Long priceId) {
		PriceEditCommand priceEditCommand = priceService
				.getPriceEditCommand(priceId);
		model.addAttribute("priceEditCommand", priceEditCommand);
		model.addAttribute("types", PriceType.values());
		model.addAttribute("touristEvents",
				touristEventService.listTouristEvents());
		model.addAttribute("periods",
				periodService.listPeriods(priceEditCommand.getTouristEventId()));
		model.addAttribute("touristEventComponents",
				touristEventComponentService
						.listTouristEventComponent(priceEditCommand
								.getTouristEventId()));
		return "editPrice";
	}

	@RequestMapping(value = "/edytuj-cene", method = RequestMethod.POST)
	public String editPriceForm(Model model,
			@Valid PriceEditCommand priceEditCommand, BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("types", PriceType.values());
			model.addAttribute("touristEvents",
					touristEventService.listTouristEvents());
			model.addAttribute("periods", periodService
					.listPeriods(priceEditCommand.getTouristEventId()));
			model.addAttribute("touristEventComponents",
					touristEventComponentService
							.listTouristEventComponent(priceEditCommand
									.getTouristEventId()));
			return "editPrice";
		}
		priceService.updatePrice(priceEditCommand);
		return "redirect:/lista-cen";
	}
}
