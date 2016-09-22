package me.academeg;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
//        int privateKey[] = new int[]{1, 2, 4, 8, 16, 32, 64, 128, 256, 512};
        int privateKey[] = new int[]{2, 7, 11, 21, 42, 89, 180, 354};
        BackpackCode code = new BackpackCode(privateKey);
        System.out.println(Arrays.toString(code.getPublicKey()));
        int[] c = code.encode("a");
        System.out.println(Arrays.toString(c));
        System.out.println(code.decode(c));

//        solve(588, 881);
    }

    public static void solve(long a, long b) {
        b=-b;
        long x = 0, y = 1, lastx = 1, lasty = 0, temp;
        while (b != 0) {
            long q = a / b;
            long r = a % b;

            a = b;
            b = -r;

            temp = x;
            x = lastx - q * x;
            lastx = temp;

            temp = y;
            y = lasty - q * y;
            lasty = temp;
        }
        System.out.println("Roots  x : " + lastx + " y :" + lasty);
    }
}
