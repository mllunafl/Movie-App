package com.example.service;

import com.example.domain.Movie_watchlist;
import com.example.repository.Movie_watchlistRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing Movie_watchlist.
 */
@Service
@Transactional
public class Movie_watchlistService {

    private final Logger log = LoggerFactory.getLogger(Movie_watchlistService.class);
    
    private final Movie_watchlistRepository movie_watchlistRepository;

    public Movie_watchlistService(Movie_watchlistRepository movie_watchlistRepository) {
        this.movie_watchlistRepository = movie_watchlistRepository;
    }

    /**
     * Save a movie_watchlist.
     *
     * @param movie_watchlist the entity to save
     * @return the persisted entity
     */
    public Movie_watchlist save(Movie_watchlist movie_watchlist) {
        log.debug("Request to save Movie_watchlist : {}", movie_watchlist);
        Movie_watchlist result = movie_watchlistRepository.save(movie_watchlist);
        return result;
    }

    /**
     *  Get all the movie_watchlists.
     *  
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<Movie_watchlist> findAll() {
        log.debug("Request to get all Movie_watchlists");
        List<Movie_watchlist> result = movie_watchlistRepository.findAll();

        return result;
    }

    /**
     *  Get one movie_watchlist by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public Movie_watchlist findOne(Long id) {
        log.debug("Request to get Movie_watchlist : {}", id);
        Movie_watchlist movie_watchlist = movie_watchlistRepository.findOne(id);
        return movie_watchlist;
    }

    /**
     *  Delete the  movie_watchlist by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Movie_watchlist : {}", id);
        movie_watchlistRepository.delete(id);
    }
}
