package com.example.repository;

import com.example.domain.MovieWishlist;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the MovieWishlist entity.
 */
@SuppressWarnings("unused")
public interface MovieWishlistRepository extends JpaRepository<MovieWishlist,Long> {

    @Query("select movieWishlist from MovieWishlist movieWishlist where movieWishlist.user.login = ?#{principal.username}")
    List<MovieWishlist> findByUserIsCurrentUser();

    @Query("select movieWishlist from MovieWishlist movieWishlist where movieWishlist.user.login = ?#{principal}")
    List<MovieWishlist> findBySocialUserIsCurrentUser();

}
