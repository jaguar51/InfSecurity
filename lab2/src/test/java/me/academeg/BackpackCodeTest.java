package me.academeg;

import me.academeg.backpack.BackpackDecoder;
import me.academeg.backpack.BackpackEncoder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BackpackCodeTest {

    @Test
    public void test1() throws Exception {
        int[] secretKey = new int[]{1, 2, 4, 8, 16, 32, 64, 128, 256, 512};
        int mod = 1061;
        int mult = 375;
        String sourceText = "Маша ела кашу. Мама мыла раму))";

        BackpackDecoder backpackDecoder = new BackpackDecoder(secretKey, mod, mult);
        int[] publicKey = backpackDecoder.getPublicKey();

        BackpackEncoder backpackEncoder = new BackpackEncoder(publicKey);
        int[] encode = backpackEncoder.encode(sourceText);

        String decode = backpackDecoder.decode(encode);
        assertEquals(sourceText, decode);
    }

    @Test
    public void test2() throws Exception {
        int[] secretKey = new int[]{1, 3, 7, 13, 26, 57, 108, 219, 444, 890};
        int mod = 3529;
        int mult = 375;
        String sourceText = "Маша ела кашу. Мама мыла раму))";

        BackpackDecoder backpackDecoder = new BackpackDecoder(secretKey, mod, mult);
        int[] publicKey = backpackDecoder.getPublicKey();

        BackpackEncoder backpackEncoder = new BackpackEncoder(publicKey);
        int[] encode = backpackEncoder.encode(sourceText);

        String decode = backpackDecoder.decode(encode);
        assertEquals(sourceText, decode);
    }

    @Test
    public void test3() throws Exception {
        int[] secretKey = new int[]{1, 2, 5, 10, 27, 50, 100, 200, 450, 900};
        int mod = 3529;
        int mult = 375;
        String sourceText = "{[((Маша ела кашу. Мама мыла раму))]}";

        BackpackDecoder backpackDecoder = new BackpackDecoder(secretKey, mod, mult);
        int[] publicKey = backpackDecoder.getPublicKey();

        BackpackEncoder backpackEncoder = new BackpackEncoder(publicKey);
        int[] encode = backpackEncoder.encode(sourceText);

        String decode = backpackDecoder.decode(encode);
        assertEquals(sourceText, decode);
    }
}