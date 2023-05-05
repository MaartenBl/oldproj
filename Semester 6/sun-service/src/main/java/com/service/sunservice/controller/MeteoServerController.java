package com.service.sunservice.controller;

import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "/meteoserver")
public class MeteoServerController {

    private String meteoServerKey = "5d881ae78e";

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

}
