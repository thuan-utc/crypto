package Asymmetric;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class AsymmetricDemo {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        AsymmetricCrypto asymmetricCrypto = new AsymmetricCrypto();
        String msg = "Nguyen Van A";
        String en_msg = asymmetricCrypto.encryptText(msg, asymmetricCrypto.getPublicKey("Keys/PublicKey"));
        System.out.println("Plain text: " + msg);
        System.out.println("Encrypted text: " + msg);
        String de_msg = asymmetricCrypto.decryptText(en_msg, asymmetricCrypto.getPrivateKey("Keys/PrivateKey"));
        System.out.println("Decrypted text: " + de_msg);
    }
}
