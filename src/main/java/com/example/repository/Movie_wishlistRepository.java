package com.example.repository;

import com.example.domain.Movie_wishlist;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Movie_wishlist entity.
 */
@SuppressWarnings("unused")
public interface Movie_wishlistRepository extends JpaRepository<Movie_wishlist,Long> {

    @Query("select movie_wishlist from Movie_wishlist movie_wishlist where movie_wishlist.user.login = ?#{principal.username}")
    List<Movie_wishlist> findByUserIsCurrentUser();

}
