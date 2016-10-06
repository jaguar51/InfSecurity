package me.academeg;

import me.academeg.SplitSecretChina.SplitSecretChinaDecode;
import me.academeg.SplitSecretChina.SplitSecretChinaEncode;
import org.junit.Assert;
import org.junit.Test;

public class SplitSecretChinaTest {

    @Test
    public void test1() throws Exception {
        int[] publicKey = {5, 7, 8, 9, 11};
        int secret = 222;
        SplitSecretChinaEncode encoder = new SplitSecretChinaEncode(publicKey, 3);
        encoder.setSecret(secret);
        int[] secretKey = encoder.getSecretKey();

        SplitSecretChinaDecode decoder = new SplitSecretChinaDecode(new int[]{publicKey[1], publicKey[3], publicKey[2]},
                new int[]{secretKey[1], secretKey[3], secretKey[2]});

        Assert.assertEquals(secret, decoder.getSecret());
    }

    @Test
    public void test2() throws Exception {
        int[] publicKey = {2, 3, 7, 11, 17, 23, 31, 41, 43, 53};
        int secret = 20000;
        SplitSecretChinaEncode encoder = new SplitSecretChinaEncode(publicKey, 5);
        encoder.setSecret(secret);
        int[] secretKey = encoder.getSecretKey();

        SplitSecretChinaDecode decoder = new SplitSecretChinaDecode(
                new int[]{publicKey[1], publicKey[3], publicKey[2], publicKey[5], publicKey[9]},
                new int[]{secretKey[1], secretKey[3], secretKey[2], secretKey[5], secretKey[9]});

        Assert.assertEquals(secret, decoder.getSecret());
    }

    @Test
    public void test3() throws Exception {
        int[] publicKey = {2, 3, 7, 11, 17, 23, 31, 41, 43, 53};
        SplitSecretChinaEncode encoder = new SplitSecretChinaEncode(publicKey, 5);
        encoder.setSecret(10000);
        int[] secretKey = encoder.getSecretKey();

        SplitSecretChinaDecode decoder = new SplitSecretChinaDecode(
                new int[]{publicKey[1], publicKey[3], publicKey[2], publicKey[5]},
                new int[]{secretKey[1], secretKey[3], secretKey[2], secretKey[5]});

        Assert.assertNotEquals(encoder.getSecret(), decoder.getSecret());
    }

    @Test
    public void test4() throws Exception {
        int[] publicKey = {2, 3, 7, 11, 17, 23, 31, 41, 43, 53};
        SplitSecretChinaEncode encoder = new SplitSecretChinaEncode(publicKey, 5);
        encoder.setSecret(10000);
        int[] secretKey = encoder.getSecretKey();

        SplitSecretChinaDecode decoder = new SplitSecretChinaDecode(
                new int[]{publicKey[1], publicKey[3], publicKey[2], publicKey[5], publicKey[7], publicKey[9]},
                new int[]{secretKey[1], secretKey[3], secretKey[2], secretKey[5], secretKey[7], secretKey[9]});

        Assert.assertEquals(encoder.getSecret(), decoder.getSecret());
    }
}
