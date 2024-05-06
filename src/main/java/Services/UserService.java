package Services;

import Util.RSAKeyPairManager;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

@ApplicationScoped
public class UserService {

    @Inject
    private RSAKeyPairManager keyPairManager;




    public KeyPair getKeyPair() {
        return keyPairManager.getKeyPair();
    }

    //La contraseña se debe encriptar mediante RSA_PKCS1_OAEP_PADDING
    public static String encryptPassword(String password, PublicKey publicKey) throws Exception {
        //instancia del algoritmo de cifrado RSA con relleno PKCS1
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(password.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decryptPassword(String encryptedPassword, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedPassword);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }

}
