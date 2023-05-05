package com.service.oilservice.controller;

import java.util.List;

import com.service.oilservice.models.EnergyModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/oil")
public class OilController {
    private final List<EnergyModel> olieproducten = getMonthlyOilProd();

    public OilController() throws JSONException{}

    @GetMapping("/all")
    public ResponseEntity getAllOilProduction(){
        try{
            return new ResponseEntity(olieproducten, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(ex.toString(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/{period}")
    public ResponseEntity getMonthOilProd(@PathVariable("period") String period){

        EnergyModel month = olieproducten.stream()
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

    private JSONObject getMonthlyData() throws Exception {
        String url = "https://opendata.cbs.nl/ODataApi/odata/84575NED/TypedDataSet";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));

        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        JSONObject myResponse = new JSONObject(response.toString());
        return myResponse;
    }

    private List<EnergyModel> filterJson(JSONArray values) throws JSONException {
        List<EnergyModel> result = new ArrayList<>();

        for (int i=0; i < values.length(); i++) {
            JSONObject period = values.getJSONObject(i);

            if (period.getString("Perioden").matches("((2020MM)([0-1][0-9]))") ||
                    period.getString("Perioden").equals("2019MM12")){

                result.add(new EnergyModel(
                        period.getString("Perioden"),
                        period.getInt("Olieproducten_7"),
                        "Olieproducten_7"));
            }
        }
        return result;
    }

    private List<EnergyModel> getMonthlyOilProd() throws JSONException {
        JSONObject jsonObject = null;
        try {
            jsonObject = getMonthlyData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filterJson(jsonObject.getJSONArray("value"));
    }

}
