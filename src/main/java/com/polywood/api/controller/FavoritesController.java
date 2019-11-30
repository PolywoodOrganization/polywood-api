package com.polywood.api.controller;

import com.polywood.api.auth.Authenticator;
import com.polywood.api.dto.FavoritesDto;
import com.polywood.api.exceptions.UnauthorizedException;
import com.polywood.api.model.FavoritesEntity;
import com.polywood.api.respositories.FavoritesEntityRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@CrossOrigin
public class FavoritesController {

    private FavoritesEntityRepository favoritesEntityRepository;

    @Autowired
    public FavoritesController(FavoritesEntityRepository favoritesEntityRepository) {
        this.favoritesEntityRepository = favoritesEntityRepository;
    }

    @GetMapping("/favorites/{id}")
    public ResponseEntity<List<FavoritesEntity>> getFavoritesByUserId(@PathVariable(value = "id") String id, @RequestHeader("Authorization") String token) {

        try {
            Authenticator.verifyAndDecodeToken(token);
        } catch (RuntimeException e) {
            throw new UnauthorizedException();
        }

        List<FavoritesEntity> favorites = favoritesEntityRepository.findAllByIduser(Integer.parseInt(id));
        return new ResponseEntity<>(favorites, HttpStatus.OK);

    }

    @PostMapping("/favorites")
    public ResponseEntity<String> addFavoritesByUserId(@RequestBody FavoritesDto newFavorite, @RequestHeader("Authorization") String token) {

        try {
            Authenticator.verifyAndDecodeToken(token);
        } catch (RuntimeException e) {
            throw new UnauthorizedException();
        }

        FavoritesEntity favoritesEntity = new FavoritesEntity();
        favoritesEntity.setIduser(newFavorite.getIduser());
        favoritesEntity.setAdded(newFavorite.getAdded());
        favoritesEntity.setCommentary(newFavorite.getCommentary());
        favoritesEntity.setIdmovie(newFavorite.getIdmovie());

        favoritesEntityRepository.save(favoritesEntity);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping("/favorites")
    public ResponseEntity<String> deleteFavoritesByUserId(@RequestBody FavoritesDto newFavorite, @RequestHeader("Authorization") String token) {

        try {
            Authenticator.verifyAndDecodeToken(token);
        } catch (RuntimeException e) {
            throw new UnauthorizedException();
        }

        FavoritesEntity favoritesEntity = new FavoritesEntity();
        favoritesEntity.setIdfavorite(newFavorite.getIdfavorite());
        favoritesEntity.setIduser(newFavorite.getIduser());
        favoritesEntity.setAdded(newFavorite.getAdded());
        favoritesEntity.setCommentary(newFavorite.getCommentary());
        favoritesEntity.setIdmovie(newFavorite.getIdmovie());

        favoritesEntityRepository.delete(favoritesEntity);

        return new ResponseEntity<>(HttpStatus.OK);

    }

}