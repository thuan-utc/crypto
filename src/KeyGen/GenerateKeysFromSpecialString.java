//package KeyGen;
//
//import java.security.*;
//import org.bouncycastle.jce.provider.BouncyCastleProvider;
//import org.bouncycastle.jce.ECNamedCurveTable;
//import org.bouncycastle.jce.spec.ECParameterSpec;
//import org.bouncycastle.util.encoders.Base64;
//
//public class GenerateKeysFromSpecialString {
//    public static void main(String[] args) {
//        try {
//            Security.addProvider(new BouncyCastleProvider());
//
//            // Your special string (seed or passphrase)
//            String seedString = "MySpecialSeed123";
//
//            // Derive keys from the special string
//            byte[] seedBytes = seedString.getBytes("UTF-8");
//
//            // Choose an elliptic curve for ECDSA (e.g., secp256r1)
//            ECParameterSpec ecSpec = ECNamedCurveTable.getParameterSpec("secp256r1");
//
//            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", "BC");
//            SecureRandom secureRandom = new SecureRandom(seedBytes);
//            keyPairGenerator.initialize(ecSpec, secureRandom);
//
//            // Generate the key pair
//            KeyPair keyPair = keyPairGenerator.generateKeyPair();
//
//            // Get the private key and public key
//            PrivateKey privateKey = keyPair.getPrivate();
//            PublicKey publicKey = keyPair.getPublic();
//
//            // Encode keys in Base64
//            String privateKeyBase64 = Base64.toBase64String(privateKey.getEncoded());
//            String publicKeyBase64 = Base64.toBase64String(publicKey.getEncoded());
//
//            System.out.println("Private Key: " + privateKeyBase64);
//            System.out.println("Public Key: " + publicKeyBase64);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
//
