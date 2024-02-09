package org.app.threads.task6;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class DataFromMultipleAPIs {
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future<IPAddressDTO> fut1 = executorService.submit(() -> gson.fromJson(getResponseBody("https://api.myip.com"), IPAddressDTO.class));
        Future<DigitalOceanStatusDTO> fut2 = executorService.submit(() -> gson.fromJson(getResponseBody("https://status.digitalocean.com/api/v2/status.json"), DigitalOceanStatusDTO.class));
        Future<YesOrNoDTO> fut3 = executorService.submit(() -> gson.fromJson(getResponseBody("https://yesno.wtf/api"), YesOrNoDTO.class));
        Future<ChuckNorrisDTO> fut4 = executorService.submit(() -> gson.fromJson(getResponseBody("https://api.chucknorris.io/jokes/random"), ChuckNorrisDTO.class));
        Future<KanyeWestDTO> fut5 = executorService.submit(() -> gson.fromJson(getResponseBody("https://api.kanye.rest"), KanyeWestDTO.class));
        Future<DonaldTrumpDTO> fut6 = executorService.submit(() -> gson.fromJson(getResponseBody("https://api.whatdoestrumpthink.com/api/v1/quotes/random"), DonaldTrumpDTO.class));

        try {
            MegaDTO megaDTO = new MegaDTO(fut1.get(), fut2.get(), fut3.get(), fut4.get(), fut5.get(), fut6.get());
            System.out.println("My IP: " + megaDTO.getIpAddressDTO().getIp() + "\n" +
                    "Digital Ocean status: " + megaDTO.getDigitalOceanStatusDTO().getStatus().getDescription() + "\n" +
                    "Yes or no?: " + megaDTO.getYesOrNoDTO().getAnswer() + "\n" +
                    "Chuck Norris joke: " + megaDTO.getChuckNorrisDTO().getValue() + "\n" +
                    "Kanye West quote: " + megaDTO.getKanyeWestDTO().getQuote() + "\n" +
                    "Donald Trump thought: " + megaDTO.getDonaldTrumpDTO().getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        executorService.shutdown();
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
    public static IPAddressDTO getIP(String url){
        String response = getResponseBody(url);
        IPAddressDTO ip = gson.fromJson(response, IPAddressDTO.class);
        return ip;
    }
    public static DigitalOceanStatusDTO getDigitalOceanStatus(String url){
        String response = getResponseBody(url);
        DigitalOceanStatusDTO status = gson.fromJson(response, DigitalOceanStatusDTO.class);
        return status;
    }
    public static YesOrNoDTO getYesOrNo(String url){
        String response = getResponseBody(url);
        YesOrNoDTO dto = gson.fromJson(response, YesOrNoDTO.class);
        return dto;
    }
    /*public static DadJokeDTO getDadJoke(String url){
        String response = getResponseBody(url);
        DadJokeDTO dto = gson.fromJson(response, DadJokeDTO.class);
        return dto;
    }*/
    public static ChuckNorrisDTO getChuckNorrisJoke(String url){
        String response = getResponseBody(url);
        ChuckNorrisDTO dto = gson.fromJson(response, ChuckNorrisDTO.class);
        return dto;
    }
    public static KanyeWestDTO getKanyeQuote(String url){
        String response = getResponseBody(url);
        KanyeWestDTO dto = gson.fromJson(response, KanyeWestDTO.class);
        return dto;
    }
    public static DonaldTrumpDTO getTrumpThought(String url){
        String response = getResponseBody(url);
        DonaldTrumpDTO dto = gson.fromJson(response, DonaldTrumpDTO.class);
        return dto;
    }
    public static DogFactDTO getDogFact(String url){
        String response = getResponseBody(url);
        DogFactDTO dto = gson.fromJson(response, DogFactDTO.class);
        return dto;
    }


}
