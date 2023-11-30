package KeyGen;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class GenKey {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        KeyPairGen kg = new KeyPairGen(1024);
        kg.writeToFile("Keys/PrivateKey", kg.getPrivateKey().getEncoded());
        kg.writeToFile("Keys/PublicKey", kg.getPublicKey().getEncoded());
    }
}
