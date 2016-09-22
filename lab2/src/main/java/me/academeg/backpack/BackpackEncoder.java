package me.academeg.backpack;

@SuppressWarnings("unused")
public class BackpackEncoder {

    private static int SYMBOL_SIZE = 16;

    private int[] publicKey;

    public BackpackEncoder(int[] publicKey) {
        this.publicKey = publicKey;
    }

    public int[] getPublicKey() {
        return publicKey;
    }

    public int[] encode(String text) {
        String binaryText = textToBinaryString(text);
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

        int keySize = publicKey.length;
        if (builder.length() % keySize != 0) {
            int count = keySize - builder.length() % keySize;
            for (int i = 0; i < count; i++) {
                builder.append('0');
            }
        }
        return builder.toString();
    }
}
