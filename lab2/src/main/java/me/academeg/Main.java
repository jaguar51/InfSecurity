package me.academeg;

import me.academeg.backpack.BackpackDecoder;
import me.academeg.backpack.BackpackEncoder;

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
            System.out.println("1. Генерировать публичный ключ");
            System.out.println("2. Закодировать");
            System.out.println("3. Декодировать");
            System.out.println("4. Выход");
            int action = scanner.nextInt();
            if (action == 1) {
                generatePublicKey();
            } else if (action == 2) {
                encode();
            } else if (action == 3) {
                decode();
            } else {
                break;
            }
        }
    }

    private void generatePublicKey() {
        System.out.print("Введите длину ключа: ");
        int size = scanner.nextInt();
        System.out.println("Введите ключ, в виде супервозрастающей последовательности:");
        int[] key = new int[size];
        for (int i = 0; i < size; i++) {
            key[i] = scanner.nextInt();
        }
        BackpackDecoder code = new BackpackDecoder(key);
        for (int el : code.getPublicKey()) {
            System.out.printf(el + " ");
        }
        System.out.println();
    }


    private void encode() {
        System.out.print("Введите длину ключа: ");
        int size = scanner.nextInt();
        System.out.println("Введите публичный ключ:");
        int[] key = new int[size];
        for (int i = 0; i < size; i++) {
            key[i] = scanner.nextInt();
        }
        System.out.println("Введите текст:");
        scanner.nextLine();
        String text = scanner.nextLine();
        BackpackEncoder coder = new BackpackEncoder(key);
        for (int el : coder.encode(text)) {
            System.out.printf(el + " ");
        }
        System.out.println();
    }

    private void decode() {
        System.out.print("Введите длину ключа: ");
        int size = scanner.nextInt();
        System.out.println("Введите ключ:");
        int[] key = new int[size];
        for (int i = 0; i < size; i++) {
            key[i] = scanner.nextInt();
        }
        System.out.println("Введите зашифрованное сообщение:");
        scanner.nextLine();
        String text = scanner.nextLine();
        String[] lines = text.split(" ");
        int[] code = new int[lines.length];
        for (int i = 0; i < lines.length; i++) {
            code[i] = Integer.parseInt(lines[i]);
        }
        BackpackDecoder coder = new BackpackDecoder(key);
        System.out.println(coder.decode(code));
        System.out.println();
    }
}
