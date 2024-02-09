package org.app.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import jdk.jfr.Frequency;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class APIExercise {
    public static final String API_KEY = System.getenv("API_KEY");
    static Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .setPrettyPrinting()
            .create();
    public static void main(String[] args) {
        //Should return the movie "Killers of the Flower Moon" by its IMDB id
        getMovieById("tt5537002", API_KEY);

        //Returns a list of all popular movies
        List<MovieDTO> allMovies = getPopularMovies(API_KEY);

        //Filtering allMovies by rating and prints out filtered movies title
        List<MovieDTO> filteredMovies = MovieController.getByRating(allMovies,7.5);
        for(MovieDTO m : filteredMovies) {
            System.out.println("Movie title: " + m.getOriginal_title());
        }

        //Sorting allMovies by release date and printing out sorted result with movie title and date
        List<MovieDTO> sortedMovies = MovieController.getSortedByReleaseDate(allMovies);
        for(MovieDTO m : sortedMovies) {
            System.out.println("Movie title: " + m.getOriginal_title() + "\n" +
                    "Release date: " + m.getRelease_date()
            );
        }

        //Searching for movie taking a String query and returns the first movie found in the API response
        MovieDTO foundMovie = MovieController.search("The Shawshank Redemption");
        System.out.println(foundMovie);
    }

    public static String getResponseBody(String url){
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        Request request = new Request.Builder()
                .url(url)
                .method("GET",null)
                .build();
        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static String getResponseBodyAccessToken(String url){
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request req = new Request.Builder()
                .url(url)
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiMTExMzYzYzBkMzc5MWU1MGNmYmYzMGYyNWYyMTM2OCIsInN1YiI6IjY1YzBjMjk2MWRiYzg4MDE3YzIwYjJjZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.axEkAaIgVCa_nJZgmpXs5HnnEeapUxPuT8C-ESdmYHM")
                .build();
        try{
            Response res = client.newCall(req).execute();
            return res.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void getMovieById(String movieId, String key){
        String url = "https://api.themoviedb.org/3/movie/{id}?api_key={key}"
                .replace("{id}",movieId)
                .replace("{key}",key);

        String response = getResponseBody(url);
        //System.out.println("JSON: " + response);

        MovieDTO movie = gson.fromJson(response, MovieDTO.class);
        System.out.println("Movie title: " + movie.getOriginal_title() + "\n" +
                "Movie overview: " + movie.getOverview()
        );

    }
    public static List<MovieDTO> getPopularMovies(String key){
        String url = "https://api.themoviedb.org/3/movie/popular?api_key={key}"
                .replace("{key}",key);

        String response = getResponseBody(url);
        //System.out.println("JSON: " + response);
        //Placing data in JsonObjedt format to get out the Array of movies.
        JsonObject jsonObject = gson.fromJson(response, JsonObject.class);
        JsonArray jsonArray = jsonObject.getAsJsonArray("results");
        //Getting json back in to a String.
        String res = gson.toJson(jsonArray);
        //System.out.println("New JSON:" + res);

        MovieDTO[] movies = gson.fromJson(res, MovieDTO[].class);
        return Arrays.stream(movies).toList();
    }
}
