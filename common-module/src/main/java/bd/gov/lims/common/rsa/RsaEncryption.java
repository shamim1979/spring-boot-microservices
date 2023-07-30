package bd.gov.lims.common.rsa;

import bd.gov.lims.common.exception.RsaEncryptionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
@DependsOn({"rsaKeyProvider"})
public class RsaEncryption {
    private final RsaKeyProvider rsaKeyProvider;

    @Autowired
    public RsaEncryption(RsaKeyProvider rsaKeyProvider) {
        this.rsaKeyProvider = rsaKeyProvider;
    }

    public String encrypt(String plain) {
        String encrypted;
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, rsaKeyProvider.getKeyPair().getPublic());
            byte[] bytes = cipher.doFinal(plain.getBytes(StandardCharsets.UTF_8));
            encrypted = Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            throw new RsaEncryptionException("Rsa encryption exception: " + e.getMessage());
        }
        return encrypted;
    }

}
