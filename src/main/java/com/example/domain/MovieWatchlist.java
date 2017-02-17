package com.example.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A MovieWatchlist.
 */
@Entity
@Table(name = "movie_watchlist")
public class MovieWatchlist implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dbmovie_id")
    private Integer dbmovieId;

    @ManyToOne
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

    public MovieWatchlist dbmovieId(Integer dbmovieId) {
        this.dbmovieId = dbmovieId;
        return this;
    }

    public void setDbmovieId(Integer dbmovieId) {
        this.dbmovieId = dbmovieId;
    }

    public User getUser() {
        return user;
    }

    public MovieWatchlist user(User user) {
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
        MovieWatchlist movieWatchlist = (MovieWatchlist) o;
        if (movieWatchlist.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, movieWatchlist.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "MovieWatchlist{" +
            "id=" + id +
            ", dbmovieId='" + dbmovieId + "'" +
            '}';
    }
}
