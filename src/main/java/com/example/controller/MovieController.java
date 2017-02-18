package com.example.controller;

import com.example.domain.Movie;
import com.example.domain.MovieWatchlist;
import com.example.domain.MovieWishlist;
import com.example.domain.User;
import com.example.domain.enumeration.Genre;
import com.example.domain.enumeration.Interest;
import com.example.repository.MovieRepository;
import com.example.service.*;
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
import java.util.Optional;

@Controller
public class MovieController {
    @Autowired
    DBMovieService dbMovieService;
    @Autowired
    MovieService movieService;
    @Autowired
    MovieWatchlistService watchlistService;
    @Autowired
    MovieWishlistService wishlistService;
    @Autowired
    UserService userService;


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
        return "login2";
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
    public String moviesByGenre(@PathVariable("id") int id, Model model, Principal principal){
        List<Movie> movieList = dbMovieService.getMoviesByGenre(id);
        List<Movie> movies = this.setInterest(movieList);
        getUser(model, principal);
        System.out.println(movies);
        model.addAttribute("movies", movies);
        model.addAttribute("title", Genre.getGenre(id));
        return "movies2";
    }

    @PostMapping("/movies/genre/{id}/interest")
    @ResponseBody
    public String saveMovieGenreItererst(@PathVariable("id") int id, Model model, Principal principal,
                                 @RequestParam(value = "interest", required = true) String interest,
                                 @RequestParam(value = "movieId", required = true) Integer movieId,
                                 @RequestParam(value = "username", required = true) String username
    ) {
        System.out.println("\n\n In post for genre" + interest + movieId + username);
        Movie movie = dbMovieService.getDbMovie(movieId);
        this.postInterest(movie, interest, username);
        List<Movie> movies = dbMovieService.getMoviesByGenre(id);
        model.addAttribute("movies", movies);
        model.addAttribute("title", Genre.getGenre(id));
        return "movies2";
    }

    @GetMapping("/movies/seenit")
    public String seenItMovies(Model model, Principal principal){
        List<Movie> movieList  = watchlistService.turnResultsToList();
        List<Movie> movies = this.setInterest(movieList);
        getUser(model, principal);
        model.addAttribute("movies", movies);
        model.addAttribute("title", "My 'Seen It' List");
        return "movies2";
    }

    @PostMapping("/movies/seenit/interest")
    @ResponseBody
    public String saveMovieSeenItererst(Model model, Principal principal,
                                    @RequestParam(value = "interest", required = true) String interest,
                                    @RequestParam(value = "movieId", required = true) Integer movieId,
                                    @RequestParam(value = "username", required = true) String username
    ) {
        System.out.println("\n\n In post for Seen It" + interest + movieId + username);
        Movie movie = dbMovieService.getDbMovie(movieId);
        this.postInterest(movie, interest, username);
        List<Movie> movies = watchlistService.turnResultsToList();
        model.addAttribute("movies", movies);
        model.addAttribute("title", "My 'Seen It' List");
        return "movies2";
    }

    @GetMapping("/movies/wanasee")
    public String wanaSeeItMovies(Model model, Principal principal){
        List<Movie> movieList  = wishlistService.turnResultsToList();
        List<Movie> movies = this.setInterest(movieList);
        getUser(model, principal);
        model.addAttribute("movies", movies);
        model.addAttribute("title", "Popular Movies");
        return "movies2";
    }

    @PostMapping("/movies/wanasee/interest")
    @ResponseBody
    public String saveMovieWanaItererst(Model model, Principal principal,
                                        @RequestParam(value = "interest", required = true) String interest,
                                        @RequestParam(value = "movieId", required = true) Integer movieId,
                                        @RequestParam(value = "username", required = true) String username
    ) {
        Movie movie = dbMovieService.getDbMovie(movieId);
        this.postInterest(movie, interest, username);
        List<Movie> movies = wishlistService.turnResultsToList();
        model.addAttribute("movies", movies);
        model.addAttribute("title", "My 'Seen It' List");
        return "movies2";
    }

    @GetMapping("/movies/popular")
    public String popularMovies(Model model, Principal principal){
        List<Movie> movieList = dbMovieService.getPopularDbMovie();
        List<Movie> movies = this.setInterest(movieList);
        getUser(model, principal);
        model.addAttribute("movies", movies);
        model.addAttribute("title", "Popular Movies");
        return "movies2";
    }

    @PostMapping("/movies/popular/interest")
    @ResponseBody
    public String saveMoviePopularItererst(Model model, Principal principal,
                                        @RequestParam(value = "interest", required = true) String interest,
                                        @RequestParam(value = "movieId", required = true) Integer movieId,
                                        @RequestParam(value = "username", required = true) String username
    ) {
        Movie movie = dbMovieService.getDbMovie(movieId);
        this.postInterest(movie, interest, username);
        List<Movie> movies = dbMovieService.getPopularDbMovie();
        model.addAttribute("movies", movies);
        model.addAttribute("title", "Popular");
        return "movies2";
    }

    @GetMapping("/movies/topRated")
    public String topRatedMovies(Model model, Principal principal){
        List<Movie> movieList = dbMovieService.getTopRatedMovies();
        List<Movie> movies = this.setInterest(movieList);
        getUser(model, principal);
        model.addAttribute("movies", movies);
        model.addAttribute("title", "Top Rated Movies");
        return "movies2";
    }

    @PostMapping("/movies/toprated/interest")
    @ResponseBody
    public String saveMovieTopItererst(Model model, Principal principal,
                                           @RequestParam(value = "interest", required = true) String interest,
                                           @RequestParam(value = "movieId", required = true) Integer movieId,
                                           @RequestParam(value = "username", required = true) String username
    ) {
        Movie movie = dbMovieService.getDbMovie(movieId);
        this.postInterest(movie, interest, username);
        List<Movie> movies = dbMovieService.getTopRatedMovies();
        model.addAttribute("movies", movies);
        model.addAttribute("title", "Top Rated Movies");
        return "movies2";
    }

    @GetMapping("/movies/upcoming")
    public String upcomingMovies(Model model, Principal principal){
        List<Movie> movieList = dbMovieService.getUpcomingMovies();
        List<Movie> movies = this.setInterest(movieList);
        getUser(model, principal);
        model.addAttribute("movies", movies);
        model.addAttribute("title", "Upcoming Movies");
        return "movies2";
    }

    @PostMapping("/movies/upcoming/interest")
    @ResponseBody
    public String saveMovieUpItererst(Model model, Principal principal,
                                       @RequestParam(value = "interest", required = true) String interest,
                                       @RequestParam(value = "movieId", required = true) Integer movieId,
                                       @RequestParam(value = "username", required = true) String username
    ) {
        Movie movie = dbMovieService.getDbMovie(movieId);
        this.postInterest(movie, interest, username);
        List<Movie> movies = dbMovieService.getUpcomingMovies();
        model.addAttribute("movies", movies);
        model.addAttribute("title", "Top Rated Movies");
        return "movies2";
    }

    @GetMapping("/movies/nowplaying")
    public String nowPlayingMovies(Model model, Principal principal){
        List<Movie> movieList = dbMovieService.getNowPlayingMovies();
        List<Movie> movies = this.setInterest(movieList);
        getUser(model, principal);
        model.addAttribute("movies", movies);
        model.addAttribute("title", "Now Playing Movies");
        return "movies2";
    }

    @PostMapping("/movies/nowplaying/interest")
    @ResponseBody
    public String saveMovieNowItererst(Model model, Principal principal,
                                      @RequestParam(value = "interest", required = true) String interest,
                                      @RequestParam(value = "movieId", required = true) Integer movieId,
                                      @RequestParam(value = "username", required = true) String username
    ) {
        Movie movie = dbMovieService.getDbMovie(movieId);
        this.postInterest(movie, interest, username);
        List<Movie> movies = dbMovieService.getNowPlayingMovies();
        model.addAttribute("movies", movies);
        model.addAttribute("title", "Now Playing Movies");
        return "movies2";
    }

    @GetMapping("/movies")
    public String getAll(Model model, Principal principal) {
        List<Movie> movies = dbMovieService.getTopRatedMovies().subList(0,4);
        List<Movie> movieList = dbMovieService.getUpcomingMovies().subList(0,4);
        System.out.println(movies);
        model.addAttribute("topmovies", movies);
        model.addAttribute("upmovies", movieList);
        return "homepage";
    }

    @GetMapping("/movies/login")
    public String getLogin(Model model) {
        return "login";
    }


    public void saveMovie(Model model, Principal principal){

    }

    public void getUser(Model model, Principal principal) {
        if (principal == null) {
            model.addAttribute("userId", null);
        } else {
            model.addAttribute("userId", principal.getName());
        }
    }

    private List<Movie> setInterest(List<Movie> movies){
        List<MovieWishlist> wishListMovies = wishlistService.findByUser();
        List<MovieWatchlist> watchListMovies = watchlistService.findByUser();
        for (Movie movie : movies){
            for(MovieWishlist movieWishlist : wishListMovies){
                if (movie.getDbmovieId().equals(movieWishlist.getDbmovieId())){
                    movie.setInterest(Interest.WANT_TO_SEE);
                    break;
                }
            }
        }

        for (Movie movie : movies){
            for(MovieWatchlist movieWatchlist : watchListMovies){
                if (movie.getDbmovieId().equals(movieWatchlist.getDbmovieId())){
                    movie.setInterest(Interest.SEEN_IT);
                    break;
                }
            }
        }
        return movies;
    }

    private void postInterest(Movie movie, String interest, String username) {
        if("delete".equals(interest)){
            watchlistService.deleteByDbid(movie.getDbmovieId());
            wishlistService.deleteByDbid(movie.getDbmovieId());
            System.out.println("need to delete!!!!!");
        } else if(Interest.SEEN_IT == Interest.valueOf(interest)) {
            wishlistService.deleteByDbid(movie.getDbmovieId());
            movie.setInterest(Interest.valueOf(interest));
            MovieWatchlist movieWatchlist = new MovieWatchlist();
            movieWatchlist.setDbmovieId(movie.getDbmovieId());
            Optional<User> optional = userService.getUserWithAuthoritiesByLogin(username);
            if (optional.isPresent()) {
                User user = optional.get();
                movieWatchlist.setUser(user);
            }

            watchlistService.save(movieWatchlist);
        }else {
            if (Interest.WANT_TO_SEE == Interest.valueOf(interest)){
                watchlistService.deleteByDbid(movie.getDbmovieId());
                MovieWishlist movieWishlist = new MovieWishlist();
                movieWishlist.setDbmovieId(movie.getDbmovieId());
                Optional<User>  optional = userService.getUserWithAuthoritiesByLogin(username);
                if (optional.isPresent()){
                    User user = optional.get();
                    movieWishlist.setUser(user);
                }

                wishlistService.save(movieWishlist);
            }
        }
    }
}

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
