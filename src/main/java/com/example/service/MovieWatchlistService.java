package com.example.service;

import com.example.domain.MovieWatchlist;
import com.example.repository.MovieWatchlistRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

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
}
