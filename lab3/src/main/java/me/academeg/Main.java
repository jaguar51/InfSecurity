package me.academeg;

import me.academeg.SplitSecretChina.SplitSecretChinaDecode;
import me.academeg.SplitSecretChina.SplitSecretChinaEncode;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private Scanner scanner;

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1. Разделить секрет");
            System.out.println("2. Собрать секрет");
            System.out.println("3. Выход");
            int action = scanner.nextInt();
            if (action == 1) {
                splitSecret();
            } else if (action == 2) {
                collectSecret();
            } else {
                break;
            }
        }
    }

    private void splitSecret() {
        System.out.println("Введите размер ключа: ");
        int size = scanner.nextInt();

        System.out.println("Введите кол-во частей для сбори секрета: ");
        int k = scanner.nextInt();

        System.out.println("Введите публичный ключ:");
        int[] publicKey = new int[size];
        for (int i = 0; i < size; i++) {
            publicKey[i] = scanner.nextInt();
        }

        SplitSecretChinaEncode encoder = new SplitSecretChinaEncode(publicKey, k);
        System.out.println("Выберите секрет из диапозона:");
        System.out.println(Arrays.toString(encoder.getRangeSecret()));
        int secret = scanner.nextInt();
        encoder.setSecret(secret);

        for (int el : encoder.getSecretKey()) {
            System.out.printf(el + " ");
        }
        System.out.println();
    }

    private void collectSecret() {
        System.out.println("Введите кол-во частей:");
        int k = scanner.nextInt();

        System.out.println("Введите публичные ключи: ");
        int[] publicKey = new int[k];
        for (int i = 0; i < k; i++) {
            publicKey[i] = scanner.nextInt();
        }

        System.out.println("Введите секретные ключи: ");
        int[] secretKey = new int[k];
        for (int i = 0; i < k; i++) {
            secretKey[i] = scanner.nextInt();
        }

        SplitSecretChinaDecode decoder = new SplitSecretChinaDecode(publicKey, secretKey);
        System.out.println(decoder.getSecret());
    }
}
