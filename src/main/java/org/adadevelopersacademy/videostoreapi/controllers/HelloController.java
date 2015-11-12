package org.adadevelopersacademy.videostoreapi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/hello")
public class HelloController {
    @RequestMapping("/{name}")
    public String printWelcome(
            ModelMap model,
            @PathVariable String name,
            @RequestParam(required = false, defaultValue = "") String age
    ) {
        model.addAttribute("message", name + age);
        return "hello";
    }
}
