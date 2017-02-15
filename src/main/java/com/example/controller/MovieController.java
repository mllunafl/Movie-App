package com.example.controller;

import com.example.domain.Movie;
import com.example.domain.Pager;
import com.example.domain.enumeration.Genre;
import com.example.domain.enumeration.Interest;
import com.example.repository.MovieRepository;
import com.example.service.DBMovieService;
import com.example.service.MovieService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
public class MovieController {
    private static final int BUTTONS_TO_SHOW = 5;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 5;
    private static final int[] PAGE_SIZES = { 5, 10, 20 };

    @Autowired
    DBMovieService dbMovieService;
    @Autowired
    MovieService movieService;


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

    @GetMapping
    public String popularMovies(@RequestParam(value = "pageSize", required = false) Integer pageSize,
                                @RequestParam(value = "page", required = false) Integer page) {
        int evalPageSize = pageSize == null ? INITIAL_PAGE_SIZE : pageSize;
        int evalPage = (page == null || page < 1) ? INITIAL_PAGE : page - 1;

        Page<Movie> movies = movieService.findAllPageable(new PageRequest(evalPage, evalPageSize));
        Pager pager = new Pager(persons.getTotalPages(), persons.getNumber(), BUTTONS_TO_SHOW);

    }
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView showPersonsPage(@RequestParam(value = "pageSize", required = false) Integer pageSize,
                                        @RequestParam(value = "page", required = false) Integer page) {
        ModelAndView modelAndView = new ModelAndView("persons");

        // Evaluate page size. If requested parameter is null, return initial
        // page size
        int evalPageSize = pageSize == null ? INITIAL_PAGE_SIZE : pageSize;
        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.
        int evalPage = (page == null || page < 1) ? INITIAL_PAGE : page - 1;

        Page<Person> persons = personService.findAllPageable(new PageRequest(evalPage, evalPageSize));
        Pager pager = new Pager(persons.getTotalPages(), persons.getNumber(), BUTTONS_TO_SHOW);

        modelAndView.addObject("persons", persons);
        modelAndView.addObject("selectedPageSize", evalPageSize);
        modelAndView.addObject("pageSizes", PAGE_SIZES);
        modelAndView.addObject("pager", pager);
        return modelAndView;
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

