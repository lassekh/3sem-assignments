package org.app.api;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
public class MovieDTO {
    private Genre[] genres;
    private String imdb_id;
    private String original_title;
    private String overview;
    private LocalDate release_date;
    private double vote_average;

    public MovieDTO(Genre[] genres, String imdb_id, String original_title, String overview, LocalDate release_date, double vote_average) {
        this.genres = genres;
        this.imdb_id = imdb_id;
        this.original_title = original_title;
        this.overview = overview;
        this.release_date = release_date;
        this.vote_average = vote_average;
    }
}
