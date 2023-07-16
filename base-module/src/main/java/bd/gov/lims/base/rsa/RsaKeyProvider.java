package bd.gov.lims.base.rsa;

import bd.gov.lims.base.exception.JwtInitializationException;
import bd.gov.lims.base.util.ResourceUtil;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Slf4j
public class RsaKeyProvider {
    private final ResourceUtil resourceUtil;
    @Getter
    private final KeyPair keyPair;
    public RsaKeyProvider(ResourceUtil resourceUtil) {
        this.resourceUtil = resourceUtil;
        this.keyPair = new KeyPair(generatePublicKey(),generatePrivateKey());
    }
    private PrivateKey generatePrivateKey() {
        PrivateKey privateKey;
        try {
            String key = resourceUtil.keyAsString("classpath:keys/lims_private_key.pem","PRIVATE");
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(key)));
        } catch (Exception e) {
            throw new JwtInitializationException("JWT Initialize: "+e.getMessage());
        }
        return privateKey;
    }
    private PublicKey generatePublicKey() {
        PublicKey publicKey;
        try {
            String key = resourceUtil.keyAsString("classpath:keys/lims_public_key.pem","PUBLIC");
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(key)));
        } catch (Exception e) {
            throw new JwtInitializationException("JWT Initialize: "+e.getMessage());
        }
        return publicKey;
    }

}
