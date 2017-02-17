package com.example.repository;

import com.example.domain.MovieWishlist;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Movie_wishlist entity.
 */
@SuppressWarnings("unused")
public interface MovieWishlistRepository extends JpaRepository<MovieWishlist,Long> {

}
