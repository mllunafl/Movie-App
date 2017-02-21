package com.example.service;

import com.example.SandboxApp;
import com.example.domain.Movie;
import com.example.domain.enumeration.Interest;
import com.example.repository.MovieRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by LunaFlores on 2/21/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SandboxApp.class)
@Transactional
public class MovieServiceTest {

    @Autowired
    MovieService movieService;
    @Autowired
    MovieRepository movieRepository;


    @Test
    public void testAddMovie(){
        Movie movie = new Movie();
        movie.setDbmovieId(1);
        movie.setTitle("test movie");
        movie.setInterest(Interest.SEEN_IT);
        movieService.save(movie);
        List<Movie> movies= movieService.findAll();
//        assertThat(movies.contains(movie));

    }
}
