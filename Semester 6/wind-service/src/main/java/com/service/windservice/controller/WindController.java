package com.service.windservice.controller;


import com.service.windservice.model.EnergyModel;
import org.json.JSONArray;
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
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@ClientEndpoint
@Controller
@RequestMapping(value = "/wind")
public class WindController {
    private List<EnergyModel> windProductionsList = getAllWindproduction();
    private String weerliveKey = "708aa95d94";
    private JSONObject forecast = getWeatherForecast();

    public WindController() throws Exception {}

    @GetMapping("/all")
    public @ResponseBody
    List<EnergyModel> getAll() {
        //hier juiste data pakken
        return windProductionsList;
    }

    @GetMapping("/{period}")
    public ResponseEntity getMonthProd(@PathVariable("period") String period){

        EnergyModel month = windProductionsList.stream()
                .filter(entry -> period.equals(entry.getMonth()))
                .findAny()
                .orElse(null);

        if (month != null) {
            try{
                return new ResponseEntity(month, HttpStatus.OK);
            } catch (Exception ex) {
                return new ResponseEntity(ex.toString(), HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity("Not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/prediction")
    public ResponseEntity getPrediction(){
        try{
            return new ResponseEntity(predictWindProduction(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(ex.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/predict")
    public @ResponseBody
    List<EnergyModel> getPredictionWithParams(
            @RequestParam double windspeed,
            @RequestParam int turbines,
            @RequestParam double rotar,
            @RequestParam double air
    ) throws JSONException, ParseException {
        return predictWindProductionWithParams(windspeed,turbines,rotar,air);
    }

    @PostMapping("/")
    public @ResponseBody
    ResponseEntity<String> add(@RequestBody String body){
        //add
        return ResponseEntity.ok("Saved");
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> delete(@PathVariable(value = "") int id){
        //delete
        return ResponseEntity.ok("Deleted");
    }

    private JSONObject getApiData() throws Exception {
        URL url = new URL("https://opendata.cbs.nl/ODataApi/odata/84575NED/TypedDataSet");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));

        String input;
        StringBuffer strBuff = new StringBuffer();
        while ((input = in.readLine()) != null) {
            strBuff.append(input);
        }
        in.close();

        JSONObject json = new JSONObject(strBuff.toString());

        return json;
    }

/*    private List<EnergyModel> jsonToWindProduction(JSONArray values) throws JSONException {
        List<EnergyModel> result = new ArrayList<>();

        for (int i = values.length() - 1; i >= 0; i--) {
            JSONObject jsonObject = values.getJSONObject(i);

            if (!jsonObject.isNull("WindenergieTotaal_12")) {

                result.add(new EnergyModel(
                        jsonObject.getString("Perioden"),
                        jsonObject.getInt("WindenergieTotaal_12"),
                        "WindenergieTotaal_12"));
            }
        }

        return result;
    }*/

    private JSONObject getWeatherForecast() throws Exception {
        URL obj = new URL("https://weerlive.nl/api/json-data-10min.php?&key="+weerliveKey+"&locatie=Groningen");
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

    private List<EnergyModel> getAllWindproduction() throws JSONException {
        JSONObject jsonObject = null;
        try {
            jsonObject = getApiData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getMonthlyWindProd();
    }

    //Watt = 0.5 * (oppervlakte rotorblad windmolen in m2 * windsnelheid in m/s * luchtdichtheid * windesnelheid2)
    private double calculateKwh(double windspeed, int amountturbines, double surface, double air) throws ParseException {
        double windspeed2 = Math.pow(windspeed, 2);
        double kwatt = 0.5 * (((surface * windspeed ) * air) * windspeed2) /1000;
        double total = kwatt * amountturbines;

        return formattedDouble(total);
    }

    private double formattedDouble(double value) throws ParseException {
        DecimalFormat df = new DecimalFormat("0.00");
        String formate = df.format(value);

        return (double) (Double)df.parse(formate);
    }

    private List<EnergyModel> predictWindProduction() throws JSONException, ParseException {
        //windsnelheid vandaag, morgen, overmorgen
        int d0wind = forecast.getInt("d0windms");
        int d1wind = forecast.getInt("d1windms");
        int d2wind = forecast.getInt("d2windms");

        ArrayList<EnergyModel> windPrediction = new ArrayList<>();

        double d0prod = calculateKwh(d0wind,563,0,0);
        double d1prod = calculateKwh(d1wind,563,0,0);
        double d2prod = calculateKwh(d2wind,563,0,0);

        windPrediction.add(new EnergyModel("vandaag", d0prod,"WindenergieTotaal_12"));
        windPrediction.add(new EnergyModel("morgen", d1prod,"WindenergieTotaal_12"));
        windPrediction.add(new EnergyModel("overmorgen", d2prod,"WindenergieTotaal_12"));

        return windPrediction;
    }

    private List<EnergyModel> predictWindProductionWithParams(double windspeed, int turbines, double surface, double air) throws JSONException, ParseException {
        ArrayList<EnergyModel> windPrediction = new ArrayList<>();

        double d0prod = calculateKwh(windspeed,turbines,surface,air);

        windPrediction.add(new EnergyModel("Simulation", d0prod,"WindenergieTotaal_12"));

        return windPrediction;
    }

    private List<EnergyModel> filterJson(JSONArray values) throws JSONException {
        List<EnergyModel> result = new ArrayList<>();

        for (int i=0; i < values.length(); i++) {
            JSONObject period = values.getJSONObject(i);

            if (period.getString("Perioden").matches("((2020MM)([0-1][0-9]))") ||
                    period.getString("Perioden").equals("2019MM12")){

                result.add(new EnergyModel(
                        period.getString("Perioden"),
                        period.getInt("WindenergieTotaal_12"),
                        "WindenergieTotaal_12"
                ));
            }

        }

        return result;
    }
    private List<EnergyModel> getMonthlyWindProd() throws JSONException {
        JSONObject jsonObject = null;
        try {
            jsonObject = getApiData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filterJson(jsonObject.getJSONArray("value"));
    }

}
