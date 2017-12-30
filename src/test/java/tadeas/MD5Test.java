package tadeas;

import org.junit.Test;
import tadeas.util.MD5Util;

import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertEquals;

public class MD5Test {
    @Test
    public void md5HashShouldMatch() throws NoSuchAlgorithmException {

        assertEquals(MD5Util.generateMD5Hash("heslo"), "955db0b81ef1989b4a4dfeae8061a9a6");
    }
}
