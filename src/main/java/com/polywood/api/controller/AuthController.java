package com.polywood.api.controller;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.google.common.hash.Hashing;
import com.polywood.api.auth.Authenticator;
import com.polywood.api.dto.LoginResponseDto;
import com.polywood.api.exceptions.InternalServerErrorException;
import com.polywood.api.exceptions.UnauthorizedException;
import com.polywood.api.model.LoginForm;
import com.polywood.api.model.UsersEntity;
import com.polywood.api.respositories.UsersEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

@RestController
public class AuthController {

    private UsersEntityRepository usersRepository;

    @Autowired
    public AuthController(UsersEntityRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.POST, value = "/login", produces = "application/json")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginForm loginForm) throws RuntimeException {

        UsersEntity user = usersRepository.findByLogin(loginForm.getLogin());

        if (user == null) {
            throw new UnauthorizedException(); //HTTP 401
        }

        String receivedHash = Hashing.sha256().hashString(loginForm.getPassword(), StandardCharsets.UTF_8).toString();

        if (!receivedHash.equals(user.getPassword())) {
            throw new UnauthorizedException(); //HTTP 401
        }

        LoginResponseDto response = new LoginResponseDto();
        response.setUser(user);
        response.setToken(Authenticator.buildToken(user));

        try {
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (JWTCreationException exception) {
            System.out.println(exception.getMessage());
            throw new InternalServerErrorException();
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UsersEntity> updateUser(@PathVariable(value = "id") String id, @RequestBody UsersEntity newUser, @RequestHeader("Authorization") String token) {

        try {
            Authenticator.verifyAndDecodeToken(token);
        } catch (RuntimeException e) {
            throw new UnauthorizedException();
        }

        UsersEntity u = usersRepository.findById(Integer.parseInt(id)).get();
        u.setLogin(newUser.getLogin());
        u.setFirstname(newUser.getFirstname());
        u.setLastname(newUser.getLastname());

        UsersEntity saved = usersRepository.save(u);

        return new ResponseEntity<>(saved, HttpStatus.OK);

    }

    @PostMapping("/users")
    public ResponseEntity<UsersEntity> addUser(@RequestBody UsersEntity newUser) {

        if(newUser.getLogin()== null || newUser.getPassword() == null) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        String hashed = Hashing.sha256().hashString(newUser.getPassword(), StandardCharsets.UTF_8).toString();
        newUser.setPassword(hashed);

        try {
            UsersEntity saved = usersRepository.save(newUser);
            return new ResponseEntity<>(saved, HttpStatus.OK);
        } catch(Exception e) {
            throw new InternalServerErrorException();
        }
    }
}