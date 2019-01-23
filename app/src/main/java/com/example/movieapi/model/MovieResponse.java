package com.example.movieapi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse {
    @SerializedName("page")
    @Expose
    private int page;
    @SerializedName("total_results")
    @Expose
    private String totalResults;
    @SerializedName("total_pages")
    @Expose
    private String totalPages;
    @SerializedName("results")
    @Expose
    private List<Result>results=null;

    public MovieResponse(){}

    public MovieResponse(Integer page,String totalResults,String totalPages,List<Result>results){
        super();
        this.page=page;
        this.totalResults=totalResults;
        this.totalPages=totalPages;
        this.results=results;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(String totalPages) {
        this.totalPages = totalPages;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
