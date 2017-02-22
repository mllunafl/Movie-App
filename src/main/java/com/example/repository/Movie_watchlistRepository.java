package com.example.repository;

import com.example.domain.Movie_watchlist;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Movie_watchlist entity.
 */
@SuppressWarnings("unused")
public interface Movie_watchlistRepository extends JpaRepository<Movie_watchlist,Long> {

    @Query("select movie_watchlist from Movie_watchlist movie_watchlist where movie_watchlist.user.login = ?#{principal.username}")
    List<Movie_watchlist> findByUserIsCurrentUser();

}
