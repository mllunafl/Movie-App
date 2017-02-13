package com.example.web.rest;

import com.example.SandboxApp;

import com.example.domain.Movie_wishlist;
import com.example.repository.Movie_wishlistRepository;
import com.example.service.Movie_wishlistService;

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
 * Test class for the Movie_wishlistResource REST controller.
 *
 * @see Movie_wishlistResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SandboxApp.class)
public class Movie_wishlistResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private Movie_wishlistRepository movie_wishlistRepository;

    @Autowired
    private Movie_wishlistService movie_wishlistService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private EntityManager em;

    private MockMvc restMovie_wishlistMockMvc;

    private Movie_wishlist movie_wishlist;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Movie_wishlistResource movie_wishlistResource = new Movie_wishlistResource(movie_wishlistService);
        this.restMovie_wishlistMockMvc = MockMvcBuilders.standaloneSetup(movie_wishlistResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Movie_wishlist createEntity(EntityManager em) {
        Movie_wishlist movie_wishlist = new Movie_wishlist()
                .name(DEFAULT_NAME);
        return movie_wishlist;
    }

    @Before
    public void initTest() {
        movie_wishlist = createEntity(em);
    }

    @Test
    @Transactional
    public void createMovie_wishlist() throws Exception {
        int databaseSizeBeforeCreate = movie_wishlistRepository.findAll().size();

        // Create the Movie_wishlist

        restMovie_wishlistMockMvc.perform(post("/api/movie-wishlists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(movie_wishlist)))
            .andExpect(status().isCreated());

        // Validate the Movie_wishlist in the database
        List<Movie_wishlist> movie_wishlistList = movie_wishlistRepository.findAll();
        assertThat(movie_wishlistList).hasSize(databaseSizeBeforeCreate + 1);
        Movie_wishlist testMovie_wishlist = movie_wishlistList.get(movie_wishlistList.size() - 1);
        assertThat(testMovie_wishlist.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createMovie_wishlistWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = movie_wishlistRepository.findAll().size();

        // Create the Movie_wishlist with an existing ID
        Movie_wishlist existingMovie_wishlist = new Movie_wishlist();
        existingMovie_wishlist.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMovie_wishlistMockMvc.perform(post("/api/movie-wishlists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingMovie_wishlist)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Movie_wishlist> movie_wishlistList = movie_wishlistRepository.findAll();
        assertThat(movie_wishlistList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = movie_wishlistRepository.findAll().size();
        // set the field null
        movie_wishlist.setName(null);

        // Create the Movie_wishlist, which fails.

        restMovie_wishlistMockMvc.perform(post("/api/movie-wishlists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(movie_wishlist)))
            .andExpect(status().isBadRequest());

        List<Movie_wishlist> movie_wishlistList = movie_wishlistRepository.findAll();
        assertThat(movie_wishlistList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMovie_wishlists() throws Exception {
        // Initialize the database
        movie_wishlistRepository.saveAndFlush(movie_wishlist);

        // Get all the movie_wishlistList
        restMovie_wishlistMockMvc.perform(get("/api/movie-wishlists?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(movie_wishlist.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }

    @Test
    @Transactional
    public void getMovie_wishlist() throws Exception {
        // Initialize the database
        movie_wishlistRepository.saveAndFlush(movie_wishlist);

        // Get the movie_wishlist
        restMovie_wishlistMockMvc.perform(get("/api/movie-wishlists/{id}", movie_wishlist.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(movie_wishlist.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMovie_wishlist() throws Exception {
        // Get the movie_wishlist
        restMovie_wishlistMockMvc.perform(get("/api/movie-wishlists/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMovie_wishlist() throws Exception {
        // Initialize the database
        movie_wishlistService.save(movie_wishlist);

        int databaseSizeBeforeUpdate = movie_wishlistRepository.findAll().size();

        // Update the movie_wishlist
        Movie_wishlist updatedMovie_wishlist = movie_wishlistRepository.findOne(movie_wishlist.getId());
        updatedMovie_wishlist
                .name(UPDATED_NAME);

        restMovie_wishlistMockMvc.perform(put("/api/movie-wishlists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedMovie_wishlist)))
            .andExpect(status().isOk());

        // Validate the Movie_wishlist in the database
        List<Movie_wishlist> movie_wishlistList = movie_wishlistRepository.findAll();
        assertThat(movie_wishlistList).hasSize(databaseSizeBeforeUpdate);
        Movie_wishlist testMovie_wishlist = movie_wishlistList.get(movie_wishlistList.size() - 1);
        assertThat(testMovie_wishlist.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingMovie_wishlist() throws Exception {
        int databaseSizeBeforeUpdate = movie_wishlistRepository.findAll().size();

        // Create the Movie_wishlist

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restMovie_wishlistMockMvc.perform(put("/api/movie-wishlists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(movie_wishlist)))
            .andExpect(status().isCreated());

        // Validate the Movie_wishlist in the database
        List<Movie_wishlist> movie_wishlistList = movie_wishlistRepository.findAll();
        assertThat(movie_wishlistList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteMovie_wishlist() throws Exception {
        // Initialize the database
        movie_wishlistService.save(movie_wishlist);

        int databaseSizeBeforeDelete = movie_wishlistRepository.findAll().size();

        // Get the movie_wishlist
        restMovie_wishlistMockMvc.perform(delete("/api/movie-wishlists/{id}", movie_wishlist.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Movie_wishlist> movie_wishlistList = movie_wishlistRepository.findAll();
        assertThat(movie_wishlistList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Movie_wishlist.class);
    }
}
