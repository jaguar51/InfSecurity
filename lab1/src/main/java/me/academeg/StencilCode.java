package me.academeg;

import java.util.Random;

public class StencilCode {

    private static char SPECIAL_FREE_SYMBOL = '~';
    private Random random;
    private String garbageSymbols;

    public StencilCode() {
        random = new Random(System.currentTimeMillis());
        garbageSymbols = "abcdefghijklmnopqrstuvwxyzабвгдеёжхийклмнопрстфухцчшщъыьэюя0123456789";
    }

    public String encode(byte[][] key, String txt) {
        int size = key.length;
        StringBuilder builder = new StringBuilder();

        int pos = 0;
        while (pos < txt.length()) {
            char[][] code = new char[size][size];
            for (int countRotate = 0; countRotate < 4; countRotate++) {
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        if (pos >= txt.length()) {
                            if (key[i][j] == 1 && code[i][j] == 0) {
                                code[i][j] = SPECIAL_FREE_SYMBOL;
                            }
                        }
                        if (key[i][j] == 1 && code[i][j] == 0) {
                            code[i][j] = txt.charAt(pos);
                            pos++;
                        }
                    }
                }
                rotateKey(key);
            }
            addGarbage(code);
            for (char[] chars : code) {
                builder.append(chars);
            }
        }

        return builder.toString();
    }

    public String decode(byte[][] key, String code) {
        int size = key.length;
        StringBuilder builder = new StringBuilder();

        int pos = 0;
        while (pos < code.length()) {
            char[][] codeArr = new char[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    codeArr[i][j] = code.charAt(pos);
                    pos++;
                }
            }
            for (int countRotate = 0; countRotate < 4; countRotate++) {
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        if (key[i][j] == 1 && codeArr[i][j] != 0 && codeArr[i][j] != SPECIAL_FREE_SYMBOL) {
                            builder.append(codeArr[i][j]);
                            codeArr[i][j] = 0;
                        }
                    }
                }
                rotateKey(key);
            }
        }

        return builder.toString();
    }

    /**
     * Add garbage o code
     */
    private void addGarbage(char[][] code) {
        for (int i = 0; i < code.length; i++) {
            for (int j = 0; j < code[i].length; j++) {
                if (code[i][j] == 0) {
                    code[i][j] = garbageSymbols.charAt(random.nextInt(garbageSymbols.length()));
                }
            }
        }
    }

    /**
     * Turn right matrix
     */
    private void rotateKey(byte[][] key) {
        byte tmp;
        int n = key.length;
        for (int i = 0; i < n / 2; i++) {
            for (int j = i; j < n - 1 - i; j++) {
                tmp = key[i][j];
                key[i][j] = key[n - j - 1][i];
                key[n - j - 1][i] = key[n - i - 1][n - j - 1];
                key[n - i - 1][n - j - 1] = key[j][n - i - 1];
                key[j][n - i - 1] = tmp;
            }
        }
    }

    @SuppressWarnings("unused")
    private byte[][] copyMatrix(byte[][] matrix) {
        byte[][] res = new byte[matrix.length][];
        for (int i = 0; i < matrix.length; i++) {
            res[i] = new byte[matrix[i].length];
            System.arraycopy(matrix[i], 0, res[i], 0, matrix[i].length);
        }
        return res;
    }

    @SuppressWarnings("unused")
    private void print(byte[][] arr) {
        for (byte[] subArr : arr) {
            for (byte el : subArr) {
                System.out.print(el + " ");
            }
            System.out.println();
        }
    }

    @SuppressWarnings("unused")
    private void print(char[][] arr) {
        for (char[] subArr : arr) {
            for (char el : subArr) {
                System.out.print(el + " ");
            }
            System.out.println();
        }
    }
}
