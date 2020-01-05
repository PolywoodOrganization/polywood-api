package com.polywood.api.controller;

import com.polywood.api.auth.Authenticator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@CrossOrigin
@RequestMapping("/movies")
public class MovieController {

    private final String FILM_SERVICE_HOST = "localhost";
    private final int FILM_SERVICE_PORT = 8081;
    private final String FILM_SERVICE_URL = "http://" + FILM_SERVICE_HOST + ":" + FILM_SERVICE_PORT + "/movies/";

    @RequestMapping(value = "", method = GET)
    @ResponseBody
    public ResponseEntity<String> findAllMoviesPaged(
            @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size, @RequestParam("sort") Optional<String> sort, @RequestHeader("Authorization") String token) {

        try {
            Authenticator.verifyAndDecodeToken(token);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        }

        RestTemplate restTemplate = new RestTemplate();
        String url = FILM_SERVICE_URL + "?" +
                "page=" + page.orElse(0) +
                "&size=" + size.orElse(Integer.MAX_VALUE) +
                "&sort=" + sort.orElse("title");

        return restTemplate.getForEntity(
                URI.create(url), String.class);

    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getMovieById(@PathVariable(value = "id") String id, @RequestHeader("Authorization") String token) {

        try {
            Authenticator.verifyAndDecodeToken(token);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        }

        RestTemplate restTemplate = new RestTemplate();
        String url = FILM_SERVICE_URL + id;

        return restTemplate.getForEntity(
                URI.create(url), String.class);
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<String> getMoviesByGenre(@PathVariable(value = "genre") String genre, @RequestHeader("Authorization") String token) {

        try {
            Authenticator.verifyAndDecodeToken(token);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        }

        RestTemplate restTemplate = new RestTemplate();
        String url = FILM_SERVICE_URL + "genre/" + genre;

        return restTemplate.getForEntity(
                URI.create(url), String.class);
    }

    @GetMapping("/director/{director}")
    public ResponseEntity<String> getMoviesByDirector(@PathVariable(value = "director") String director, @RequestHeader("Authorization") String token) {

        try {
            Authenticator.verifyAndDecodeToken(token);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        }

        RestTemplate restTemplate = new RestTemplate();
        String url = null;
        try {
            url = FILM_SERVICE_URL + "director/" + URLEncoder.encode(director, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong parameters format");
        }

        return restTemplate.getForEntity(
                URI.create(url), String.class);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<String> getMoviesByTitle(@PathVariable(value = "title") String title, @RequestHeader("Authorization") String token) {

        try {
            Authenticator.verifyAndDecodeToken(token);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        }

        RestTemplate restTemplate = new RestTemplate();
        String url = null;
        try {
            url = FILM_SERVICE_URL + "title/" + URLEncoder.encode(title, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong parameters format");
        }

        return restTemplate.getForEntity(
                URI.create(url), String.class);
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<String> getMovieImageById(@PathVariable(value = "id") String id, @RequestHeader("Authorization") String token) {

        try {
            Authenticator.verifyAndDecodeToken(token);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        }

        RestTemplate restTemplate = new RestTemplate();
        String url = FILM_SERVICE_URL + "image/" + id;

        return restTemplate.getForEntity(
                URI.create(url), String.class);
    }

    @GetMapping("/casting/{id}")
    public ResponseEntity<String>  getMovieCastingById(@PathVariable(value = "id") String id, @RequestHeader("Authorization") String token) {

        try {
            Authenticator.verifyAndDecodeToken(token);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        }

        RestTemplate restTemplate = new RestTemplate();
        String url = FILM_SERVICE_URL + "casting/" + id;

        return restTemplate.getForEntity(
                URI.create(url), String.class);
    }


}