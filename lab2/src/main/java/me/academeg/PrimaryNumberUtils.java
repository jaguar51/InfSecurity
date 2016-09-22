package me.academeg;

public class PrimaryNumberUtils {

    public static int getPrimeNumber(int num) {
        for (int i = num + 1; ; i++) {
            if (isPrime(i)) {
                return i;
            }
        }
    }

    public static boolean isPrime(int num) {
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
