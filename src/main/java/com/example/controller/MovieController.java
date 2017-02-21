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

import javax.servlet.http.HttpServletRequest;
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

    private static final String PAGE = "page";


    @GetMapping("/")
    public String root(HttpServletRequest request, Principal principal) {
        String page = (String)request.getSession().getAttribute(PAGE);
        if (page != null) {
            request.getSession().removeAttribute(PAGE);
            if ("popular".equals(page)) {
                return "redirect:/movies/popular";
            } else if("upcoming".equals(page)){
                return "redirect:/movies/upcoming";
            } else if("toprated".equals(page)){
                return "redirect:/movies/toprated";
            } else if ("nowplaying".equals(page)){
                return "redirect:/movies/nowplaying";
            } else {
                return "redirect:/movies/genre/" + page;
            }
        }

        /*
        ACTION(28,"Action"),
    COMEDY(35,"Comedy"),
    DRAMA(18, "Drama"),
    FAMILY(10751, "Family"),
    HORROR(27, "Horror"),
    ROMANCE(10749,"Romance");
        *\
         */

        String referer = request.getHeader("referer");
        if (principal != null) {
            System.out.println("this is the principle " +principal.getName());
        }
        if (referer != null && principal != null) {
            this.addSocialUser(principal.getName());
            return "redirect:/home";
        } else {
            return "redirect:/home";
        }
    }

    @GetMapping("/home")
    public String home(HttpServletRequest request, Model model) {
        request.getSession().removeAttribute(PAGE);
        List<Movie> movies = dbMovieService.getTopRatedMovies().subList(0,4);
        List<Movie> movieList = dbMovieService.getUpcomingMovies().subList(0,4);
        System.out.println(movies);
        model.addAttribute("topmovies", movies);
        model.addAttribute("upmovies", movieList);
        return "homepage";
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
        return "homepage";
    }


    @GetMapping("/movies/genre/{id}")
    public String moviesByGenre(@PathVariable("id") int id, Model model, Principal principal, HttpServletRequest request){
        request.getSession().setAttribute(PAGE, Integer.toString(id));
        List<Movie> movieList = dbMovieService.getMoviesByGenre(id);
        String user = getUser(model, principal);
        if (null == user){
            model.addAttribute("movies", movieList);
        } else if(!"null".equals(user)) {
            List<Movie> movies = this.setInterest(movieList);
            model.addAttribute("movies", movies);
        }
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
        String user = getUser(model, principal);
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
    public String popularMovies(HttpServletRequest request, Model model, Principal principal){
        request.getSession().setAttribute(PAGE, "popular");
        List<Movie> movieList = dbMovieService.getPopularDbMovie();
        String user = getUser(model, principal);
        if (null == user){
            model.addAttribute("movies", movieList);
        } else if(!"null".equals(user)) {
            List<Movie> movies = this.setInterest(movieList);
            model.addAttribute("movies", movies);
        }
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

    @GetMapping("/movies/toprated")
    public String topRatedMovies(HttpServletRequest request, Model model, Principal principal){
        request.getSession().setAttribute(PAGE, "toprated");
        List<Movie> movieList = dbMovieService.getTopRatedMovies();
        String user = getUser(model, principal);
        if (null == user){
            model.addAttribute("movies", movieList);
        } else if(!"null".equals(user)) {
            List<Movie> movies = this.setInterest(movieList);
            model.addAttribute("movies", movies);
        }
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
    public String upcomingMovies(HttpServletRequest request, Model model, Principal principal){
        request.getSession().setAttribute(PAGE, "upcoming");
        List<Movie> movieList = dbMovieService.getUpcomingMovies();
        String user = getUser(model, principal);
        if (null == user){
            model.addAttribute("movies", movieList);
        } else if(!"null".equals(user)) {
            List<Movie> movies = this.setInterest(movieList);
            model.addAttribute("movies", movies);
        }
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
    public String nowPlayingMovies(HttpServletRequest request, Model model, Principal principal){
        request.getSession().setAttribute(PAGE, "nowplaying");
        List<Movie> movieList = dbMovieService.getNowPlayingMovies();
        String user = getUser(model, principal);
        if (null == user){
            model.addAttribute("movies", movieList);
        } else if(!"null".equals(user)) {
            List<Movie> movies = this.setInterest(movieList);
            model.addAttribute("movies", movies);
        }
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

    public String getUser(Model model, Principal principal) {
        if (principal == null) {
            model.addAttribute("userId", null);
            return null;
        } else {
            model.addAttribute("userId", principal.getName());
            return principal.getName();
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
        System.out.println("in post string username " + username);
        if("delete".equals(interest)){
            watchlistService.deleteByDbid(movie.getDbmovieId());
            wishlistService.deleteByDbid(movie.getDbmovieId());
        } else if(Interest.SEEN_IT == Interest.valueOf(interest)) {
            wishlistService.deleteByDbid(movie.getDbmovieId());
            movie.setInterest(Interest.valueOf(interest));
            MovieWatchlist movieWatchlist = new MovieWatchlist();
            movieWatchlist.setDbmovieId(movie.getDbmovieId());
            Optional<User> optional = userService.getUserWithAuthoritiesByLogin(username);
            System.out.println("in seen it in post optional " + optional);
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

    private void addSocialUser(String login) {
        try {
            Optional<User> optional = userService.getUserWithAuthoritiesByLogin(login);
            System.out.println("\n!!!\n!!! "  + login);
            if (!optional.isPresent()) {
                System.out.println("!!! creating social user");
                userService.createSocialUser(login);
            }
            System.out.println("!!! user already exists");
        } catch(Exception e) {
            System.out.println("### " + e.getMessage());
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





