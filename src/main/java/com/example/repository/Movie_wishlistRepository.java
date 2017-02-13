package com.example.repository;

import com.example.domain.Movie_wishlist;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Movie_wishlist entity.
 */
@SuppressWarnings("unused")
public interface Movie_wishlistRepository extends JpaRepository<Movie_wishlist,Long> {

    @Query("select distinct movie_wishlist from Movie_wishlist movie_wishlist left join fetch movie_wishlist.movies")
    List<Movie_wishlist> findAllWithEagerRelationships();

    @Query("select movie_wishlist from Movie_wishlist movie_wishlist left join fetch movie_wishlist.movies where movie_wishlist.id =:id")
    Movie_wishlist findOneWithEagerRelationships(@Param("id") Long id);

}
