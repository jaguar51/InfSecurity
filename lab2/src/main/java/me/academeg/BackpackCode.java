package me.academeg;

/**
 * Описание
 * https://ru.wikipedia.org/wiki/Ранцевая_криптосистема_Меркла_—_Хеллмана
 */
@SuppressWarnings("unused")
public class BackpackCode {

    private static int SYMBOL_SIZE = 16;
    int q;
    int r;
    private int[] privateKey;
    private int[] publicKey;

    public BackpackCode(int[] privateKey) {
        this.privateKey = privateKey;
        generatePublicKey();
    }

    private void generatePublicKey() {
        int totalSumPrivateKey = 0;
        for (int iKey : privateKey) {
            totalSumPrivateKey += iKey;
        }

//        q = PrimaryNumberUtils.getPrimeNumber(totalSumPrivateKey);
//        r = q / 2;
        q = 881;
        r = 588;

        publicKey = new int[privateKey.length];
        for (int i = 0; i < privateKey.length; i++) {
            publicKey[i] = r * privateKey[i] % q;
        }
    }

    public int[] encode(String text) {
        String binaryText = textToBinaryString(text);
        System.out.println(binaryText);
        int keySize = publicKey.length;
        int[] code = new int[binaryText.length() / keySize];
        int posCode = 0;
        for (int i = 0; i < binaryText.length(); i += keySize) {
            for (int j = 0; j < keySize; j++) {
                if (binaryText.charAt(i + j) == '1') {
                    code[posCode] += publicKey[j];
                }
            }
            posCode++;
        }
        return code;
    }

    public String decode(int[] code) {
        StringBuilder builder = new StringBuilder();
        int inverseR = multiInverse(r, q);
        System.out.println("in " + inverseR + " q " + q + " r " + r);
        for (int el : code) {
            el = el * inverseR % q;
            builder.append(decodeElement(el));
        }
        System.out.println(builder.toString());
        return textFromBinaryString(builder.toString());
    }

    public char[] decodeElement(int el) {
        char[] bites = new char[privateKey.length];
        for (int i = 0; i < bites.length; i++) {
            bites[i] = '0';
        }
        for (int i = privateKey.length - 1; i >= 0 && el > 0; i--) {
            if (el / privateKey[i] > 0) {
                bites[i] = '1';
                el -= el / privateKey[i] * privateKey[i];
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
        return builder.toString();
    }

    private String textToBinaryString(String text) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            String binary = Integer.toBinaryString((int) text.charAt(i));
            int countBits = SYMBOL_SIZE - binary.length();
            for (int j = 0; j < countBits; j++) {
                builder.append('0');
            }
            builder.append(binary);
        }

        int keySize = privateKey.length;
        if (builder.length() % keySize != 0) {
            int count = keySize - builder.length() % keySize;
            for (int i = 0; i < count; i++) {
                builder.append('0');
            }
        }
        return builder.toString();
    }

    /**
     * Стандартный алгоритм Евклида решает задачу для выражения: ax+by=d
     * Чтобы решить для случая, ax-by=d; мы инвертируем значение b.
     *
     * @return мультипликативно обратное число для a
     */
    public int multiInverse(long a, long b) {
        b = -b;
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
        return (int) 442;
    }

    public int[] getPublicKey() {
        return publicKey;
    }
}
