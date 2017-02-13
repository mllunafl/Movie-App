package com.example.controller;

import com.example.domain.Movie;
import com.example.service.DBMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
public class MovieController {


    @PostMapping("/movies")
    @ResponseBody
    public String postMovies(Model model) {
        System.out.println("hey we did it!!");
        return "ok";
    }

	@GetMapping("/movies")
    public String getAll(Model model) {
	    DBMovieService dbMovieService = new DBMovieService();
        List<Movie> movies = dbMovieService.getPopularDbMovie();
        System.out.println(movies);
        model.addAttribute("movies", movies);
        //model.addAttribute("image", movies.get(1).getPoster_url());
        return "movies";
    }

    @GetMapping("/movies/login")
    public String getLogin(Model model) {
	    return "login";
    }
}

