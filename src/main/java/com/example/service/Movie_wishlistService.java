package com.example.service;

import com.example.domain.Movie_wishlist;
import com.example.repository.Movie_wishlistRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing Movie_wishlist.
 */
@Service
@Transactional
public class Movie_wishlistService {

    private final Logger log = LoggerFactory.getLogger(Movie_wishlistService.class);
    
    private final Movie_wishlistRepository movie_wishlistRepository;

    public Movie_wishlistService(Movie_wishlistRepository movie_wishlistRepository) {
        this.movie_wishlistRepository = movie_wishlistRepository;
    }

    /**
     * Save a movie_wishlist.
     *
     * @param movie_wishlist the entity to save
     * @return the persisted entity
     */
    public Movie_wishlist save(Movie_wishlist movie_wishlist) {
        log.debug("Request to save Movie_wishlist : {}", movie_wishlist);
        Movie_wishlist result = movie_wishlistRepository.save(movie_wishlist);
        return result;
    }

    /**
     *  Get all the movie_wishlists.
     *  
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<Movie_wishlist> findAll() {
        log.debug("Request to get all Movie_wishlists");
        List<Movie_wishlist> result = movie_wishlistRepository.findAll();

        return result;
    }

    /**
     *  Get one movie_wishlist by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public Movie_wishlist findOne(Long id) {
        log.debug("Request to get Movie_wishlist : {}", id);
        Movie_wishlist movie_wishlist = movie_wishlistRepository.findOne(id);
        return movie_wishlist;
    }

    /**
     *  Delete the  movie_wishlist by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Movie_wishlist : {}", id);
        movie_wishlistRepository.delete(id);
    }
}
