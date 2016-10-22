package me.academeg.cocks;

import java.util.Arrays;
import java.util.Random;

/**
 * CocksEncoder Encoder
 *
 * @author Yuriy A. Samsonov <y.samsonov@erpscan.com>
 * @version 1.0
 */
public class CocksEncoder {

    public int[][] encode(String text, int a, int n) {
        Random rand = new Random(System.currentTimeMillis());
        byte[] textForEncoding = Utils.convertForEncoding(TextUtils.textToBinaryString(text));
        System.out.println(Arrays.toString(textForEncoding));
        int[][] res = new int[textForEncoding.length][2];

        for (int i = 0; i < textForEncoding.length; i++) {
            int t1 = rand.nextInt(n);
            while (Utils.jacobiSymbol(t1, n) != textForEncoding[i]) {
                t1 = rand.nextInt(n);
            }

            int t2 = rand.nextInt(n);
            while (Utils.jacobiSymbol(t2, n) != textForEncoding[i] || t1 == t2) {
                t2 = rand.nextInt(n);
            }

            res[i][0] = t1 + a * Utils.multiInverse(t1, n);
            res[i][1] = t2 - a * Utils.multiInverse(t2, n);
        }
        return res;
    }
}
