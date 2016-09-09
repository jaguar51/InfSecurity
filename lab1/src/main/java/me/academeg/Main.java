package me.academeg;

import java.util.Scanner;

public class Main {

    private Scanner scanner;

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Выберите действие: ");
            System.out.println("1. Закодировать");
            System.out.println("2. Декодировать");
            System.out.println("3. Выход");
            int action = scanner.nextInt();
            if (action == 1) {
                encode();
            } else if (action == 2) {
                decode();
            } else {
                break;
            }
        }
    }

    private byte[][] inputKey() {
        System.out.print("Введите размерность ключа: ");
        int size = scanner.nextInt();
        System.out.println("Введите ключ в виде матрицы:");
        byte[][] code = new byte[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                code[i][j] = scanner.nextByte();
            }
        }
        return code;
    }

    private void encode() {
        byte[][] key = inputKey();
        System.out.println("Введите текст:");
        scanner.nextLine();
        String txt = scanner.nextLine();

        StencilCode stencilCode = new StencilCode();
        String encode = stencilCode.encode(key, txt);
        System.out.println(encode);
    }

    private void decode() {
        byte[][] key = inputKey();
        System.out.println("Введите код:");
        scanner.nextLine();
        String txt = scanner.nextLine();

        StencilCode stencilCode = new StencilCode();
        String decode = stencilCode.decode(key, txt);
        System.out.println(decode);
    }
}
