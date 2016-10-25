package me.academeg;

import me.academeg.cocks.CocksDecoder;
import me.academeg.cocks.CocksEncoder;
import me.academeg.cocks.CocksPKG;

import java.util.Scanner;

/**
 * Main
 *
 * @author Yuriy A. Samsonov <y.samsonov@erpscan.com>
 * @version 1.0
 */
public class Main {

    private Scanner scanner;

    public static void main(String[] args) {
        new Main().runTest();
    }

    private void runTest() {
        int p = 7;
        int q = 19;
        int n = p * q;

        int id = 54321;

        int a = CocksPKG.hashA(id, n);

        CocksEncoder encoder = new CocksEncoder();
        String stockText = "Мама мыла раму. Маша ела кашу.";
        int[][] encode = encoder.encode(stockText, a, n);

        CocksDecoder decoder = new CocksDecoder();
        int r = CocksPKG.userR(a, p, q);
        String decode = decoder.decoder(encode, r, a, n);
        System.out.println(decode);
    }

    private void run() {
        scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1. Получить a по ID");
            System.out.println("2. Закодировать");
            System.out.println("3. Получить r для декодирования");
            System.out.println("4. Декодировать");
            System.out.println("5. Выход");
            int action = scanner.nextInt();
            if (action == 1) {
                getAById();
            } else if (action == 2) {
                encodeText();
            } else if (action == 3) {
                getRForDecoding();
            } else if (action == 4) {
                decodeText();
            } else {
                break;
            }
        }
    }

    private void getAById() {
        System.out.print("Введите id: ");
        int id = scanner.nextInt();
        System.out.println("Введите p и q:");
        int p = scanner.nextInt();
        int q = scanner.nextInt();
        int n = p * q;
        System.out.println(CocksPKG.hashA(id, n));
    }

    private void encodeText() {
        System.out.println("Введите сообщешие:");
        scanner.nextLine();
        String text = scanner.nextLine();
        CocksEncoder encoder = new CocksEncoder();
        System.out.print("Введите a: ");
        int a = scanner.nextInt();
        System.out.print("Введите n: ");
        int n = scanner.nextInt();
        int[][] encode = encoder.encode(text, a, n);
        System.out.println("Закодировано:");
        System.out.println(encode.length);
        for (int i = 0; i < encode.length; i++) {
            System.out.println(encode[i][0] + " " + encode[i][1]);
        }
        System.out.println();
    }

    private void getRForDecoding() {
        System.out.print("Введите a: ");
        int a = scanner.nextInt();

        System.out.print("Введите p: ");
        int p = scanner.nextInt();

        System.out.print("Введите q: ");
        int q = scanner.nextInt();

        System.out.println("R: " + CocksPKG.userR(a, p, q));
    }

    private void decodeText() {
        System.out.print("Введите размер последовательности: ");
        int length = scanner.nextInt();
        System.out.println("Введите последовательность: ");
        int[][] code = new int[length][2];
        for (int i = 0; i < length; i++) {
            code[i][0] = scanner.nextInt();
            code[i][1] = scanner.nextInt();
        }

        System.out.print("Введите r: ");
        int r = scanner.nextInt();

        System.out.print("Введите a: ");
        int a = scanner.nextInt();

        System.out.print("Введите n: ");
        int n = scanner.nextInt();

        System.out.println("Декодировано:");
        CocksDecoder decoder = new CocksDecoder();
        System.out.println(decoder.decoder(code, r, a, n));
    }
}
