package me.academeg;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int privateKey[] = new int[]{1, 2, 4, 8, 16, 32, 64, 128, 256, 512};
//        int privateKey[] = new int[]{2, 7, 11, 21, 42, 89, 180, 354};
        BackpackCode code = new BackpackCode(privateKey);
//        System.out.println(Arrays.toString(code.getPublicKey()));
        int[] c = code.encode("Мама мыла раму 1234567890 !@#$%^&*()");
        System.out.println(Arrays.toString(c));
        System.out.println(code.decode(c));
    }

}
