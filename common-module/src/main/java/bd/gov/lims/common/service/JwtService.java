package bd.gov.lims.common.service;

import bd.gov.lims.common.rsa.RsaKeyProvider;
import bd.gov.lims.common.util.DateTimeUtil;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.KeyPair;
import java.util.Optional;

public class JwtService {
    private final DateTimeUtil dateTimeUtil;
    private final RsaKeyProvider rsaKeyProvider;
    @Autowired
    public JwtService(DateTimeUtil dateTimeUtil, RsaKeyProvider rsaKeyProvider) {
        this.dateTimeUtil = dateTimeUtil;
        this.rsaKeyProvider = rsaKeyProvider;
    }

    public String generateToken(String userId) {
        KeyPair keyPair = rsaKeyProvider.getKeyPair();
        return Jwts.builder()
                .setExpiration(dateTimeUtil.jwtExpiredDateTime())
                .signWith(keyPair.getPrivate(),SignatureAlgorithm.RS384)
                .setId(userId)
                .compact();
    }

    public String generateRefreshToken(String userId, String sub) {
        KeyPair keyPair = rsaKeyProvider.getKeyPair();
        return Jwts.builder()
                .setExpiration(dateTimeUtil.jwtRefreshTokenExpiredDateTime())
                .signWith(keyPair.getPrivate(),SignatureAlgorithm.RS384)
                .setId(userId)
                .setSubject(sub)
                .compact();
    }

    public Optional<String> verifyToken(String token) {
        KeyPair keyPair = rsaKeyProvider.getKeyPair();
        try {
            String userId = Jwts.parserBuilder()
                    .setSigningKey(keyPair.getPublic())
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getId();
            return Optional.ofNullable(userId);
        } catch (JwtException e) {

        }
        return Optional.empty();
    }

}
