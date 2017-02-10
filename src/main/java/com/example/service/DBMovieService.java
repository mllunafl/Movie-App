package com.example.service;


import com.example.api.DBMovie;
import com.example.api.ResultsPage;
import com.example.domain.Movie;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by LunaFlores on 2/8/17.
 */
public class DBMovieService {
    private DBMovie dbMovie;


    public Movie getDbMovie(int dbmovieiId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.themoviedb.org/3/movie/" + dbmovieiId + "?api_key=f3d19e01363ab38b737c12cd2e9b704c&language=en-US";
        DBMovie dbMovie = restTemplate.getForObject(url, DBMovie.class);
        Movie movie = new Movie();
        movie.setTitle(dbMovie.getOriginalTitle());
        movie.setDbmovie_id(dbMovie.getId());
        movie.setPoster_path(dbMovie.getPosterPath());
        movie.setPoster_url(getdbMovieImgUrl(movie));
        return movie;
    }

    public List<Movie> getPopularDbMovie() {
        RestTemplate restTemplate = new RestTemplate();
        ResultsPage resultsPage = restTemplate.getForObject("https://api.themoviedb.org/3/movie/popular?api_key=f3d19e01363ab38b737c12cd2e9b704c&language=en-US&page=1", ResultsPage.class);

        List<DBMovie> dbMovies = resultsPage.getResults();
        //System.out.println(dbMovies);
        List<Movie> movies = new ArrayList<>();

        Iterator<DBMovie> it = dbMovies.iterator();
        while (it.hasNext()) {
            Movie movie1 = new Movie();
            DBMovie dbMovie =it.next();
            movie1.setTitle(dbMovie.getOriginalTitle());
            movie1.setPoster_path(dbMovie.getPosterPath());
            movie1.setDbmovie_id(dbMovie.getId());
            movie1.setPoster_url(getdbMovieImgUrl(movie1));
            //System.out.println(movie1);
            movies.add(movie1);

        }
        return movies;
    }

    public String getdbMovieImgUrl(Movie movie) {
        return "http://image.tmdb.org/t/p/original" + movie.getPoster_path();
    }

    public static void main(String[] args) {
        DBMovieService dbMovieService = new DBMovieService();
        List<Movie> movieList = new ArrayList<>();
        movieList = dbMovieService.getPopularDbMovie();
        System.out.println(movieList);
    }
}
