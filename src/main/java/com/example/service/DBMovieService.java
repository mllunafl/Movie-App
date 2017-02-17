package com.example.service;


import com.example.api.DBMovie;
import com.example.api.ResultsPage;
import com.example.domain.Movie;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by LunaFlores on 2/8/17.
 */

@Service
public class DBMovieService {


    private DBMovie dbMovie = new DBMovie();

    //API method call to get back a list of popular movies
    public List<Movie> getPopularDbMovie() {
        RestTemplate restTemplate = new RestTemplate();
        ResultsPage resultsPage = restTemplate.getForObject("https://api.themoviedb.org/3/movie/popular?api_key=f3d19e01363ab38b737c12cd2e9b704c&language=en-US&page=1", ResultsPage.class);
        List<Movie> movies = turnResultsToList(resultsPage);
        return movies;
    }

    //API method call to get back a list of top rated movies
    public List<Movie> getTopRatedMovies() {
        RestTemplate restTemplate = new RestTemplate();
        ResultsPage resultsPage = restTemplate.getForObject("https://api.themoviedb.org/3/movie/top_rated?api_key=f3d19e01363ab38b737c12cd2e9b704c&language=en-US&page=1", ResultsPage.class);
        List<Movie> movies = turnResultsToList(resultsPage);
        return movies;
    }

    //API method call to get back a list of upcoming movies
    public List<Movie> getUpcomingMovies() {
        RestTemplate restTemplate = new RestTemplate();
        ResultsPage resultsPage = restTemplate.getForObject("https://api.themoviedb.org/3/movie/upcoming?api_key=f3d19e01363ab38b737c12cd2e9b704c&language=en-US&page=1", ResultsPage.class);
        List<Movie> movies = turnResultsToList(resultsPage);
        return movies;
    }

    //API method call to get back a list of now playing movies
    public List<Movie> getNowPlayingMovies() {
        RestTemplate restTemplate = new RestTemplate();
        ResultsPage resultsPage = restTemplate.getForObject("https://api.themoviedb.org/3/movie/now_playing?api_key=f3d19e01363ab38b737c12cd2e9b704c&language=en-US&page=1", ResultsPage.class);
        List<Movie> movies = turnResultsToList(resultsPage);
        return movies;
    }

    //API method call to get back a list of movies by genre
    public List<Movie> getMoviesByGenre(int genreId) {
        RestTemplate restTemplate = new RestTemplate();
        ResultsPage resultsPage = restTemplate.getForObject("https://api.themoviedb.org/3/discover/movie?api_key=f3d19e01363ab38b737c12cd2e9b704c&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&with_genres=" + genreId, ResultsPage.class);
        List<Movie> movies = turnResultsToList(resultsPage);
        return movies;
    }

    //API method call to get back a single movie by api movie id
    public Movie getDbMovie(int dbmovieiId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.themoviedb.org/3/movie/" + dbmovieiId + "?api_key=f3d19e01363ab38b737c12cd2e9b704c&language=en-US";
        DBMovie dbMovie = restTemplate.getForObject(url, DBMovie.class);
        Movie movie = new Movie();
        movie.setTitle(dbMovie.getOriginalTitle());
        movie.setDbmovieId(dbMovie.getId());
        movie.setPoster_path(dbMovie.getPosterPath());
        movie.setPoster_url(getdbMovieImgUrl(movie));
        return movie;
    }

    //private method to take api object variable poster_path and return a string for our movie variable url_path
    private String getdbMovieImgUrl(Movie movie) {
        return "http://image.tmdb.org/t/p/original" + movie.getPoster_path();
    }

    //priavte method to interate through list of dbmovies from resultspage results and set and add movies to our movies list
    private List<Movie> turnResultsToList(ResultsPage resultsPage){
        List<DBMovie> dbMovies = resultsPage.getResults();
        //System.out.println(dbMovies);
        List<Movie> movies = new ArrayList<>();

        Iterator<DBMovie> it = dbMovies.iterator();
        while (it.hasNext()) {
            Movie movie1 = new Movie();
            DBMovie dbMovie =it.next();
            movie1.setTitle(dbMovie.getOriginalTitle());
            movie1.setPoster_path(dbMovie.getPosterPath());
            movie1.setDbmovieId(dbMovie.getId());
            movie1.setPoster_url(getdbMovieImgUrl(movie1));
            //System.out.println(movie1);
            movies.add(movie1);

        }
        return movies;
    }


    public static void main(String[] args) {
        DBMovieService dbMovieService = new DBMovieService();
        List<Movie> movieList;

        movieList = dbMovieService.getUpcomingMovies();
        System.out.println("size: " + movieList.size());
        System.out.println("upcoming" + movieList);

    }
}
