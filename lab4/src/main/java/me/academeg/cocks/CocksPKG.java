package me.academeg.cocks;

import java.math.BigInteger;

/**
 * CocksPKG
 *
 * @author Yuriy A. Samsonov <y.samsonov@erpscan.com>
 * @version 1.0
 */
final public class CocksPKG {

    public static int hashA(int id, int n) {
        id = Utils.hash(id);
        while (Utils.jacobiSymbol(id, n) != 1) {
            id = Utils.hash(id);
        }
        return id;
    }

    public static int userR(int a, int p, int q) {
        int n = p * q;
        int pow = (n + 5 - p - q) / 8;
        BigInteger bigInteger = BigInteger.valueOf(a).pow(pow).mod(BigInteger.valueOf(n));
        return bigInteger.intValue();
    }
}
