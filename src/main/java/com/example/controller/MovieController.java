package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MovieController {
	@GetMapping("/movies")
    public String getAll(Model model) {
        //model.addAttribute("persons", personService.findAll());
        return "movies";
    }
}

