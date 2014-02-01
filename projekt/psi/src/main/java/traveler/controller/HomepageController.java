package traveler.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import traveler.command.QueryCommand;

@Controller
public class HomepageController {

	@RequestMapping("/")
	public String homepage(Model model) {
		model.addAttribute("command", new QueryCommand());
		return "homepage";
	}
	
}
