package com.example.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LunaFlores on 2/8/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class DBMovie  {

    @JsonProperty("id")
    private int id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("original_title")
    private String originalTitle;

    @JsonProperty("popularity")
    private float popularity;

    @JsonProperty("backdrop_path")
    private String backdropPath;
    @JsonProperty("poster_path")
    private String posterPath;

    @JsonProperty("release_date")
    private String releaseDate;


    @JsonProperty("genres")
    private List<Genre> genres;
    @JsonProperty("homepage")
    private String homepage;

    @JsonProperty("overview")
    private String overview;

    // todo still there??
    @JsonProperty("imdb_id")
    private String imdbID;

    @JsonProperty("original_language")
    private String originalLanguage;




    @JsonProperty("revenue")
    private long revenue;
    @JsonProperty("runtime")
    private int runtime;

    @JsonProperty("spoken_languages")
    private List<Language> spokenLanguages;

    @JsonProperty("tagline")
    private String tagline;

    @JsonProperty("rating")
    private float userRating;

    @JsonProperty("vote_average")
    private float voteAverage;
    @JsonProperty("vote_count")
    private int voteCount;

    @JsonProperty("status")
    private String status;

    @Override
    public String toString() {
        return title + " - " + releaseDate + " - " + id;
    }


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public float getPopularity() {
        return popularity;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getOverview() {
        return overview;
    }

    public String getImdbID() {
        return imdbID;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public long getRevenue() {
        return revenue;
    }

    public int getRuntime() {
        return runtime;
    }

    public List<Language> getSpokenLanguages() {
        return spokenLanguages;
    }

    public String getTagline() {
        return tagline;
    }

    public float getUserRating() {
        return userRating;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public String getStatus() {
        return status;
    }


    //    public static void main(String[] args) {
//        RestTemplate restTemplate = new RestTemplate();
//
//
//       HttpEntity<String> entity = new HttpEntity<>();
//        //System.out.println(restTemplate.getForObject("https://api.themoviedb.org/3/movie/297761?api_key=f3d19e01363ab38b737c12cd2e9b704c&language=en-US",String.class));
//
//        //RestTemplate restTemplate = new RestTemplate();
//        DBMovie dbMovie = restTemplate.getForObject("https://api.themoviedb.org/3/movie/297761?api_key=f3d19e01363ab38b737c12cd2e9b704c&language=en-US", DBMovie.class);
//        System.out.println(dbMovie);
//    }
}
