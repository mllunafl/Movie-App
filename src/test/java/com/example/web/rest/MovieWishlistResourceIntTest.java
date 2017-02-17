package com.example.web.rest;

import com.example.SandboxApp;

import com.example.domain.MovieWishlist;
import com.example.repository.MovieWishlistRepository;
import com.example.service.MovieWishlistService;

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
 * Test class for the MovieWishlistResource REST controller.
 *
 * @see MovieWishlistResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SandboxApp.class)
public class MovieWishlistResourceIntTest {

    private static final Integer DEFAULT_DBMOVIE_ID = 1;
    private static final Integer UPDATED_DBMOVIE_ID = 2;

    @Autowired
    private MovieWishlistRepository movieWishlistRepository;

    @Autowired
    private MovieWishlistService movieWishlistService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private EntityManager em;

    private MockMvc restMovieWishlistMockMvc;

    private MovieWishlist movieWishlist;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        MovieWishlistResource movieWishlistResource = new MovieWishlistResource(movieWishlistService);
        this.restMovieWishlistMockMvc = MockMvcBuilders.standaloneSetup(movieWishlistResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MovieWishlist createEntity(EntityManager em) {
        MovieWishlist movieWishlist = new MovieWishlist()
                .dbmovieId(DEFAULT_DBMOVIE_ID);
        return movieWishlist;
    }

    @Before
    public void initTest() {
        movieWishlist = createEntity(em);
    }

    @Test
    @Transactional
    public void createMovieWishlist() throws Exception {
        int databaseSizeBeforeCreate = movieWishlistRepository.findAll().size();

        // Create the MovieWishlist

        restMovieWishlistMockMvc.perform(post("/api/movie-wishlists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(movieWishlist)))
            .andExpect(status().isCreated());

        // Validate the MovieWishlist in the database
        List<MovieWishlist> movieWishlistList = movieWishlistRepository.findAll();
        assertThat(movieWishlistList).hasSize(databaseSizeBeforeCreate + 1);
        MovieWishlist testMovieWishlist = movieWishlistList.get(movieWishlistList.size() - 1);
        assertThat(testMovieWishlist.getDbmovieId()).isEqualTo(DEFAULT_DBMOVIE_ID);
    }

    @Test
    @Transactional
    public void createMovieWishlistWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = movieWishlistRepository.findAll().size();

        // Create the MovieWishlist with an existing ID
        MovieWishlist existingMovieWishlist = new MovieWishlist();
        existingMovieWishlist.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMovieWishlistMockMvc.perform(post("/api/movie-wishlists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingMovieWishlist)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<MovieWishlist> movieWishlistList = movieWishlistRepository.findAll();
        assertThat(movieWishlistList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllMovieWishlists() throws Exception {
        // Initialize the database
        movieWishlistRepository.saveAndFlush(movieWishlist);

        // Get all the movieWishlistList
        restMovieWishlistMockMvc.perform(get("/api/movie-wishlists?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(movieWishlist.getId().intValue())))
            .andExpect(jsonPath("$.[*].dbmovie_id").value(hasItem(DEFAULT_DBMOVIE_ID)));
    }

    @Test
    @Transactional
    public void getMovieWishlist() throws Exception {
        // Initialize the database
        movieWishlistRepository.saveAndFlush(movieWishlist);

        // Get the movieWishlist
        restMovieWishlistMockMvc.perform(get("/api/movie-wishlists/{id}", movieWishlist.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(movieWishlist.getId().intValue()))
            .andExpect(jsonPath("$.dbmovie_id").value(DEFAULT_DBMOVIE_ID));
    }

    @Test
    @Transactional
    public void getNonExistingMovieWishlist() throws Exception {
        // Get the movieWishlist
        restMovieWishlistMockMvc.perform(get("/api/movie-wishlists/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMovieWishlist() throws Exception {
        // Initialize the database
        movieWishlistService.save(movieWishlist);

        int databaseSizeBeforeUpdate = movieWishlistRepository.findAll().size();

        // Update the movieWishlist
        MovieWishlist updatedMovieWishlist = movieWishlistRepository.findOne(movieWishlist.getId());
        updatedMovieWishlist
                .dbmovieId(UPDATED_DBMOVIE_ID);

        restMovieWishlistMockMvc.perform(put("/api/movie-wishlists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedMovieWishlist)))
            .andExpect(status().isOk());

        // Validate the MovieWishlist in the database
        List<MovieWishlist> movieWishlistList = movieWishlistRepository.findAll();
        assertThat(movieWishlistList).hasSize(databaseSizeBeforeUpdate);
        MovieWishlist testMovieWishlist = movieWishlistList.get(movieWishlistList.size() - 1);
        assertThat(testMovieWishlist.getDbmovieId()).isEqualTo(UPDATED_DBMOVIE_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingMovieWishlist() throws Exception {
        int databaseSizeBeforeUpdate = movieWishlistRepository.findAll().size();

        // Create the MovieWishlist

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restMovieWishlistMockMvc.perform(put("/api/movie-wishlists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(movieWishlist)))
            .andExpect(status().isCreated());

        // Validate the MovieWishlist in the database
        List<MovieWishlist> movieWishlistList = movieWishlistRepository.findAll();
        assertThat(movieWishlistList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteMovieWishlist() throws Exception {
        // Initialize the database
        movieWishlistService.save(movieWishlist);

        int databaseSizeBeforeDelete = movieWishlistRepository.findAll().size();

        // Get the movieWishlist
        restMovieWishlistMockMvc.perform(delete("/api/movie-wishlists/{id}", movieWishlist.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<MovieWishlist> movieWishlistList = movieWishlistRepository.findAll();
        assertThat(movieWishlistList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MovieWishlist.class);
    }
}
