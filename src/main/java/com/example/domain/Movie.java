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
    private Integer dbmovieId;

    @Column(name = "poster_path")
    private String poster_path;

    @Column(name = "poster_url")
    private String poster_url;

    @Enumerated(EnumType.STRING)
    @Column(name = "interest")
    private Interest interest;

    @ManyToOne
    private MovieWishlist movieWishlist;

    @ManyToOne
    private MovieWatchlist movieWatchlist;

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

    public Integer getDbmovieId() {
        return dbmovieId;
    }

    public Movie dbmovieId(Integer dbmovieId) {
        this.dbmovieId = dbmovieId;
        return this;
    }

    public void setDbmovieId(Integer dbmovieId) {
        this.dbmovieId = dbmovieId;
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

    public MovieWishlist getMovieWishlist() {
        return movieWishlist;
    }

    public Movie movieWishlist(MovieWishlist movieWishlist) {
        this.movieWishlist = movieWishlist;
        return this;
    }

    public void setMovieWishlist(MovieWishlist movieWishlist) {
        this.movieWishlist = movieWishlist;
    }

    public MovieWatchlist getMovieWatchlist() {
        return movieWatchlist;
    }

    public Movie movieWatchlist(MovieWatchlist movieWatchlist) {
        this.movieWatchlist = movieWatchlist;
        return this;
    }

    public void setMovieWatchlist(MovieWatchlist movieWatchlist) {
        this.movieWatchlist = movieWatchlist;
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
            ", dbmovieId='" + dbmovieId + "'" +
            ", poster_path='" + poster_path + "'" +
            ", poster_url='" + poster_url + "'" +
            ", interest='" + interest + "'" +
            '}';
    }
}
