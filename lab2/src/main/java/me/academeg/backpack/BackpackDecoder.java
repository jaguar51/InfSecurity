package me.academeg.backpack;

import java.util.Arrays;

/**
 * Описание
 * https://ru.wikipedia.org/wiki/Ранцевая_криптосистема_Меркла_—_Хеллмана
 * https://sites.google.com/site/anisimovkhv/learning/kripto/lecture/tema8#p83
 * https://webcache.googleusercontent.com/search?q=cache:0pgprGqnvaoJ:https://asoiu.files.wordpress.com/2010/02/d0b7d0b0d0b4d0b0d187d0b0-d0be-d180d18ed0bad0b7d0b0d0bad0b5.ppt+&cd=8&hl=ru&ct=clnk&gl=ru
 * https://ru.wikipedia.org/wiki/%D0%97%D0%B0%D0%B4%D0%B0%D1%87%D0%B0_%D0%BE_%D1%80%D0%B0%D0%BD%D1%86%D0%B5_%D0%B2_%D0%BA%D1%80%D0%B8%D0%BF%D1%82%D0%BE%D0%B3%D1%80%D0%B0%D1%84%D0%B8%D0%B8
 */
@SuppressWarnings("unused")
public class BackpackDecoder {

    private static int SYMBOL_SIZE = 16;

    private int q;
    private int r;
    private int[] secretKey;

    private int[] publicKey;

    public BackpackDecoder(int[] secretKey) {
        this.secretKey = secretKey;
        generatePublicKey();
    }

    public int[] getPublicKey() {
        return publicKey;
    }

    private void generatePublicKey() {
        int totalSumSecretKey = Arrays.stream(secretKey).sum();
        q = PrimaryNumberUtils.getPrimeNumber(totalSumSecretKey);
        r = q / 2 + q / 3 + q / 4 - 2 * (q / 5);
        publicKey = new int[secretKey.length];
        for (int i = 0; i < secretKey.length; i++) {
            publicKey[i] = r * secretKey[i] % q;
        }
    }

    public String decode(int[] code) {
        StringBuilder builder = new StringBuilder();
        int inverseR = multiInverse(r, q);
        for (int el : code) {
            el = el * inverseR % q;
            builder.append(decodeElement(el));
        }
        return textFromBinaryString(builder.toString());
    }

    private char[] decodeElement(int el) {
        char[] bites = new char[secretKey.length];
        for (int i = 0; i < bites.length; i++) {
            bites[i] = '0';
        }
        for (int i = secretKey.length - 1; i >= 0 && el > 0; i--) {
            if (el / secretKey[i] > 0) {
                bites[i] = '1';
                el -= el / secretKey[i] * secretKey[i];
            }
        }
        return bites;
    }

    private String textFromBinaryString(String binaryText) {
        StringBuilder builder = new StringBuilder();
        while (binaryText.length() % SYMBOL_SIZE != 0) {
            binaryText = binaryText + '0';
        }
        for (int i = 0; i < binaryText.length(); i += SYMBOL_SIZE) {
            String binarySymbol = binaryText.substring(i, i + SYMBOL_SIZE);
            char c = (char) Integer.valueOf(binarySymbol, 2).shortValue();
            builder.append(c);
        }
        if (builder.charAt(builder.length() - 1) == 0) {
            builder.deleteCharAt(builder.length() - 1);
        }
        return builder.toString();
    }

    /**
     * Стандартный алгоритм Евклида решает задачу для выражения: ax+by=d
     *
     * @return мультипликативно обратное число для a, по модулю b
     */
    private int multiInverse(long aa, long bb) {
        long a = aa;
        long b = bb;
        long x = 0;
        long y = 1;
        long lastX = 1;
        long lastY = 0;
        long temp;
        while (b != 0) {
            long q = a / b;
            long r = a % b;

            a = b;
            b = r;

            temp = x;
            x = lastX - q * x;
            lastX = temp;

            temp = y;
            y = lastY - q * y;
            lastY = temp;
        }
        return (int) ((lastX % bb + bb) % bb);
    }
}
