package com.example.web.rest;

import com.example.SandboxApp;

import com.example.domain.Movie_watchlist;
import com.example.repository.Movie_watchlistRepository;
import com.example.service.Movie_watchlistService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the Movie_watchlistResource REST controller.
 *
 * @see Movie_watchlistResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SandboxApp.class)
public class Movie_watchlistResourceIntTest {

    private static final Integer DEFAULT_DBMOVIE_ID = 1;
    private static final Integer UPDATED_DBMOVIE_ID = 2;

    @Autowired
    private Movie_watchlistRepository movie_watchlistRepository;

    @Autowired
    private Movie_watchlistService movie_watchlistService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private EntityManager em;

    private MockMvc restMovie_watchlistMockMvc;

    private Movie_watchlist movie_watchlist;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Movie_watchlistResource movie_watchlistResource = new Movie_watchlistResource(movie_watchlistService);
        this.restMovie_watchlistMockMvc = MockMvcBuilders.standaloneSetup(movie_watchlistResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Movie_watchlist createEntity(EntityManager em) {
        Movie_watchlist movie_watchlist = new Movie_watchlist()
                .dbmovie_id(DEFAULT_DBMOVIE_ID);
        return movie_watchlist;
    }

    @Before
    public void initTest() {
        movie_watchlist = createEntity(em);
    }

    @Test
    @Transactional
    public void createMovie_watchlist() throws Exception {
        int databaseSizeBeforeCreate = movie_watchlistRepository.findAll().size();

        // Create the Movie_watchlist

        restMovie_watchlistMockMvc.perform(post("/api/movie-watchlists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(movie_watchlist)))
            .andExpect(status().isCreated());

        // Validate the Movie_watchlist in the database
        List<Movie_watchlist> movie_watchlistList = movie_watchlistRepository.findAll();
        assertThat(movie_watchlistList).hasSize(databaseSizeBeforeCreate + 1);
        Movie_watchlist testMovie_watchlist = movie_watchlistList.get(movie_watchlistList.size() - 1);
        assertThat(testMovie_watchlist.getDbmovie_id()).isEqualTo(DEFAULT_DBMOVIE_ID);
    }

    @Test
    @Transactional
    public void createMovie_watchlistWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = movie_watchlistRepository.findAll().size();

        // Create the Movie_watchlist with an existing ID
        Movie_watchlist existingMovie_watchlist = new Movie_watchlist();
        existingMovie_watchlist.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMovie_watchlistMockMvc.perform(post("/api/movie-watchlists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingMovie_watchlist)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Movie_watchlist> movie_watchlistList = movie_watchlistRepository.findAll();
        assertThat(movie_watchlistList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllMovie_watchlists() throws Exception {
        // Initialize the database
        movie_watchlistRepository.saveAndFlush(movie_watchlist);

        // Get all the movie_watchlistList
        restMovie_watchlistMockMvc.perform(get("/api/movie-watchlists?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(movie_watchlist.getId().intValue())))
            .andExpect(jsonPath("$.[*].dbmovie_id").value(hasItem(DEFAULT_DBMOVIE_ID)));
    }

    @Test
    @Transactional
    public void getMovie_watchlist() throws Exception {
        // Initialize the database
        movie_watchlistRepository.saveAndFlush(movie_watchlist);

        // Get the movie_watchlist
        restMovie_watchlistMockMvc.perform(get("/api/movie-watchlists/{id}", movie_watchlist.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(movie_watchlist.getId().intValue()))
            .andExpect(jsonPath("$.dbmovie_id").value(DEFAULT_DBMOVIE_ID));
    }

    @Test
    @Transactional
    public void getNonExistingMovie_watchlist() throws Exception {
        // Get the movie_watchlist
        restMovie_watchlistMockMvc.perform(get("/api/movie-watchlists/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMovie_watchlist() throws Exception {
        // Initialize the database
        movie_watchlistService.save(movie_watchlist);

        int databaseSizeBeforeUpdate = movie_watchlistRepository.findAll().size();

        // Update the movie_watchlist
        Movie_watchlist updatedMovie_watchlist = movie_watchlistRepository.findOne(movie_watchlist.getId());
        updatedMovie_watchlist
                .dbmovie_id(UPDATED_DBMOVIE_ID);

        restMovie_watchlistMockMvc.perform(put("/api/movie-watchlists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedMovie_watchlist)))
            .andExpect(status().isOk());

        // Validate the Movie_watchlist in the database
        List<Movie_watchlist> movie_watchlistList = movie_watchlistRepository.findAll();
        assertThat(movie_watchlistList).hasSize(databaseSizeBeforeUpdate);
        Movie_watchlist testMovie_watchlist = movie_watchlistList.get(movie_watchlistList.size() - 1);
        assertThat(testMovie_watchlist.getDbmovie_id()).isEqualTo(UPDATED_DBMOVIE_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingMovie_watchlist() throws Exception {
        int databaseSizeBeforeUpdate = movie_watchlistRepository.findAll().size();

        // Create the Movie_watchlist

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restMovie_watchlistMockMvc.perform(put("/api/movie-watchlists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(movie_watchlist)))
            .andExpect(status().isCreated());

        // Validate the Movie_watchlist in the database
        List<Movie_watchlist> movie_watchlistList = movie_watchlistRepository.findAll();
        assertThat(movie_watchlistList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteMovie_watchlist() throws Exception {
        // Initialize the database
        movie_watchlistService.save(movie_watchlist);

        int databaseSizeBeforeDelete = movie_watchlistRepository.findAll().size();

        // Get the movie_watchlist
        restMovie_watchlistMockMvc.perform(delete("/api/movie-watchlists/{id}", movie_watchlist.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Movie_watchlist> movie_watchlistList = movie_watchlistRepository.findAll();
        assertThat(movie_watchlistList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Movie_watchlist.class);
    }
}
