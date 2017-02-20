package com.example.repository;

import com.example.domain.MovieWatchlist;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the MovieWatchlist entity.
 */
@SuppressWarnings("unused")
public interface MovieWatchlistRepository extends JpaRepository<MovieWatchlist,Long> {

    @Query("select movieWatchlist from MovieWatchlist movieWatchlist where movieWatchlist.user.login = ?#{principal.username}")
    List<MovieWatchlist> findByUserIsCurrentUser();

    @Query("select movieWatchlist from MovieWatchlist movieWatchlist where movieWatchlist.user.login = ?#{principal}")
    List<MovieWatchlist> findBySocialUserIsCurrentUser();


}
