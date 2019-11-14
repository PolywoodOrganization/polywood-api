package com.polywood.api.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.common.hash.Hashing;
import com.polywood.api.exceptions.UnauthorizedException;
import com.polywood.api.model.UsersEntity;
import com.polywood.api.utils.RandomString;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * Classe Authenticator
 * Gère tous les services liés aux tokens
 */
@Service
public class Authenticator {

    private static final String SECRET_KEY = "scLpAQyKs4C5mlGgG3FYmbb1FVYNuyKi";
    private static final String VERIFICATION_KEY = "i89XgrMlBWbJVaiWRm9fTGrw7z1aAgI1";


    public static DecodedJWT verifyAndDecodeToken(String token) throws RuntimeException {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

            //Verify jwt
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("polyauto")
                    .acceptLeeway(7200)
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);

            String login = jwt.getClaim("user").asString();
            String userId = jwt.getClaim("userId").asString();
            String nonce = jwt.getClaim("nonce").asString();

            //Verify verification string
            String verification = generateVerificationString(login, userId, nonce);

            if (!verification.equals(jwt.getClaim("verification").asString())) {
                throw new JWTVerificationException("Invalid verification string");
            } else {
                return jwt;
            }
        } catch (JWTVerificationException exception) {
            System.out.println(exception.getMessage());
            throw new UnauthorizedException();
        }

    }

    private static String generateVerificationString(String login, String userId, String nonce) {
        return Hashing.sha256().hashString(login + nonce + userId + nonce + VERIFICATION_KEY, StandardCharsets.UTF_8).toString();
    }

    public static String buildToken(UsersEntity user) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

        String nonce = new RandomString(64).nextString();

        String verification = generateVerificationString(user.getLogin(), String.valueOf(user.getIduser()), nonce);

        return JWT.create()
                .withClaim("user", user.getLogin())
                .withClaim("userId", String.valueOf(user.getIduser()))
                .withClaim("nonce", nonce)
                .withClaim("verification", verification)
                .withIssuer("polywood")
                .withIssuedAt(new Date())
                .sign(algorithm);
    }
}