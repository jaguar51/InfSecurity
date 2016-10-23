package me.academeg;

import me.academeg.cocks.CocksDecoder;
import me.academeg.cocks.CocksEncoder;
import me.academeg.cocks.CocksPKG;

/**
 * Main
 *
 * @author Yuriy A. Samsonov <y.samsonov@erpscan.com>
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        int p = 23;
        int q = 11;
        int n = p * q;

        int id = 2;

        int a = CocksPKG.hashA(id, n);

        CocksEncoder encoder = new CocksEncoder();
        String stockText = "Aabdcrbrenr";
        int[][] encode = encoder.encode(stockText, a, n);

        CocksDecoder decoder = new CocksDecoder();
        int r = CocksPKG.userR(a, p, q);
        String decode = decoder.decoder(encode, r, a, n);
        System.out.println(decode);
    }
}
