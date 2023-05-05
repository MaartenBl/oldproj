package com.service.meteoserverservice.controller;

import com.service.meteoserverservice.model.Weather;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.ClientEndpoint;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@CrossOrigin(origins = "http://localhost:3000")
@ClientEndpoint
@Controller
@RequestMapping(path = "/meteoserver")
public class MeteoServer {
    private JSONObject forecast = getWeatherForecast();
    private String meteoServerKey = "5d881ae78e";
    /*private String weerliveKey = "a616a89874";*/

    public MeteoServer() throws Exception {
    }

    @GetMapping(path="/{locatie}")
    public @ResponseBody
    CompletableFuture<String> getTest(@PathVariable(value = "locatie") String locatie) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://data.meteoserver.nl/api/solar.php?locatie=" + locatie + "&key=" + meteoServerKey))
                .build();
        return  client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
    }

    @GetMapping("/weather")
    public ResponseEntity getPrediction(){
        try{
            return new ResponseEntity(weatherAndNotes(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(ex.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    public JSONObject returnForecast() throws Exception {
        return getWeatherForecast();
    }

    private JSONObject getWeatherForecast() throws Exception {
        URL obj = new URL("https://weerlive.nl/api/json-data-10min.php?&key=10c308236a&locatie=Groningen");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));

        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        JSONObject myResponse = new JSONObject(response.toString());

        JSONObject forecast = myResponse.getJSONArray("liveweer").getJSONObject(0);
        return forecast;
    }

    private List<Weather> weatherAndNotes() throws JSONException, ParseException {
        ArrayList<Weather> weatherArrayList = new ArrayList<>();

        int wind = forecast.getInt("d0windms");
        int minTemp = forecast.getInt("d0tmin");
        int maxTemp = forecast.getInt("d0tmax");
        String note = "";

        if (wind >= 29){
            note += "Wind speed may damage the wind turbines.";
        }else if (wind <= 3){
            note += "Wind speed to low to produce wind energy.";
        }

        if (maxTemp >= 35){
            note += "<br/> Temperature too high, solar panel efficiency rate drops.";
        }else if (maxTemp <= 10){
            note += "<br/> Temperature too low, solar panel efficiency rate drops.";
        }

        weatherArrayList.add(new Weather(maxTemp, minTemp, wind, note));

        return weatherArrayList;
    }
}
