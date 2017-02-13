package com.example.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Movie_wishlist.
 */
@Entity
@Table(name = "movie_wishlist")
public class Movie_wishlist implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(name = "movie_wishlist_movie",
               joinColumns = @JoinColumn(name="movie_wishlists_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="movies_id", referencedColumnName="id"))
    private Set<Movie> movies = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Movie_wishlist name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public Movie_wishlist movies(Set<Movie> movies) {
        this.movies = movies;
        return this;
    }

    public Movie_wishlist addMovie(Movie movie) {
        this.movies.add(movie);
        movie.getMovie_wishlists().add(this);
        return this;
    }

    public Movie_wishlist removeMovie(Movie movie) {
        this.movies.remove(movie);
        movie.getMovie_wishlists().remove(this);
        return this;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Movie_wishlist movie_wishlist = (Movie_wishlist) o;
        if (movie_wishlist.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, movie_wishlist.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Movie_wishlist{" +
            "id=" + id +
            ", name='" + name + "'" +
            '}';
    }
}
