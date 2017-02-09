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

    public DBMovie getDbMovie(){
        RestTemplate restTemplate = new RestTemplate();
       return dbMovie = restTemplate.getForObject("https://api.themoviedb.org/3/movie/297761?api_key=f3d19e01363ab38b737c12cd2e9b704c&language=en-US", DBMovie.class);
    }

    public List<DBMovie> getListDbMovie(){
        RestTemplate restTemplate = new RestTemplate();
         ResultsPage resultsPage = restTemplate.getForObject("https://api.themoviedb.org/3/movie/popular?api_key=f3d19e01363ab38b737c12cd2e9b704c&language=en-US&page=1", ResultsPage.class );
       List<DBMovie> dbMovies = resultsPage.getResults();
       return dbMovies;
    }

    public static void main(String[] args) {
        DBMovieService dbMovieService = new DBMovieService();
        DBMovie Dbmovie = dbMovieService.getDbMovie();
        System.out.println(Dbmovie);
        Movie movie = new Movie();
        movie.setTitle(Dbmovie.getTitle());
        System.out.println(movie);


        List<DBMovie> movies = dbMovieService.getListDbMovie();

        System.out.println(movies);
        movie.setTitle(movies.get(2).getOriginalTitle());
        System.out.println(movie);
        List<Movie> movieList = new ArrayList<>();

        Iterator<DBMovie> it = movies.iterator();
        while (it.hasNext()) {
            Movie movie1 = new Movie();
            movie1.setTitle(it.next().getOriginalTitle());
            System.out.println(movie1);
            movieList.add(movie1);

        }
        System.out.println(movieList);
    }
}
