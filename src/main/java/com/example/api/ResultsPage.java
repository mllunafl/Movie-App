package com.example.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Iterator;
import java.util.List;

/**
 * Created by LunaFlores on 2/8/17.
 */
public class ResultsPage<T> implements Iterable<DBMovie> {

    @JsonProperty("results")
    private List<DBMovie> results;

    @JsonProperty("page")
    private int page;

    @JsonProperty("total_pages")
    private int totalPages;

    @JsonProperty("total_results")
    private int totalResults;


    @Override
    public Iterator<DBMovie> iterator() {
        return results.iterator();
    }


    public List<DBMovie> getResults() {
        return results;
    }


    public int getPage() {
        return page;
    }


    public int getTotalPages() {
        return totalPages;
    }


    public int getTotalResults() {
        return totalResults;
    }


    public void setPage(int page) {
        this.page = page;
    }


    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }


    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }


}
