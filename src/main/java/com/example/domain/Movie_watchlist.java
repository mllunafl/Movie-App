package com.example.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Movie_watchlist.
 */
@Entity
@Table(name = "movie_watchlist")
public class Movie_watchlist implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dbmovie_id")
    private Integer dbmovie_id;

    @ManyToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDbmovie_id() {
        return dbmovie_id;
    }

    public Movie_watchlist dbmovie_id(Integer dbmovie_id) {
        this.dbmovie_id = dbmovie_id;
        return this;
    }

    public void setDbmovie_id(Integer dbmovie_id) {
        this.dbmovie_id = dbmovie_id;
    }

    public User getUser() {
        return user;
    }

    public Movie_watchlist user(User user) {
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
        Movie_watchlist movie_watchlist = (Movie_watchlist) o;
        if (movie_watchlist.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, movie_watchlist.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Movie_watchlist{" +
            "id=" + id +
            ", dbmovie_id='" + dbmovie_id + "'" +
            '}';
    }
}
