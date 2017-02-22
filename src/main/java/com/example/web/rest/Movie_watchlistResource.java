package com.example.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.example.domain.Movie_watchlist;
import com.example.service.Movie_watchlistService;
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
 * REST controller for managing Movie_watchlist.
 */
@RestController
@RequestMapping("/api")
public class Movie_watchlistResource {

    private final Logger log = LoggerFactory.getLogger(Movie_watchlistResource.class);

    private static final String ENTITY_NAME = "movie_watchlist";
        
    private final Movie_watchlistService movie_watchlistService;

    public Movie_watchlistResource(Movie_watchlistService movie_watchlistService) {
        this.movie_watchlistService = movie_watchlistService;
    }

    /**
     * POST  /movie-watchlists : Create a new movie_watchlist.
     *
     * @param movie_watchlist the movie_watchlist to create
     * @return the ResponseEntity with status 201 (Created) and with body the new movie_watchlist, or with status 400 (Bad Request) if the movie_watchlist has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/movie-watchlists")
    @Timed
    public ResponseEntity<Movie_watchlist> createMovie_watchlist(@RequestBody Movie_watchlist movie_watchlist) throws URISyntaxException {
        log.debug("REST request to save Movie_watchlist : {}", movie_watchlist);
        if (movie_watchlist.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new movie_watchlist cannot already have an ID")).body(null);
        }
        Movie_watchlist result = movie_watchlistService.save(movie_watchlist);
        return ResponseEntity.created(new URI("/api/movie-watchlists/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /movie-watchlists : Updates an existing movie_watchlist.
     *
     * @param movie_watchlist the movie_watchlist to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated movie_watchlist,
     * or with status 400 (Bad Request) if the movie_watchlist is not valid,
     * or with status 500 (Internal Server Error) if the movie_watchlist couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/movie-watchlists")
    @Timed
    public ResponseEntity<Movie_watchlist> updateMovie_watchlist(@RequestBody Movie_watchlist movie_watchlist) throws URISyntaxException {
        log.debug("REST request to update Movie_watchlist : {}", movie_watchlist);
        if (movie_watchlist.getId() == null) {
            return createMovie_watchlist(movie_watchlist);
        }
        Movie_watchlist result = movie_watchlistService.save(movie_watchlist);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, movie_watchlist.getId().toString()))
            .body(result);
    }

    /**
     * GET  /movie-watchlists : get all the movie_watchlists.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of movie_watchlists in body
     */
    @GetMapping("/movie-watchlists")
    @Timed
    public List<Movie_watchlist> getAllMovie_watchlists() {
        log.debug("REST request to get all Movie_watchlists");
        return movie_watchlistService.findAll();
    }

    /**
     * GET  /movie-watchlists/:id : get the "id" movie_watchlist.
     *
     * @param id the id of the movie_watchlist to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the movie_watchlist, or with status 404 (Not Found)
     */
    @GetMapping("/movie-watchlists/{id}")
    @Timed
    public ResponseEntity<Movie_watchlist> getMovie_watchlist(@PathVariable Long id) {
        log.debug("REST request to get Movie_watchlist : {}", id);
        Movie_watchlist movie_watchlist = movie_watchlistService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(movie_watchlist));
    }

    /**
     * DELETE  /movie-watchlists/:id : delete the "id" movie_watchlist.
     *
     * @param id the id of the movie_watchlist to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/movie-watchlists/{id}")
    @Timed
    public ResponseEntity<Void> deleteMovie_watchlist(@PathVariable Long id) {
        log.debug("REST request to delete Movie_watchlist : {}", id);
        movie_watchlistService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
