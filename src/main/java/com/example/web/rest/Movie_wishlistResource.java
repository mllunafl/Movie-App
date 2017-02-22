package com.example.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.example.domain.Movie_wishlist;
import com.example.service.Movie_wishlistService;
import com.example.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Movie_wishlist.
 */
@RestController
@RequestMapping("/api")
public class Movie_wishlistResource {

    private final Logger log = LoggerFactory.getLogger(Movie_wishlistResource.class);

    private static final String ENTITY_NAME = "movie_wishlist";
        
    private final Movie_wishlistService movie_wishlistService;

    public Movie_wishlistResource(Movie_wishlistService movie_wishlistService) {
        this.movie_wishlistService = movie_wishlistService;
    }

    /**
     * POST  /movie-wishlists : Create a new movie_wishlist.
     *
     * @param movie_wishlist the movie_wishlist to create
     * @return the ResponseEntity with status 201 (Created) and with body the new movie_wishlist, or with status 400 (Bad Request) if the movie_wishlist has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/movie-wishlists")
    @Timed
    public ResponseEntity<Movie_wishlist> createMovie_wishlist(@RequestBody Movie_wishlist movie_wishlist) throws URISyntaxException {
        log.debug("REST request to save Movie_wishlist : {}", movie_wishlist);
        if (movie_wishlist.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new movie_wishlist cannot already have an ID")).body(null);
        }
        Movie_wishlist result = movie_wishlistService.save(movie_wishlist);
        return ResponseEntity.created(new URI("/api/movie-wishlists/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /movie-wishlists : Updates an existing movie_wishlist.
     *
     * @param movie_wishlist the movie_wishlist to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated movie_wishlist,
     * or with status 400 (Bad Request) if the movie_wishlist is not valid,
     * or with status 500 (Internal Server Error) if the movie_wishlist couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/movie-wishlists")
    @Timed
    public ResponseEntity<Movie_wishlist> updateMovie_wishlist(@RequestBody Movie_wishlist movie_wishlist) throws URISyntaxException {
        log.debug("REST request to update Movie_wishlist : {}", movie_wishlist);
        if (movie_wishlist.getId() == null) {
            return createMovie_wishlist(movie_wishlist);
        }
        Movie_wishlist result = movie_wishlistService.save(movie_wishlist);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, movie_wishlist.getId().toString()))
            .body(result);
    }

    /**
     * GET  /movie-wishlists : get all the movie_wishlists.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of movie_wishlists in body
     */
    @GetMapping("/movie-wishlists")
    @Timed
    public List<Movie_wishlist> getAllMovie_wishlists() {
        log.debug("REST request to get all Movie_wishlists");
        return movie_wishlistService.findAll();
    }

    /**
     * GET  /movie-wishlists/:id : get the "id" movie_wishlist.
     *
     * @param id the id of the movie_wishlist to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the movie_wishlist, or with status 404 (Not Found)
     */
    @GetMapping("/movie-wishlists/{id}")
    @Timed
    public ResponseEntity<Movie_wishlist> getMovie_wishlist(@PathVariable Long id) {
        log.debug("REST request to get Movie_wishlist : {}", id);
        Movie_wishlist movie_wishlist = movie_wishlistService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(movie_wishlist));
    }

    /**
     * DELETE  /movie-wishlists/:id : delete the "id" movie_wishlist.
     *
     * @param id the id of the movie_wishlist to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/movie-wishlists/{id}")
    @Timed
    public ResponseEntity<Void> deleteMovie_wishlist(@PathVariable Long id) {
        log.debug("REST request to delete Movie_wishlist : {}", id);
        movie_wishlistService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
