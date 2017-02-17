package com.example.repository;

import com.example.domain.MovieWatchlist;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Movie_watchlist entity.
 */
@SuppressWarnings("unused")
public interface MovieWatchlistRepository extends JpaRepository<MovieWatchlist,Long> {

}
