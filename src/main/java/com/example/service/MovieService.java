package com.example.service;

import com.example.domain.Movie;
import com.example.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing Movie.
 */
@Service
@Transactional
public class MovieService {

    private final Logger log = LoggerFactory.getLogger(MovieService.class);

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    /**
     * Save a movie.
     *
     * @param movie the entity to save
     * @return the persisted entity
     */
    public Movie save(Movie movie) {
        log.debug("Request to save Movie : {}", movie);
        Movie result = movieRepository.save(movie);
        return result;
    }

    /**
     *  Get all the movies.
     *
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<Movie> findAll() {
        log.debug("Request to get all Movies");
        List<Movie> result = movieRepository.findAll();

        return result;
    }

    /**
     *  Get one movie by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public Movie findOne(Long id) {
        log.debug("Request to get Movie : {}", id);
        Movie movie = movieRepository.findOne(id);
        return movie;
    }

    /**
     *  Delete the  movie by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Movie : {}", id);
        movieRepository.delete(id);
    }


    @Transactional
    public Page<Movie> findAllPageable(Pageable pageable) {
        return personRepos.findAll(pageable);
    }

    @Transactional
    public Iterable<Person> save(Iterable<Person> persons) {
        return personRepository.save(persons);
    }
}
