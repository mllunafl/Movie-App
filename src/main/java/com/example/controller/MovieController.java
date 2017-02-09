package com.example.controller;

import com.example.api.DBMovie;
import com.example.domain.Movie;
import com.example.service.DBMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
public class MovieController {



	@GetMapping("/movies")
    public String getAll(Model model) {
	    DBMovieService dbMovieService = new DBMovieService();
//	    DBMovie dbMovie = dbMovieService.getDbMovie();
        Movie movie = new Movie();
//        movie.setTitle(dbMovie.getTitle());
//        model.addAttribute("movies",movie);
//        return "movies";
       List<DBMovie> DBmovies = dbMovieService.getListDbMovie();
        List<Movie> movies = new ArrayList<>();

        Iterator<DBMovie> it = DBmovies.iterator();
        while (it.hasNext()) {
            Movie movie1 = new Movie();
            movie1.setTitle(it.next().getOriginalTitle());
            System.out.println(movie1);
            movies.add(movie1);

        }
        model.addAttribute("movies", movies);
        return "movies";
    }
}

