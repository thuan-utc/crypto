package Asymmetric;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class AsymmetricCrypto {
    private Cipher cipher;

    public PublicKey getPublicKey(String fileName) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] keybytes = Files.readAllBytes(new File(fileName).toPath());
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keybytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");//MD5
        return kf.generatePublic(spec);
    }

    public PrivateKey getPrivateKey(String fileName) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] keybytes = Files.readAllBytes(new File(fileName).toPath());
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keybytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);
    }

    public String encryptText(String msg, PublicKey key) throws NoSuchPaddingException, NoSuchAlgorithmException,
            UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException,
            InvalidKeyException {
        cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return Base64.getEncoder().encodeToString(cipher.doFinal(msg.getBytes(StandardCharsets.UTF_8)));
    }

    public String decryptText(String msg, PrivateKey key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return new String(cipher.doFinal(Base64.getDecoder().decode(msg)), StandardCharsets.UTF_8);
    }
}
