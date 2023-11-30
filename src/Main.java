import Symmetric.SymmetricCryptor;

import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        File input = new File("inputEncode.txt");
        File inputDecode = new File("inputDecode.txt");
        BufferedReader reader = new BufferedReader(new FileReader(input));
        String key = reader.readLine();
        String plainText = reader.readLine();
        reader.close();

        SymmetricCryptor SC = new SymmetricCryptor();
//        String msg = "Nguyễn Đức Thuận";
        String msg = plainText;
        String encrypted_msg =
                SC.encryptText(msg, SC.getSecretKey());
        System.out.println("Plain text: " + msg);
        System.out.println("Encrypted text: " + encrypted_msg);

        BufferedWriter writer = new BufferedWriter(new FileWriter(inputDecode));
        writer.write(key + "\n");
        writer.write(encrypted_msg);
        writer.close();

        reader = new BufferedReader(new FileReader(inputDecode));
        key = reader.readLine();
        encrypted_msg = reader.readLine();
        String decrypted_msg =
                SC.decryptText(encrypted_msg, SC.getSecretKey());
        System.out.println("Decrypted text: " + decrypted_msg);
        //--------------------------------------------------------------

//        msg = "Ma hoa lan 2";
//        encrypted_msg =
//                SC.encryptText(msg, SC.getSecretKeySpec());
//        System.out.println("Plain text: " + msg);
//        System.out.println("Encrypted text: " + encrypted_msg);
//        decrypted_msg =
//                SC.decryptText(encrypted_msg, SC.getSecretKeySpec());
//        System.out.println("Decrypted text: " + decrypted_msg);
    }
}