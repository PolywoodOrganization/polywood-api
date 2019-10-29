package com.polywood.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@CrossOrigin
@RequestMapping("/movies")
public class MovieController {

    private final String FILM_SERVICE_HOST = "localhost";
    private final int FILM_SERVICE_PORT = 8081;
    private final String FILM_SERVICE_URL = "http://" + FILM_SERVICE_HOST + ":" + FILM_SERVICE_PORT + "/movies/";

    @GetMapping("/")
    public ResponseEntity<String> findAllMovies() {
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForEntity(
                URI.create(FILM_SERVICE_URL), String.class);
    }


    @RequestMapping(value = "", method = GET)
    @ResponseBody
    public ResponseEntity<String> findAllMoviesPaged(
            @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size, @RequestParam("sort") Optional<String> sort) {

        RestTemplate restTemplate = new RestTemplate();
        String url = FILM_SERVICE_URL + "?" +
                "page=" + page.orElse(0) +
                "&size=" + size.orElse(Integer.MAX_VALUE) +
                "&sort=" + sort.orElse("title");

        return restTemplate.getForEntity(
                URI.create(url), String.class);

    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getMovieById(@PathVariable(value = "id") String id) {
        RestTemplate restTemplate = new RestTemplate();
        String url = FILM_SERVICE_URL + id;

        return restTemplate.getForEntity(
                URI.create(url), String.class);
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<String> getMovieImageById(@PathVariable(value = "id") String id) {

        RestTemplate restTemplate = new RestTemplate();
        String url = FILM_SERVICE_URL + "image/" + id;

        return restTemplate.getForEntity(
                URI.create(url), String.class);
    }

    @GetMapping("/casting/{id}")
    public ResponseEntity<String>  getMovieCastingById(@PathVariable(value = "id") String id) {

        RestTemplate restTemplate = new RestTemplate();
        String url = FILM_SERVICE_URL + "casting/" + id;

        return restTemplate.getForEntity(
                URI.create(url), String.class);
    }


}