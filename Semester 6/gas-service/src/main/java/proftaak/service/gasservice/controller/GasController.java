package proftaak.service.gasservice.controller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import proftaak.service.gasservice.model.EnergyModel;

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
@RequestMapping(value = "/gas")
public class GasController {
    private final double PERCENTAGE_TO_ENERGY  = 26.43;
    private List<EnergyModel> gasProductionlist = getAllGasProduction();

    public GasController() throws Exception {}

    @GetMapping("/all")
    public @ResponseBody
    List<EnergyModel> getAll() throws JSONException {
        return getAllGasProduction();
    }

    @GetMapping("/{period}")
    public ResponseEntity getMonthProd(@PathVariable("period") String period){

        EnergyModel month = gasProductionlist.stream()
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

    @GetMapping("/predict")
    public ResponseEntity getPrediction(@RequestParam double nm3){
        try{
            return new ResponseEntity(predictGasProduction(nm3), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(ex.toString(), HttpStatus.BAD_REQUEST);
        }
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

    private List<EnergyModel> getAllGasProduction() throws JSONException {
        JSONObject jsonObject = null;
        try {
            jsonObject = getApiData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getMonthlyGasProd();
    }

    //1 Nm3 corresponds to 35.17 MJ / 3.6 MJ = 9.769 kWh = 9.769 / 1000 = 0.0097694 MWH
    private double calculateMwh(double nm3) throws ParseException {
        //26.43% of the extracted gas in nm3 is used for energy production.
        double usable = (nm3/100) * PERCENTAGE_TO_ENERGY;
        double mwh = ((usable * 35.17) / 3.6) / 1000;

        return formattedDouble(mwh);
    }

    private double formattedDouble(double value) throws ParseException {
        DecimalFormat df = new DecimalFormat("0.00");
        String formate = df.format(value);

        return (double) (Double)df.parse(formate);
    }

    private List<EnergyModel> predictGasProduction(double nm3) throws JSONException, ParseException {
        ArrayList<EnergyModel> gasPrediction = new ArrayList<>();

        gasPrediction.add(new EnergyModel(
                "Simulation",
                calculateMwh(nm3),
                "Aardgas_8"
        ));

        return gasPrediction;
    }


    private List<EnergyModel> filterJson(JSONArray values) throws JSONException {
        List<EnergyModel> result = new ArrayList<>();

        for (int i=0; i < values.length(); i++) {
            JSONObject period = values.getJSONObject(i);

            if (period.getString("Perioden").matches("((2020MM)([0-1][0-9]))") ||
                    period.getString("Perioden").equals("2019MM12")){

                result.add(new EnergyModel(
                        period.getString("Perioden"),
                        period.getInt("Aardgas_8"),
                        "Aardgas_8"
                ));
            }

        }

        return result;
    }
    private List<EnergyModel> getMonthlyGasProd() throws JSONException {
        JSONObject jsonObject = null;
        try {
            jsonObject = getApiData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filterJson(jsonObject.getJSONArray("value"));
    }
}
