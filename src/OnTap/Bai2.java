package OnTap;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Bai2 {
    public static byte[] hashMessage(String message, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        return messageDigest.digest(message.getBytes(StandardCharsets.UTF_8));
    }

    public static void main(String[] args) {
        String message = "test-hash-function";
        System.out.println("Message: " + message);
        //2.1 MD5
        try {
            byte[] hashByMD5 = hashMessage(message, "MD5");
            System.out.printf("\nHash by MD5: %s", new String(hashByMD5, StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            System.out.printf("\nCan not hash %s by MD5", message);
        }

        //2.2 SHA1
        try {
            byte[] hashBySHA1 = hashMessage(message, "SHA1");
            System.out.printf("\nHash by SHA1: %s", new String(hashBySHA1, StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            System.out.printf("\nCan not hash %s by SHA1", message);
        }

        //2.2 SHA256
        try {
            byte[] hashBySHA2 = hashMessage(message, "SHA256");
            System.out.printf("\nHash by SHA2: %s", new String(hashBySHA2, StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            System.out.printf("\nCan not hash %s by SHA2: " + e.getMessage(), message);
        }
    }

    public static void main2(String[] args) throws Exception {
        String msg = "Nguyen Quoc Dung";
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(msg.getBytes("UTF-8"));
        byte byteData[] = md.digest();
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            String hex = Integer.toHexString(0xff & byteData[i]);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
    }
}
