package net.slipp.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WelcomeController {

	@GetMapping("/helloworld")
	public String welcome(String name, @RequestParam(value = "i", required = false, defaultValue = "33") Integer age, Model model){
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		System.out.println("name : " + name);
		return "welcome";
	}
}
