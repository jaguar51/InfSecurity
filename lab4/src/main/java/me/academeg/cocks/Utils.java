package me.academeg.cocks;

/**
 * @author Yuriy A. Samsonov <y.samsonov@erpscan.com>
 * @version 1.0
 */
final public class Utils {

    /**
     * http://neerc.ifmo.ru/wiki/index.php?title=%D0%90%D0%BB%D0%B3%D0%BE%D1%80%D0%B8%D1%82%D0%BC_%D0%B2%D1%8B%D1%87%D0%B8%D1%81%D0%BB%D0%B5%D0%BD%D0%B8%D1%8F_%D1%81%D0%B8%D0%BC%D0%B2%D0%BE%D0%BB%D0%B0_%D0%AF%D0%BA%D0%BE%D0%B1%D0%B8
     *
     * @param a
     * @param p
     * @return
     */
    public static int jakobiSymbol(int a, int p) {
        if (a < 0) {
            return jakobiSymbol(-a, p) * powMinusOne((p - 1) / 2);
        }

        if (a % 2 == 0 && a != 0) {
            return jakobiSymbol(a / 2, p) * powMinusOne((p * p - 1) / 8);
        }

        if (a == 1) {
            return 1;
        }

        if (a < p) {
            return jakobiSymbol(p, a) * powMinusOne((a - 1) / 2 * (p - 1) / 2);
        }

        return jakobiSymbol(a % p, p);
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
