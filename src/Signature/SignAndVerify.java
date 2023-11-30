package Signature;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class SignAndVerify {
    Cipher cipher;

    public PublicKey getPublicKey(String fileName) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] keybytes = Files.readAllBytes(new File(fileName).toPath());
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keybytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }

    public PrivateKey getPrivateKey(String fileName) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] keybytes = Files.readAllBytes(new File(fileName).toPath());
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keybytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);
    }

    public byte[] encryptText(byte[] msg, PrivateKey privateKey) throws NoSuchPaddingException, NoSuchAlgorithmException,
            IllegalBlockSizeException, BadPaddingException,
            InvalidKeyException {
        cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return Base64.getEncoder().encode(cipher.doFinal(msg));
    }

    public byte[] decryptText(byte[] msg, PublicKey key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(Base64.getDecoder().decode(msg));
    }

}
