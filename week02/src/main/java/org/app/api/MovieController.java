package org.app.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.app.api.APIExercise.gson;

public class MovieController {

    public static List<MovieDTO> getByRating(List<MovieDTO> movies, double rating){
        return movies.stream().filter(movieDTO -> movieDTO.getVote_average() > rating)
                .collect(Collectors.toList());
    }

    public static List<MovieDTO> getSortedByReleaseDate(List<MovieDTO> movies){
        return movies.stream()
                .sorted(Comparator.comparing(MovieDTO::getRelease_date))
                .collect(Collectors.toList());
    }
    public static MovieDTO search(String query){
        String url = "https://api.themoviedb.org/3/search/movie?query={q}"
                .replace("{q}",query.toLowerCase())
                .replace(" ","%");
        String response = APIExercise.getResponseBodyAccessToken(url);
        JsonArray jsonArray = gson.fromJson(response, JsonObject.class).getAsJsonArray("results");
        return gson.fromJson(jsonArray.get(0),MovieDTO.class);
    }
}
