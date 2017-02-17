package com.example.service;

import com.example.domain.MovieWishlist;
import com.example.repository.MovieWishlistRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing MovieWishlist.
 */
@Service
@Transactional
public class MovieWishlistService {

    private final Logger log = LoggerFactory.getLogger(MovieWishlistService.class);

    private final MovieWishlistRepository movieWishlistRepository;

    public MovieWishlistService(MovieWishlistRepository movieWishlistRepository) {
        this.movieWishlistRepository = movieWishlistRepository;
    }

    /**
     * Save a movieWishlist.
     *
     * @param movieWishlist the entity to save
     * @return the persisted entity
     */
    public MovieWishlist save(MovieWishlist movieWishlist) {
        log.debug("Request to save MovieWishlist : {}", movieWishlist);
        MovieWishlist result = movieWishlistRepository.save(movieWishlist);
        return result;
    }

    /**
     *  Get all the movieWishlists.
     *
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<MovieWishlist> findAll() {
        log.debug("Request to get all MovieWishlists");
        List<MovieWishlist> result = movieWishlistRepository.findAll();

        return result;
    }

    /**
     *  Get one movieWishlist by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public MovieWishlist findOne(Long id) {
        log.debug("Request to get MovieWishlist : {}", id);
        MovieWishlist movieWishlist = movieWishlistRepository.findOne(id);
        return movieWishlist;
    }

    /**
     *  Delete the  movieWishlist by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete MovieWishlist : {}", id);
        movieWishlistRepository.delete(id);
    }
}
