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

    public static String convertStringForEncoding(String stock) {
        StringBuilder builder = new StringBuilder(stock.length());
        for (int i = 0; i < stock.length(); i++) {
            builder.append(stock.charAt(i) == '0' ? "-1" : stock.charAt(i));
        }
        return builder.toString();
    }

    public static String convertStringAfterDecoding(String decode) {
        StringBuilder builder = new StringBuilder(decode.length());
        int i = 0;
        while (i < decode.length()) {
            if (decode.charAt(i) == '-') {
                builder.append("0");
                i++;
            } else {
                builder.append(decode.charAt(i));
            }
            i++;
        }
        return builder.toString();
    }
}
