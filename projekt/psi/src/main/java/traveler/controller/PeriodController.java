package traveler.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import traveler.command.PeriodCommand;
import traveler.command.PeriodEditCommand;
import traveler.command.PeriodFilterCommand;
import traveler.service.PeriodService;
import traveler.service.TouristEventService;

@Controller
public class PeriodController {

	@Inject
	private PeriodService periodService;

	@Inject
	private TouristEventService touristEventService;

	@RequestMapping("/ajax/lista-terminow")
	public String ajaxListCities(Model model,
			@RequestParam("touristEventId") Long touristEventId) {
		model.addAttribute("periods", periodService.listPeriods(touristEventId));
		return "ajaxListPeriods";
	}

	@RequestMapping("/lista-terminow")
	public String listPeriods(Model model, PeriodFilterCommand filterCommand) {
		model.addAttribute("periods", periodService.listPeriods(filterCommand));
		return "listPeriods";
	}

	@RequestMapping(value = "/dodaj-termin", method = RequestMethod.GET)
	public String addPeriodForm(Model model, PeriodCommand periodCommand) {
		model.addAttribute("touristEvents",
				touristEventService.listTouristEvents());
		return "addPeriod";
	}

	@RequestMapping(value = "/usun-termin", method = RequestMethod.POST)
	public String removePeriod(Model model, Long periodId) throws Exception {
		periodService.removePeriod(periodId);
		return "redirect:/lista-terminow";
	}

	@RequestMapping(value = "/dodaj-termin", method = RequestMethod.POST)
	public String addPeriod(Model model, @Valid PeriodCommand periodCommand,
			BindingResult result) {
		periodCommand.setAdditionalErrorInfo("");
		if (result.hasErrors()) {
			return addPeriodForm(model, periodCommand);
		}
		if (periodCommand.getTo().isBefore(periodCommand.getFrom())
				|| periodCommand.getTo().isEqual(periodCommand.getFrom())) {
			periodCommand
					.setAdditionalErrorInfo("Data od musi być przed datą do");
			return addPeriodForm(model, periodCommand);
		}
		if (periodCommand.getRepeatPeriod() != null
				&& periodCommand.getRepeatPeriod().booleanValue()) {
			if (periodCommand.getRepeatCount() == null
					|| periodCommand.getPeriodSpace() == null) {
				periodCommand
						.setAdditionalErrorInfo("Liczba powtórzeń i/lub odstęp terminów są niewłaściwie wypełnione");
			}
		}
		periodService.addPeriod(periodCommand);
		return "redirect:/lista-terminow";
	}

	@RequestMapping(value = "/edytuj-termin", method = RequestMethod.POST, params = { "periodId" })
	public String editPeriodForm(Model model,
			@RequestParam("periodId") Long periodId) {
		PeriodEditCommand periodEditCommand = periodService
				.getPeriodEditCommand(periodId);
		model.addAttribute("periodEditCommand", periodEditCommand);
		model.addAttribute("touristEvents",
				touristEventService.listTouristEvents());
		return "editPeriod";
	}

	@RequestMapping(value = "/edytuj-termin", method = RequestMethod.POST)
	public String editPeriod(Model model,
			@Valid PeriodEditCommand periodEditCommand, BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("touristEvents",
					touristEventService.listTouristEvents());
			return "editPeriod";
		}
		periodService.updatePeriod(periodEditCommand);
		return "redirect:/lista-terminow";
	}

}
