package com.example.controller;

import com.example.domain.Movie;
import com.example.domain.enumeration.Genre;
import com.example.domain.enumeration.Interest;
import com.example.repository.MovieRepository;
import com.example.service.DBMovieService;
import com.example.service.MovieService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
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


    @GetMapping("/")
    public String root() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/auth/home")
    public String loggedinhome(Principal principal) {
//        try {
//            Optional<User> optional = userService.getUserWithAuthoritiesByLogin(principal.getName());
//            System.out.println("\n!!!\n!!! "  + principal.getName());
//            if (!optional.isPresent()) {
//                System.out.println("!!! creating social user");
//                userService.createSocialUser(principal.getName());
//            }
//            System.out.println("!!! user already exists");
//        } catch(Exception e) {
//            System.out.println("### " + e.getMessage());
//        }
        return "auth-home";
    }

    @GetMapping("/movies/genre/{id}")
    public String moviesByGenre(@PathVariable("id") int id, Model model){
        List<Movie> movies = dbMovieService.getMoviesByGenre(id);
        System.out.println(movies);
        model.addAttribute("movies", movies);
        model.addAttribute("title", Genre.getGenre(id));
        return "movies2";
    }

    @GetMapping("/movies/Popular")
    public String popularMovies(Model model){
        List<Movie> movies = dbMovieService.getPopularDbMovie();
        model.addAttribute("movies", movies);
        model.addAttribute("title", "Popular Movies");
        return "movies2";
    }

    @GetMapping("/movies/TopRated")
    public String topRatedMovies(Model model){
        List<Movie> movies = dbMovieService.getTopRatedMovies();
        model.addAttribute("movies", movies);
        model.addAttribute("title", "Top Rated Movies");
        return "movies2";
    }

    @GetMapping("/movies/Upcoming")
    public String upcomingMovies(Model model){
        List<Movie> movies = dbMovieService.getUpcomingMovies();
        model.addAttribute("movies", movies);
        model.addAttribute("title", "Upcoming Movies");
        return "movies2";
    }

    @GetMapping("/movies/NowPlaying")
    public String nowPlayingMovies(Model model){
        List<Movie> movies = dbMovieService.getNowPlayingMovies();
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
}

