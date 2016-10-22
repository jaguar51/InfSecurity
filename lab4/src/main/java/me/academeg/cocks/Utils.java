package me.academeg.cocks;

/**
 * Utils
 *
 * @author Yuriy A. Samsonov <y.samsonov@erpscan.com>
 * @version 1.0
 */
final public class Utils {

    /**
     * Calculate Jacobi Symbol
     *
     * @param a - number
     * @param p - module
     * @return -1 or 1
     */
    public static int jacobiSymbol(int a, int p) {
        if (a == 0 || p == 0) {
            return 0;
        }

        if (a < 0) {
            return jacobiSymbol(-a, p) * powMinusOne((p - 1) / 2);
        }

        if (a % 2 == 0) {
            return jacobiSymbol(a / 2, p) * powMinusOne((p * p - 1) / 8);
        }

        if (a == 1) {
            return 1;
        }

        if (a < p) {
            return jacobiSymbol(p, a) * powMinusOne((a - 1) / 2 * (p - 1) / 2);
        }

        return jacobiSymbol(a % p, p);
    }

    private static int powMinusOne(int pow) {
        return pow % 2 == 0 ? 1 : -1;
    }

    public static int hash(int hashCode) {
        hashCode ^= (hashCode >>> 20) ^ (hashCode >>> 12);
        hashCode ^= (hashCode >>> 7) ^ (hashCode >>> 4);
        return hashCode < 0 ? -hashCode : hashCode;
    }
}
