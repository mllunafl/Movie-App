package com.example.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.example.domain.MovieWishlist;
import com.example.service.MovieWishlistService;
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
 * REST controller for managing MovieWishlist.
 */
@RestController
@RequestMapping("/api")
public class MovieWishlistResource {

    private final Logger log = LoggerFactory.getLogger(MovieWishlistResource.class);

    private static final String ENTITY_NAME = "movieWishlist";

    private final MovieWishlistService movieWishlistService;

    public MovieWishlistResource(MovieWishlistService movieWishlistService) {
        this.movieWishlistService = movieWishlistService;
    }

    /**
     * POST  /movie-wishlists : Create a new movieWishlist.
     *
     * @param movieWishlist the movieWishlist to create
     * @return the ResponseEntity with status 201 (Created) and with body the new movieWishlist, or with status 400 (Bad Request) if the movieWishlist has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/movie-wishlists")
    @Timed
    public ResponseEntity<MovieWishlist> createMovieWishlist(@RequestBody MovieWishlist movieWishlist) throws URISyntaxException {
        log.debug("REST request to save MovieWishlist : {}", movieWishlist);
        if (movieWishlist.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new movieWishlist cannot already have an ID")).body(null);
        }
        MovieWishlist result = movieWishlistService.save(movieWishlist);
        return ResponseEntity.created(new URI("/api/movie-wishlists/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /movie-wishlists : Updates an existing movieWishlist.
     *
     * @param movieWishlist the movieWishlist to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated movieWishlist,
     * or with status 400 (Bad Request) if the movieWishlist is not valid,
     * or with status 500 (Internal Server Error) if the movieWishlist couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/movie-wishlists")
    @Timed
    public ResponseEntity<MovieWishlist> updateMovieWishlist(@RequestBody MovieWishlist movieWishlist) throws URISyntaxException {
        log.debug("REST request to update MovieWishlist : {}", movieWishlist);
        if (movieWishlist.getId() == null) {
            return createMovieWishlist(movieWishlist);
        }
        MovieWishlist result = movieWishlistService.save(movieWishlist);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, movieWishlist.getId().toString()))
            .body(result);
    }

    /**
     * GET  /movie-wishlists : get all the movieWishlists.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of movieWishlists in body
     */
    @GetMapping("/movie-wishlists")
    @Timed
    public List<MovieWishlist> getAllMovieWishlists() {
        log.debug("REST request to get all MovieWishlists");
        return movieWishlistService.findAll();
    }

    /**
     * GET  /movie-wishlists/:id : get the "id" movieWishlist.
     *
     * @param id the id of the movieWishlist to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the movieWishlist, or with status 404 (Not Found)
     */
    @GetMapping("/movie-wishlists/{id}")
    @Timed
    public ResponseEntity<MovieWishlist> getMovieWishlist(@PathVariable Long id) {
        log.debug("REST request to get MovieWishlist : {}", id);
        MovieWishlist movieWishlist = movieWishlistService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(movieWishlist));
    }

    /**
     * DELETE  /movie-wishlists/:id : delete the "id" movieWishlist.
     *
     * @param id the id of the movieWishlist to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/movie-wishlists/{id}")
    @Timed
    public ResponseEntity<Void> deleteMovieWishlist(@PathVariable Long id) {
        log.debug("REST request to delete MovieWishlist : {}", id);
        movieWishlistService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
