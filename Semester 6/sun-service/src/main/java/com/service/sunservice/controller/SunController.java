package com.service.sunservice.controller;

import com.google.gson.*;
import com.service.sunservice.logic.Capacity;
import com.service.sunservice.logic.SolarCalculator;
import com.service.sunservice.model.EnergyModel;
import com.service.sunservice.model.ProductionAtTimeModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.ParseException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static com.google.gson.JsonParser.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "/sun")
public class SunController {
    private JSONObject forecast = getWeatherForecast();;
    public static List<EnergyModel> solarProduction;

    public static String year = "";

    public SunController() throws Exception {
    }

    @GetMapping("/all")
    public @ResponseBody CompletableFuture<String> getLastYear(){
        return getRepos("2020");
    }

    @GetMapping("/{yearToSearch}")
    public @ResponseBody
    CompletableFuture<String> getRepos(@PathVariable(value = "yearToSearch") String yearToSearch){
        //Scuffed
        year = yearToSearch;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://opendata.cbs.nl/ODataApi/odata/84575NED/TypedDataSet"))
                .build();
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(SunController::Filter);
    }

    @GetMapping("/prediction")
    public ResponseEntity getPrediction(){
        try{
            return new ResponseEntity(predictSolarProduction(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(ex.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/predict")
    public @ResponseBody
    EnergyModel getPredictionWithParams(
            @RequestParam int radiation,
            @RequestParam int peakCapacity
    ) throws JSONException {
        int result = SolarCalculator.CalculateMWH(radiation, peakCapacity);
        return new EnergyModel("vandaag", result, "Zonnestroom_15");
    }


    public static String Filter(String data){
        Gson gson = new Gson();

        JSONObject jsonData = null;
        try {
            jsonData = new JSONObject(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONArray periods = null;
        try {
            periods = jsonData.getJSONArray("value");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayList<EnergyModel> periodMonthData = new ArrayList<>();

        for (int i = 0; i < periods.length(); i++){
            JSONObject period = null;
            try {
                period = periods.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                //Scuffed solution for 2020 incomplete year
                if (Integer.parseInt(year) == 2020 && period.getString("Perioden").contains("2019MM12")){
                    periodMonthData.add(new EnergyModel(period.getString("Perioden"), period.getInt("Zonnestroom_15"), "Zonnestroom_15"));
                }

                if (period.getString("Perioden").contains(year + "MM")){

                    String month = period.getString("Perioden");
                    int zonneStroomValue = period.getInt("Zonnestroom_15");
                    String source = "Zonnestroom_15";

                    periodMonthData.add(new EnergyModel(month, zonneStroomValue, source));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        solarProduction = periodMonthData;

        return gson.toJson(periodMonthData);
    }

    private List<EnergyModel> predictSolarProduction() throws JSONException {
        // ugly ass code, lets go, what can go wrong
        double prodPerHour = 2.8;
        // 3600 seconds in an hour.
        double sunUpInHours = (getSunUpTime() / (double) 3600);

        double d0Zon = forecast.getInt("d0zon") / (double) 100;
        double d1Zon = forecast.getInt("d1zon") / (double) 100;
        double d2Zon = forecast.getInt("d2zon") / (double) 100;

        ArrayList<EnergyModel> solarPrediction = new ArrayList<>();

        int d0prod = (int)(sunUpInHours * d0Zon * prodPerHour);
        int d1prod = (int)(sunUpInHours * d1Zon * prodPerHour);
        int d2prod = (int)(sunUpInHours * d2Zon * prodPerHour);

        solarPrediction.add(new EnergyModel("vandaag", d0prod,"Zonnestroom_15"));
        solarPrediction.add(new EnergyModel("morgen", d1prod,"Zonnestroom_15"));
        solarPrediction.add(new EnergyModel("overmorgen", d2prod,"Zonnestroom_15"));

        return solarPrediction;
    }

    private JSONObject getWeatherForecast() throws Exception {
        String url = "https://weerlive.nl/api/json-data-10min.php?&key=10c308236a&locatie=Groningen";

        URL obj = new URL(url);
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

    private long getSunUpTime() throws JSONException {
        String sup = forecast.getString("sup");
        String sunder = forecast.getString("sunder");

        LocalTime t1 = LocalTime.parse(sup);
        LocalTime t2 = LocalTime.parse(sunder);

        Duration d = Duration.between(t1, t2);
        System.out.println(d.toSeconds());
        return d.toSeconds();
    }

    // (Somewhat) Proper code

    /**
     * Get current solar production for a province.
     * @param province Province to calculate
     * @param radiation temp variable system will request this information somewhere
     * @return Production and a timestamp
     */
    @GetMapping("/current/{province}/{radiation}")
    public ResponseEntity getCurrentSolarByArea(@PathVariable(value = "province") String province, @PathVariable(value= "radiation") int radiation){
        Capacity cap;

        try{
            cap = Capacity.valueOf(province.toUpperCase());
        } catch (Exception ex) {
            return new ResponseEntity("Check your spelling on the province!", HttpStatus.BAD_REQUEST);
        }

        int output = SolarCalculator.CalculateMWH(radiation, cap.getPeak());

        ProductionAtTimeModel result = new ProductionAtTimeModel(output, System.currentTimeMillis(), province.toUpperCase());

        try {
            return new ResponseEntity(result, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(ex, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/predictnextfourdays")
    public ResponseEntity predictSolarProductionUpcoming4Days() throws ExecutionException, InterruptedException {

        MeteoServerController meteo = new MeteoServerController();

        Map<Capacity ,String> forecasts = new HashMap<>();
        for (Capacity capacity:
                Capacity.values()) {
            String temp = meteo.getTest(capacity.getStationLocation()).get();
            forecasts.put(capacity ,temp);
        }

        Map<String, List<ProductionAtTimeModel>> calculatedProductionValues = new HashMap<>();

        JsonParser jsonParser = new JsonParser();
        for (Map.Entry<Capacity, String> forecast:forecasts.entrySet()) {
            JsonObject jo = (JsonObject)jsonParser.parse(forecast.getValue());
            JsonArray forecastArray = jo.getAsJsonArray("forecast");
            List<ProductionAtTimeModel> predictionAtLocation = SolarCalculator.calculateNext4Days(forecast.getKey(), forecastArray);
            calculatedProductionValues.put(forecast.getKey().toString(), predictionAtLocation);
        }

        try{
            return new ResponseEntity(calculatedProductionValues, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(ex, HttpStatus.BAD_REQUEST);
        }
    }

}
