package me.academeg.cocks;

/**
 * TextUtils
 *
 * @author Yuriy A. Samsonov <y.samsonov@erpscan.com>
 * @version 1.0
 */
public final class TextUtils {

    private static int SYMBOL_SIZE = 16;

    public static String textFromBinaryString(String binaryText) {
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

    public static String textToBinaryString(String text) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            String binary = Integer.toBinaryString((int) text.charAt(i));
            int countBits = SYMBOL_SIZE - binary.length();
            for (int j = 0; j < countBits; j++) {
                builder.append('0');
            }
            builder.append(binary);
        }
        return builder.toString();
    }
}
