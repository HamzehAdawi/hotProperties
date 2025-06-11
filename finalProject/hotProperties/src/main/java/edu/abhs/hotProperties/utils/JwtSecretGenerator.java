package edu.abhs.hotProperties.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.MacAlgorithm;
import javax.crypto.SecretKey;

public class JwtSecretGenerator {
    public static void main(String[] args) {

        MacAlgorithm alg = Jwts.SIG.HS256;
        SecretKey key = alg.key().build();
        String base64Key = Encoders.BASE64.encode(key.getEncoded());
        System.out.println("Generated Base64-encoded secret key:");
        System.out.println(base64Key);
    }
}
