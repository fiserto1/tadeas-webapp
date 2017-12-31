package tadeas.util;

import org.junit.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.*;

public class MD5UtilTest {

    @Test
    public void generateMD5Hash() throws NoSuchAlgorithmException {
        String actual = MD5Util.generateMD5Hash("heslo");
        assertEquals("955db0b81ef1989b4a4dfeae8061a9a6", actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void generateMD5HashNullParam() throws NoSuchAlgorithmException {
        MD5Util.generateMD5Hash(null);
    }
}