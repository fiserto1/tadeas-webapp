package tadeas.util;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

    public static String generateMD5Hash(final String input) throws NoSuchAlgorithmException {
        if (input == null) {
            throw new IllegalArgumentException("Input string cannot be null.");
        }

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(input.getBytes());

        byte byteData[] = md.digest();

        //convert the byte to hex format method 1
        StringBuilder sb = new StringBuilder();
        for (byte aByteData : byteData) {
            String byteAsHex = Integer.toString((aByteData & 0xff) + 0x100, 16).substring(1);
            sb.append(byteAsHex);
        }
        return sb.toString();
    }
}
