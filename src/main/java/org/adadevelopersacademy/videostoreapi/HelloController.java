package org.adadevelopersacademy.videostoreapi;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class HelloController {
	public class Video {
		public String title;
	}

	@RequestMapping("/foo/{name}")
	public String printWelcome(ModelMap model, @PathVariable String name, @RequestParam(required=false, defaultValue="") String age) {

		model.addAttribute("message", name + age);
		return "hello";
	}

	@RequestMapping("/foo/get2")
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "It worked");
		return "hello";
	}

}