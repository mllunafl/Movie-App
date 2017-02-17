package com.example.web.rest;

import com.example.SandboxApp;

import com.example.domain.MovieWatchlist;
import com.example.repository.MovieWatchlistRepository;
import com.example.service.MovieWatchlistService;

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
 * Test class for the MovieWatchlistResource REST controller.
 *
 * @see MovieWatchlistResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SandboxApp.class)
public class MovieWatchlistResourceIntTest {

    private static final Integer DEFAULT_DBMOVIE_ID = 1;
    private static final Integer UPDATED_DBMOVIE_ID = 2;

    @Autowired
    private MovieWatchlistRepository movieWatchlistRepository;

    @Autowired
    private MovieWatchlistService movieWatchlistService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private EntityManager em;

    private MockMvc restMovieWatchlistMockMvc;

    private MovieWatchlist movieWatchlist;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        MovieWatchlistResource movieWatchlistResource = new MovieWatchlistResource(movieWatchlistService);
        this.restMovieWatchlistMockMvc = MockMvcBuilders.standaloneSetup(movieWatchlistResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MovieWatchlist createEntity(EntityManager em) {
        MovieWatchlist movieWatchlist = new MovieWatchlist()
                .dbmovieId(DEFAULT_DBMOVIE_ID);
        return movieWatchlist;
    }

    @Before
    public void initTest() {
        movieWatchlist = createEntity(em);
    }

    @Test
    @Transactional
    public void createMovieWatchlist() throws Exception {
        int databaseSizeBeforeCreate = movieWatchlistRepository.findAll().size();

        // Create the MovieWatchlist

        restMovieWatchlistMockMvc.perform(post("/api/movie-watchlists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(movieWatchlist)))
            .andExpect(status().isCreated());

        // Validate the MovieWatchlist in the database
        List<MovieWatchlist> movieWatchlistList = movieWatchlistRepository.findAll();
        assertThat(movieWatchlistList).hasSize(databaseSizeBeforeCreate + 1);
        MovieWatchlist testMovieWatchlist = movieWatchlistList.get(movieWatchlistList.size() - 1);
        assertThat(testMovieWatchlist.getDbmovieId()).isEqualTo(DEFAULT_DBMOVIE_ID);
    }

    @Test
    @Transactional
    public void createMovieWatchlistWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = movieWatchlistRepository.findAll().size();

        // Create the MovieWatchlist with an existing ID
        MovieWatchlist existingMovieWatchlist = new MovieWatchlist();
        existingMovieWatchlist.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMovieWatchlistMockMvc.perform(post("/api/movie-watchlists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingMovieWatchlist)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<MovieWatchlist> movieWatchlistList = movieWatchlistRepository.findAll();
        assertThat(movieWatchlistList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllMovieWatchlists() throws Exception {
        // Initialize the database
        movieWatchlistRepository.saveAndFlush(movieWatchlist);

        // Get all the movieWatchlistList
        restMovieWatchlistMockMvc.perform(get("/api/movie-watchlists?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(movieWatchlist.getId().intValue())))
            .andExpect(jsonPath("$.[*].dbmovie_id").value(hasItem(DEFAULT_DBMOVIE_ID)));
    }

    @Test
    @Transactional
    public void getMovieWatchlist() throws Exception {
        // Initialize the database
        movieWatchlistRepository.saveAndFlush(movieWatchlist);

        // Get the movieWatchlist
        restMovieWatchlistMockMvc.perform(get("/api/movie-watchlists/{id}", movieWatchlist.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(movieWatchlist.getId().intValue()))
            .andExpect(jsonPath("$.dbmovie_id").value(DEFAULT_DBMOVIE_ID));
    }

    @Test
    @Transactional
    public void getNonExistingMovieWatchlist() throws Exception {
        // Get the movieWatchlist
        restMovieWatchlistMockMvc.perform(get("/api/movie-watchlists/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMovieWatchlist() throws Exception {
        // Initialize the database
        movieWatchlistService.save(movieWatchlist);

        int databaseSizeBeforeUpdate = movieWatchlistRepository.findAll().size();

        // Update the movieWatchlist
        MovieWatchlist updatedMovieWatchlist = movieWatchlistRepository.findOne(movieWatchlist.getId());
        updatedMovieWatchlist
                .dbmovieId(UPDATED_DBMOVIE_ID);

        restMovieWatchlistMockMvc.perform(put("/api/movie-watchlists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedMovieWatchlist)))
            .andExpect(status().isOk());

        // Validate the MovieWatchlist in the database
        List<MovieWatchlist> movieWatchlistList = movieWatchlistRepository.findAll();
        assertThat(movieWatchlistList).hasSize(databaseSizeBeforeUpdate);
        MovieWatchlist testMovieWatchlist = movieWatchlistList.get(movieWatchlistList.size() - 1);
        assertThat(testMovieWatchlist.getDbmovieId()).isEqualTo(UPDATED_DBMOVIE_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingMovieWatchlist() throws Exception {
        int databaseSizeBeforeUpdate = movieWatchlistRepository.findAll().size();

        // Create the MovieWatchlist

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restMovieWatchlistMockMvc.perform(put("/api/movie-watchlists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(movieWatchlist)))
            .andExpect(status().isCreated());

        // Validate the MovieWatchlist in the database
        List<MovieWatchlist> movieWatchlistList = movieWatchlistRepository.findAll();
        assertThat(movieWatchlistList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteMovieWatchlist() throws Exception {
        // Initialize the database
        movieWatchlistService.save(movieWatchlist);

        int databaseSizeBeforeDelete = movieWatchlistRepository.findAll().size();

        // Get the movieWatchlist
        restMovieWatchlistMockMvc.perform(delete("/api/movie-watchlists/{id}", movieWatchlist.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<MovieWatchlist> movieWatchlistList = movieWatchlistRepository.findAll();
        assertThat(movieWatchlistList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MovieWatchlist.class);
    }
}
