package com.polywood.api.controller;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.google.common.hash.Hashing;
import com.polywood.api.auth.Authenticator;
import com.polywood.api.exceptions.InternalServerErrorException;
import com.polywood.api.exceptions.UnauthorizedException;
import com.polywood.api.model.UsersEntity;
import com.polywood.api.respositories.UsersEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;

@RestController
public class AuthController {

    private UsersEntityRepository usersRepository;

    @Autowired
    public AuthController(UsersEntityRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.GET, value = "/login", produces = "application/json")
    public ResponseEntity<String> login(@RequestParam String login, @RequestParam String password) throws RuntimeException {
        UsersEntity user = usersRepository.findByLogin(login);

        if (user == null) {
            throw new UnauthorizedException(); //HTTP 401
        }

        String receivedHash = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();

        if (!receivedHash.equals(user.getPassword())) {
            throw new UnauthorizedException(); //HTTP 401
        }

        try {
            return new ResponseEntity<>(Authenticator.buildToken(user), HttpStatus.OK);

        } catch (JWTCreationException exception) {
            System.out.println(exception.getMessage());
            throw new InternalServerErrorException();
        }
    }
}