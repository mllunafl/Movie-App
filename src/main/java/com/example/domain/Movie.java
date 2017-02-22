package com.example.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
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

    @NotNull
    @Column(name = "dbmovie_id", nullable = false)
    private Integer dbmovie_id;

    @Column(name = "poster_path")
    private String poster_path;

    @Column(name = "poster_url")
    private String poster_url;

    @Enumerated(EnumType.STRING)
    @Column(name = "interest")
    private Interest interest;

    @ManyToOne
    private Movie_wishlist movie_wishlist;

    @ManyToOne
    private Movie_watchlist movie_watchlist;

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

    public Movie_wishlist getMovie_wishlist() {
        return movie_wishlist;
    }

    public Movie movie_wishlist(Movie_wishlist movie_wishlist) {
        this.movie_wishlist = movie_wishlist;
        return this;
    }

    public void setMovie_wishlist(Movie_wishlist movie_wishlist) {
        this.movie_wishlist = movie_wishlist;
    }

    public Movie_watchlist getMovie_watchlist() {
        return movie_watchlist;
    }

    public Movie movie_watchlist(Movie_watchlist movie_watchlist) {
        this.movie_watchlist = movie_watchlist;
        return this;
    }

    public void setMovie_watchlist(Movie_watchlist movie_watchlist) {
        this.movie_watchlist = movie_watchlist;
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
            ", dbmovie_id='" + dbmovie_id + "'" +
            ", poster_path='" + poster_path + "'" +
            ", poster_url='" + poster_url + "'" +
            ", interest='" + interest + "'" +
            '}';
    }
}
