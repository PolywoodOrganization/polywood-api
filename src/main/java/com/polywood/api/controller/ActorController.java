package com.polywood.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@CrossOrigin
@RequestMapping("/actors")
public class ActorController {
    private final String ACTOR_SERVICE_HOST = "localhost";
    private final int ACTOR_SERVICE_PORT = 8082;
    private final String ACTOR_SERVICE_URL = "http://" + ACTOR_SERVICE_HOST + ":" + ACTOR_SERVICE_PORT + "/actors/";


    @GetMapping("/")
    public ResponseEntity<String> findAllActors() {
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForEntity(
                URI.create(ACTOR_SERVICE_URL), String.class);
    }


    @RequestMapping(value = "", method = GET)
    @ResponseBody
    public ResponseEntity<String> findAllActorsPaged(
            @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size, @RequestParam("sort") Optional<String> sort) {

        RestTemplate restTemplate = new RestTemplate();
        String url = ACTOR_SERVICE_URL + "?" +
                "page=" + page.orElse(0) +
                "&size=" + size.orElse(Integer.MAX_VALUE) +
                "&sort=" + sort.orElse("name");

        return restTemplate.getForEntity(
                URI.create(url), String.class);

    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getActorById(@PathVariable(value = "id") String id) {
        RestTemplate restTemplate = new RestTemplate();
        String url = ACTOR_SERVICE_URL + id;

        return restTemplate.getForEntity(
                URI.create(url), String.class);
    }

    @GetMapping("/filmography/{id}")
    public ResponseEntity<String>  getFilmographyById(@PathVariable(value = "id") String id) {

        RestTemplate restTemplate = new RestTemplate();
        String url = ACTOR_SERVICE_URL + "filmography/" + id;

        return restTemplate.getForEntity(
                URI.create(url), String.class);
    }

}
