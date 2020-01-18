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

    @GetMapping("/favorites")
    public ResponseEntity<List<FavoritesEntity>> getFavoritesByUserToken(@RequestHeader("Authorization") String token) {

        try {
            Authenticator.verifyAndDecodeToken(token);
        } catch (RuntimeException e) {
            throw new UnauthorizedException();
        }

        String id = Authenticator.getUserIdFromToken(token);
        List<FavoritesEntity> favorites = favoritesEntityRepository.findAllByIduser(Integer.parseInt(id));
        return new ResponseEntity<>(favorites, HttpStatus.OK);

    }

    @PostMapping("/favorites")
    public ResponseEntity<FavoritesEntity> addFavorite(@RequestBody FavoritesDto newFavorite, @RequestHeader("Authorization") String token) {

        try {
            Authenticator.verifyAndDecodeToken(token);
        } catch (RuntimeException e) {
            throw new UnauthorizedException();
        }

        String id = Authenticator.getUserIdFromToken(token);

        FavoritesEntity favoritesEntity = new FavoritesEntity();

        if(newFavorite.getIdfavorite() != -1){
            favoritesEntity.setIdfavorite(newFavorite.getIdfavorite());
        }

        favoritesEntity.setIduser(Integer.parseInt(id));
        favoritesEntity.setAdded(newFavorite.getAdded());
        favoritesEntity.setCommentary(newFavorite.getCommentary());
        favoritesEntity.setIdmovie(newFavorite.getIdmovie());

        FavoritesEntity saved = favoritesEntityRepository.save(favoritesEntity);

        return new ResponseEntity<>(saved, HttpStatus.OK);

    }

    @DeleteMapping("/favorites/{id}")
    public ResponseEntity<String> deleteFavoritesByUserId(@PathVariable(value = "id") String idFavorite, @RequestHeader("Authorization") String token) {


        try {
            Authenticator.verifyAndDecodeToken(token);
        } catch (RuntimeException e) {
            throw new UnauthorizedException();
        }
    
        String id = Authenticator.getUserIdFromToken(token);
        List<FavoritesEntity> favorites = favoritesEntityRepository.findAllByIduser(Integer.parseInt(id));
        
        for (FavoritesEntity favorite : favorites) {
            if (favorite.getIdfavorite() == Integer.parseInt(idFavorite)) {
                favoritesEntityRepository.delete(favorite);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/favorites/movie/{id}")
    public ResponseEntity<String> deleteUserFavoriteByMovieId(@PathVariable(value = "id") String idMovie, @RequestHeader("Authorization") String token) {

        try {
            Authenticator.verifyAndDecodeToken(token);
        } catch (RuntimeException e) {
            throw new UnauthorizedException();
        }

        String id = Authenticator.getUserIdFromToken(token);
        List<FavoritesEntity> favorites = favoritesEntityRepository.findAllByIduser(Integer.parseInt(id));

        for (FavoritesEntity favorite : favorites) {
            if (favorite.getIdmovie().equals(idMovie)) {
                favoritesEntityRepository.delete(favorite);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}