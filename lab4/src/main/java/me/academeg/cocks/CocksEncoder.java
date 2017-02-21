package me.academeg.cocks;

import me.academeg.cocks.util.TextUtils;
import me.academeg.cocks.util.Utils;

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
        Random rand = new Random();
        byte[] textForEncoding = Utils.convertForEncoding(TextUtils.textToBinaryString(text));
//        byte[] textForEncoding = {-1};
        System.out.println(Arrays.toString(textForEncoding));
        int[][] res = new int[textForEncoding.length][2];

        for (int i = 0; i < textForEncoding.length; i++) {
            int t1 = rand.nextInt(n);
            int multiInverseT1 = Utils.multiInverse(t1, n);
            while (Utils.jacobiSymbol(t1, n) != textForEncoding[i] || multiInverseT1 == 0 || multiInverseT1 != t1) {
                t1 = rand.nextInt(n);
                multiInverseT1 = Utils.multiInverse(t1, n);
            }
            res[i][0] = t1 + a * multiInverseT1;

            int t2 = rand.nextInt(n);
            int multiInverseT2 = Utils.multiInverse(t2, n);
            while (Utils.jacobiSymbol(t2, n) != textForEncoding[i] || t1 == t2 || multiInverseT2 == 0
                    || t2 == multiInverseT1 || t1 == multiInverseT2 || multiInverseT2 != t2) {
                t2 = rand.nextInt(n);
                multiInverseT2 = Utils.multiInverse(t2, n);
            }
            res[i][1] = t2 - a * multiInverseT2;

            System.out.println(i);
            System.out.println("t1 mt1 " + t1 + " " + multiInverseT1);
            System.out.println("t2 mt2 " + t2 + " " + multiInverseT2);
            System.out.println();
        }
        return res;
    }
}
