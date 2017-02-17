package com.example.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A MovieWishlist.
 */
@Entity
@Table(name = "movie_wishlist")
public class MovieWishlist implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dbmovieId")
    private Integer dbmovieId;

    @OneToMany(mappedBy = "movieWishlist")
    @JsonIgnore
    private Set<Movie> movies = new HashSet<>();

    @OneToOne
    @JoinColumn(unique = true)
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDbmovieId() {
        return dbmovieId;
    }

    public MovieWishlist dbmovieId(Integer dbmovieId) {
        this.dbmovieId = dbmovieId;
        return this;
    }

    public void setDbmovieId(Integer dbmovieId) {
        this.dbmovieId = dbmovieId;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public MovieWishlist movies(Set<Movie> movies) {
        this.movies = movies;
        return this;
    }

    public MovieWishlist addMovie(Movie movie) {
        this.movies.add(movie);
        movie.setMovieWishlist(this);
        return this;
    }

    public MovieWishlist removeMovie(Movie movie) {
        this.movies.remove(movie);
        movie.setMovieWishlist(null);
        return this;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    public User getUser() {
        return user;
    }

    public MovieWishlist user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MovieWishlist movieWishlist = (MovieWishlist) o;
        if (movieWishlist.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, movieWishlist.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "MovieWishlist{" +
            "id=" + id +
            ", dbmovieId='" + dbmovieId + "'" +
            '}';
    }
}
