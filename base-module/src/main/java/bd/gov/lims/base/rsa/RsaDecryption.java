package bd.gov.lims.base.rsa;

import bd.gov.lims.base.exception.RsaDecryptionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.util.Base64;

@Component
@DependsOn({"rsaKeyProvider"})
@Slf4j
public class RsaDecryption {
    private final RsaKeyProvider rsaKeyProvider;
    @Autowired
    public RsaDecryption(RsaKeyProvider rsaKeyProvider) {
        this.rsaKeyProvider = rsaKeyProvider;
    }
    public String decrypt(String encrypted) {
        Cipher cipher;
        try {
            cipher = Cipher.getInstance("RSA");
        } catch (Exception e) {
            throw new RsaDecryptionException("Rsa decryption exception: " + e.getMessage());
        }
        KeyPair keyPair = rsaKeyProvider.getKeyPair();
        try {
            cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
            byte[] bytes = Base64.getDecoder().decode(encrypted);
            return new String(cipher.doFinal(bytes));
        } catch (Exception e) {

        }
        return null;
    }
}
