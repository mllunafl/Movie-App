package com.example.service;

import com.example.domain.Movie;
import com.example.domain.MovieWatchlist;
import com.example.domain.MovieWishlist;
import com.example.domain.User;
import com.example.repository.MovieWatchlistRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Service Implementation for managing MovieWatchlist.
 */
@Service
@Transactional
public class MovieWatchlistService {

    private final Logger log = LoggerFactory.getLogger(MovieWatchlistService.class);

    private final MovieWatchlistRepository movieWatchlistRepository;

    public MovieWatchlistService(MovieWatchlistRepository movieWatchlistRepository) {
        this.movieWatchlistRepository = movieWatchlistRepository;
    }

    /**
     * Save a movieWatchlist.
     *
     * @param movieWatchlist the entity to save
     * @return the persisted entity
     */
    public MovieWatchlist save(MovieWatchlist movieWatchlist) {
        log.debug("Request to save MovieWatchlist : {}", movieWatchlist);
        MovieWatchlist result = movieWatchlistRepository.save(movieWatchlist);
        return result;
    }


    public List<MovieWatchlist> findByUser() {
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.debug("Request to get all MovieWishlists");
        List<MovieWatchlist> result;
        if (obj instanceof User) {
            System.out.println("finding by user");
            result = movieWatchlistRepository.findByUserIsCurrentUser();
        } else {
            System.out.println("finding by social user");
            result = movieWatchlistRepository.findBySocialUserIsCurrentUser();
        }

        return result;
    }


    /**
     *  Get all the movieWatchlists.
     *
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<MovieWatchlist> findAll() {
        log.debug("Request to get all MovieWatchlists");
        List<MovieWatchlist> result = movieWatchlistRepository.findAll();

        return result;
    }

    /**
     *  Get one movieWatchlist by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public MovieWatchlist findOne(Long id) {
        log.debug("Request to get MovieWatchlist : {}", id);
        MovieWatchlist movieWatchlist = movieWatchlistRepository.findOne(id);
        return movieWatchlist;
    }



    /**
     *  Delete the  movieWatchlist by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete MovieWatchlist : {}", id);
        movieWatchlistRepository.delete(id);
    }

    public void deleteByDbid(int dbmovieId) {
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<MovieWatchlist> movieWatchlists = new ArrayList<>();
        if (obj instanceof User) {
            System.out.println("finding by user");
            movieWatchlists = movieWatchlistRepository.findByUserIsCurrentUser();
        } else {
            System.out.println("finding by social user");
            movieWatchlists = movieWatchlistRepository.findBySocialUserIsCurrentUser();
        }
        for (MovieWatchlist movieWatchlist : movieWatchlists){
            if (dbmovieId == movieWatchlist.getDbmovieId()){
                delete(movieWatchlist.getId());
            }
        }
    }

    public List<Movie> turnResultsToList(){
        DBMovieService dbMovieService = new DBMovieService();
        List<MovieWatchlist> movieWatchlists = this.findByUser();
        List<Movie> movies = new ArrayList<>();

        Iterator<MovieWatchlist> it = movieWatchlists.iterator();
        while (it.hasNext()) {
            MovieWatchlist movieWatchlist =it.next();
            Movie movie1 = dbMovieService.getDbMovie(movieWatchlist.getDbmovieId());
            movies.add(movie1);
        }
        return movies;
    }
}
