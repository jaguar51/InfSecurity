package me.academeg;

import me.academeg.cocks.CocksDecoder;
import me.academeg.cocks.CocksEncoder;
import me.academeg.cocks.CocksPKG;
import me.academeg.cocks.Utils;

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
        int p = 7;
        int q = 11;
        int n = p * q;

        int id = 1;

        int a = CocksPKG.hashA(id, n);

        CocksEncoder encoder = new CocksEncoder();
        String stockText = "Aa";
        int[][] encode = encoder.encode(stockText, a, n);

        CocksDecoder decoder = new CocksDecoder();
        String decode = decoder.decoder(encode, CocksPKG.userR(a, p, q), a, n);
        System.out.println(decode);
    }
}
