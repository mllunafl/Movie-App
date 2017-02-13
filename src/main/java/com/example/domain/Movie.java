package com.example.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.example.domain.enumeration.Interest;

/**
 * A Movie.
 */
@Entity
@Table(name = "movie")
public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "interest")
    private Interest interest;

    @NotNull
    @Column(name = "dbmovie_id", nullable = false)
    private Integer dbmovie_id;

    @Column(name = "poster_path")
    private String poster_path;

    @Column(name = "poster_url")
    private String poster_url;

    @ManyToMany(mappedBy = "movies")
    @JsonIgnore
    private Set<Movie_wishlist> movie_wishlists = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Movie title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Interest getInterest() {
        return interest;
    }

    public Movie interest(Interest interest) {
        this.interest = interest;
        return this;
    }

    public void setInterest(Interest interest) {
        this.interest = interest;
    }

    public Integer getDbmovie_id() {
        return dbmovie_id;
    }

    public Movie dbmovie_id(Integer dbmovie_id) {
        this.dbmovie_id = dbmovie_id;
        return this;
    }

    public void setDbmovie_id(Integer dbmovie_id) {
        this.dbmovie_id = dbmovie_id;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public Movie poster_path(String poster_path) {
        this.poster_path = poster_path;
        return this;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getPoster_url() {
        return poster_url;
    }

    public Movie poster_url(String poster_url) {
        this.poster_url = poster_url;
        return this;
    }

    public void setPoster_url(String poster_url) {
        this.poster_url = poster_url;
    }

    public Set<Movie_wishlist> getMovie_wishlists() {
        return movie_wishlists;
    }

    public Movie movie_wishlists(Set<Movie_wishlist> movie_wishlists) {
        this.movie_wishlists = movie_wishlists;
        return this;
    }

    public Movie addMovie_wishlist(Movie_wishlist movie_wishlist) {
        this.movie_wishlists.add(movie_wishlist);
        movie_wishlist.getMovies().add(this);
        return this;
    }

    public Movie removeMovie_wishlist(Movie_wishlist movie_wishlist) {
        this.movie_wishlists.remove(movie_wishlist);
        movie_wishlist.getMovies().remove(this);
        return this;
    }

    public void setMovie_wishlists(Set<Movie_wishlist> movie_wishlists) {
        this.movie_wishlists = movie_wishlists;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Movie movie = (Movie) o;
        if (movie.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, movie.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Movie{" +
            "id=" + id +
            ", title='" + title + "'" +
            ", interest='" + interest + "'" +
            ", dbmovie_id='" + dbmovie_id + "'" +
            ", poster_path='" + poster_path + "'" +
            ", poster_url='" + poster_url + "'" +
            '}';
    }
}
