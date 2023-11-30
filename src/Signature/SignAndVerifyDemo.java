package Signature;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class SignAndVerifyDemo {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException,
            NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {
        /* Ký số */
        String message = "Nguyen Duc Thuan";
        //Băm bản tin
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] messageHash = messageDigest.digest(message.getBytes(StandardCharsets.UTF_8));
        // Mã hóa bản băm
        SignAndVerify signAndVerify = new SignAndVerify();
        byte[] signature = signAndVerify.encryptText(messageHash, signAndVerify.getPrivateKey("Keys/PrivateKey"));
        System.out.println("Kí số");
        System.out.println("Bản tin:           " + message);
        System.out.println("Bản tin đã băm:    " + Base64.getEncoder().encodeToString(messageHash));
        System.out.println("Chữ kí:            " + Base64.getEncoder().encodeToString(signature));

        /* Xác minh khi nhận được bản tin(message) và chữ kí(signature) */
        byte[] messageHash2 = messageDigest.digest(message.getBytes(StandardCharsets.UTF_8));
        // Xác minh chữ kí bằng cách giả mã và so sánh với bản băm của bản tin
        byte[] decodedSignature = signAndVerify.decryptText(signature, signAndVerify.getPublicKey("Keys/PublicKey"));
        //so sánh decodedSignature và messageHash2
        boolean isVerified = MessageDigest.isEqual(messageHash2, decodedSignature);
        System.out.println("------------------------------------------------------------------------------------------------------------------");
        System.out.println("Xác minh");
        System.out.println("Bản tin:          " + message);
        System.out.println("Bản tin đã băm:   " + Base64.getEncoder().encodeToString(messageHash2));
        System.out.println("Chữ kí:           " + Base64.getEncoder().encodeToString(signature));
        System.out.println("Giải mã thu được: " + Base64.getEncoder().encodeToString(decodedSignature));
        System.out.print("Kết quả: ");
        if (isVerified) {
            System.out.print("Verify success!");
        } else {
            System.out.print("Verify failed!");
        }
    }
}
