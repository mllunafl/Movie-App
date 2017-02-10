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

    @Enumerated(EnumType.STRING)
    @Column(name = "interest")
    private Interest interest;

    @NotNull
    @Column(name = "dbmovie_id", nullable = false)
    private Integer dbmovieId;

    @Column(name = "poster_path")
    private String posterPath;

    @Column(name = "poster_url")
    private String posterUrl;

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
        return dbmovieId;
    }

    public Movie dbmovie_id(Integer dbmovie_id) {
        this.dbmovieId = dbmovie_id;
        return this;
    }

    public void setDbmovie_id(Integer dbmovie_id) {
        this.dbmovieId = dbmovie_id;
    }

    public String getPoster_path() {
        return posterPath;
    }

    public Movie poster_path(String poster_path) {
        this.posterPath = poster_path;
        return this;
    }

    public void setPoster_path(String poster_path) {
        this.posterPath = poster_path;
    }

    public String getPoster_url() {
        return posterUrl;
    }

    public Movie poster_url(String poster_url) {
        this.posterUrl = poster_url;
        return this;
    }

    public void setPoster_url(String poster_url) {
        this.posterUrl = poster_url;
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
            ", dbmovieId='" + dbmovieId + "'" +
            ", posterPath='" + posterPath + "'" +
            ", posterUrl='" + posterUrl + "'" +
            '}';
    }
}
