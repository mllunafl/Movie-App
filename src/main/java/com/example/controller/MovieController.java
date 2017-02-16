package com.example.controller;

import com.example.domain.Movie;
import com.example.domain.enumeration.Genre;
import com.example.domain.enumeration.Interest;
import com.example.repository.MovieRepository;
import com.example.service.DBMovieService;
import com.example.service.MovieService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
public class MovieController {
    @Autowired
    DBMovieService dbMovieService;
    @Autowired
    MovieService movieService;


    @GetMapping("/movies/genre/{id}")
    public String moviesByGenre(@PathVariable("id") int id, Model model, Principal principal){
        List<Movie> movies = dbMovieService.getMoviesByGenre(id);
        getUser(model, principal);
        System.out.println(movies);
        model.addAttribute("movies", movies);
        model.addAttribute("title", Genre.getGenre(id));
        return "movies2";
    }

    @GetMapping("/movies/Popular")
    public String popularMovies(Model model, Principal principal){
        List<Movie> movies = dbMovieService.getPopularDbMovie();
        getUser(model, principal);
        model.addAttribute("movies", movies);
        model.addAttribute("title", "Popular Movies");
        return "movies2";
    }

    @GetMapping("/movies/TopRated")
    public String topRatedMovies(Model model, Principal principal){
        List<Movie> movies = dbMovieService.getTopRatedMovies();
        getUser(model, principal);
        model.addAttribute("movies", movies);
        model.addAttribute("title", "Top Rated Movies");
        return "movies2";
    }

    @GetMapping("/movies/Upcoming")
    public String upcomingMovies(Model model, Principal principal){
        List<Movie> movies = dbMovieService.getUpcomingMovies();
        getUser(model, principal);
        model.addAttribute("movies", movies);
        model.addAttribute("title", "Upcoming Movies");
        return "movies2";
    }

    @GetMapping("/movies/NowPlaying")
    public String nowPlayingMovies(Model model, Principal principal){
        List<Movie> movies = dbMovieService.getNowPlayingMovies();
        getUser(model, principal);
        model.addAttribute("movies", movies);
        model.addAttribute("title", "Now Playing Movies");
        return "movies2";
    }

    @GetMapping("/movies")
    public String getAll(Model model, Principal principal) {
        List<Movie> movies = dbMovieService.getPopularDbMovie().subList(0,4);
        System.out.println(movies);
        model.addAttribute("movies", movies);

//        if (principal == null) {
//            model.addAttribute("userId", null);
//        } else {
//            model.addAttribute("userId", principal.getName());
//        }
//        return "movies";
//    }
//
//    @PostMapping("/movies/interest")
//    @ResponseBody
//    public String changeInterest(Model model, Principal principal,
//                                 @RequestParam(value = "interest", required = true) String interest,
//                                 @RequestParam(value = "movieId", required = true) Integer movieId,
//                                 @RequestParam(value = "username", required = true) String username
//    ) {
//
//        System.out.println("username=" + username);
//        Movie movie = dbMovieService.getDbMovie(movieId);
//        movie.setInterest(Interest.valueOf(interest));
//        movieService.save(movie);
//        //System.out.println(movieService.findAll());

        return "homepage";
    }

    @GetMapping("/movies/login")
    public String getLogin(Model model) {
        return "login";
    }

    public void getUser(Model model, Principal principal) {
        if (principal == null) {
            model.addAttribute("userId", null);
        } else {
            model.addAttribute("userId", principal.getName());
        }
    }
}

