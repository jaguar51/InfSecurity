package me.academeg.cocks;

import me.academeg.cocks.util.TextUtils;
import me.academeg.cocks.util.Utils;

import java.util.Arrays;

/**
 * CocksDecoder Decoder
 *
 * @author Yuriy A. Samsonov <y.samsonov@erpscan.com>
 * @version 1.0
 */
public class CocksDecoder {

    public String decoder(int[][] code, int r, int a, int n) {
        byte[] res = new byte[code.length];
        boolean flag = ((int) Math.pow(r, 2)) % n == a;
        for (int i = 0; i < code.length; i++) {
            int alpha = flag ? code[i][0] + 2 * r : code[i][1] + 2 * r;
            res[i] = (byte) Utils.jacobiSymbol(alpha, n);
        }
        System.out.println(Arrays.toString(res));
        String binaryText = Utils.convertAfterDecoding(res);
        String text = TextUtils.textFromBinaryString(binaryText);
        return text;
    }
}
