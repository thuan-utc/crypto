package KeyGen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;

public class KeyPairGen {
    private KeyPairGenerator keyGen;
    private KeyPair keyPair;
    private PublicKey publicKey;
    private PrivateKey privateKey;

    public KeyPairGen(int keyLength) throws NoSuchAlgorithmException {
        keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(keyLength);
        keyPair = keyGen.generateKeyPair();
        publicKey = keyPair.getPublic();
        privateKey = keyPair.getPrivate();
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public void writeToFile(String path, byte[] key) throws IOException {
        File f = new File(path);
        f.getParentFile().mkdir();
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(key);
        fos.flush();
        fos.close();
    }
}
