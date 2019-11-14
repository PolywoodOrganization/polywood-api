package com.polywood.api.controller;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.google.common.hash.Hashing;
import com.polywood.api.auth.Authenticator;
import com.polywood.api.exceptions.InternalServerErrorException;
import com.polywood.api.exceptions.UnauthorizedException;
import com.polywood.api.model.UsersEntity;
import com.polywood.api.respositories.UsersEntityRepository;
import com.polywood.api.utils.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;

@RestController
public class AuthController {
    @Autowired
    private UsersEntityRepository usersRepository;

    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.POST, value = "/login", produces = "application/json")
    public GenericResponse login(@RequestParam String login, @RequestParam String password) throws RuntimeException {
        UsersEntity user = usersRepository.findByLogin(login);

        if (user == null) {
            throw new UnauthorizedException(); //HTTP 401
        }

        //Check mdp
        String receivedHash = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();

        if (!receivedHash.equals(user.getPassword())) {
            throw new UnauthorizedException(); //HTTP 401
        }

        GenericResponse response = new GenericResponse();

        try {
            String token = Authenticator.buildToken(user);

            //Renvoi du token à l'api
            response.addToContent("token", token);
            response.addToContent("userId", String.valueOf(user.getIdUser()));
            return response;

        } catch (JWTCreationException exception) {
            System.out.println(exception.getMessage());
            //HTTP 500
            throw new InternalServerErrorException();
        }
    }
}