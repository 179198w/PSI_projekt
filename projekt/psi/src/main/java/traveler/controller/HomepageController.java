package traveler.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import traveler.command.QueryCommand;

@Controller
public class HomepageController {

	@RequestMapping("/")
	public String homepage(Model model, QueryCommand queryCommand) {
		model.addAttribute("queryCommand", queryCommand);
		return "homepage";
	}
	
}
