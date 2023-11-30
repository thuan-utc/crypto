package Symmetric;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class SymmetricCryptor {
    private final String Algorithm = "AES";
    private SecretKey secretKey;
    private KeyGenerator keyGen;
    private Cipher cipher;
    private SecretKeySpec secretKeySpec;

    public SymmetricCryptor() throws NoSuchAlgorithmException {
        keyGen = KeyGenerator.getInstance(Algorithm);
        secretKey = keyGen.generateKey();
        String key = "mothaibabonnamsa"; // -> 16byte, instead can you sha256 to hash a random key -> 256 bit
        secretKeySpec = new SecretKeySpec(key.getBytes(), Algorithm);
    }

    public SymmetricCryptor(String key) throws NoSuchAlgorithmException {
        keyGen = KeyGenerator.getInstance(Algorithm);
        secretKey = keyGen.generateKey();
        key = key.substring(0,16);
        secretKeySpec = new SecretKeySpec(key.getBytes(), Algorithm);
    }

    public SecretKey getSecretKey() {
        return secretKey;
    }

    public SecretKeySpec getSecretKeySpec() {
        return secretKeySpec;
    }

    public String encryptText(String msg, SecretKey key)
            throws NoSuchPaddingException,
            NoSuchAlgorithmException,
            InvalidKeyException,
            IllegalBlockSizeException,
            BadPaddingException {
        cipher = Cipher.getInstance(Algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return Base64.getEncoder()
                .encodeToString(cipher.doFinal(msg.getBytes(StandardCharsets.UTF_8)));
    }

    public String decryptText(String msg, SecretKey key)
            throws NoSuchPaddingException,
            NoSuchAlgorithmException,
            InvalidKeyException,
            IllegalBlockSizeException,
            BadPaddingException {
        cipher = Cipher.getInstance(Algorithm);
        cipher.init(Cipher.DECRYPT_MODE, key);
        return new String(cipher.doFinal(Base64.getDecoder().decode(msg)), StandardCharsets.UTF_8);
    }
}
