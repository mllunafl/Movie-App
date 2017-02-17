package com.example.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.example.domain.MovieWatchlist;
import com.example.service.MovieWatchlistService;
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
 * REST controller for managing MovieWatchlist.
 */
@RestController
@RequestMapping("/api")
public class MovieWatchlistResource {

    private final Logger log = LoggerFactory.getLogger(MovieWatchlistResource.class);

    private static final String ENTITY_NAME = "movieWatchlist";

    private final MovieWatchlistService movieWatchlistService;

    public MovieWatchlistResource(MovieWatchlistService movieWatchlistService) {
        this.movieWatchlistService = movieWatchlistService;
    }

    /**
     * POST  /movie-watchlists : Create a new movieWatchlist.
     *
     * @param movieWatchlist the movieWatchlist to create
     * @return the ResponseEntity with status 201 (Created) and with body the new movieWatchlist, or with status 400 (Bad Request) if the movieWatchlist has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/movie-watchlists")
    @Timed
    public ResponseEntity<MovieWatchlist> createMovieWatchlist(@RequestBody MovieWatchlist movieWatchlist) throws URISyntaxException {
        log.debug("REST request to save MovieWatchlist : {}", movieWatchlist);
        if (movieWatchlist.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new movieWatchlist cannot already have an ID")).body(null);
        }
        MovieWatchlist result = movieWatchlistService.save(movieWatchlist);
        return ResponseEntity.created(new URI("/api/movie-watchlists/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /movie-watchlists : Updates an existing movieWatchlist.
     *
     * @param movieWatchlist the movieWatchlist to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated movieWatchlist,
     * or with status 400 (Bad Request) if the movieWatchlist is not valid,
     * or with status 500 (Internal Server Error) if the movieWatchlist couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/movie-watchlists")
    @Timed
    public ResponseEntity<MovieWatchlist> updateMovieWatchlist(@RequestBody MovieWatchlist movieWatchlist) throws URISyntaxException {
        log.debug("REST request to update MovieWatchlist : {}", movieWatchlist);
        if (movieWatchlist.getId() == null) {
            return createMovieWatchlist(movieWatchlist);
        }
        MovieWatchlist result = movieWatchlistService.save(movieWatchlist);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, movieWatchlist.getId().toString()))
            .body(result);
    }

    /**
     * GET  /movie-watchlists : get all the movieWatchlists.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of movieWatchlists in body
     */
    @GetMapping("/movie-watchlists")
    @Timed
    public List<MovieWatchlist> getAllMovieWatchlists() {
        log.debug("REST request to get all MovieWatchlists");
        return movieWatchlistService.findAll();
    }

    /**
     * GET  /movie-watchlists/:id : get the "id" movieWatchlist.
     *
     * @param id the id of the movieWatchlist to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the movieWatchlist, or with status 404 (Not Found)
     */
    @GetMapping("/movie-watchlists/{id}")
    @Timed
    public ResponseEntity<MovieWatchlist> getMovieWatchlist(@PathVariable Long id) {
        log.debug("REST request to get MovieWatchlist : {}", id);
        MovieWatchlist movieWatchlist = movieWatchlistService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(movieWatchlist));
    }

    /**
     * DELETE  /movie-watchlists/:id : delete the "id" movieWatchlist.
     *
     * @param id the id of the movieWatchlist to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/movie-watchlists/{id}")
    @Timed
    public ResponseEntity<Void> deleteMovieWatchlist(@PathVariable Long id) {
        log.debug("REST request to delete MovieWatchlist : {}", id);
        movieWatchlistService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
