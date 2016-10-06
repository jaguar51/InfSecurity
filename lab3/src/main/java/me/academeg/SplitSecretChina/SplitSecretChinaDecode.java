package me.academeg.SplitSecretChina;

@SuppressWarnings("unused")
public class SplitSecretChinaDecode {

    private int[] publicKey;
    private int[] secretKey;
    private int countSecret;

    public SplitSecretChinaDecode(int[] publicKey, int[] secretKey) {
        this.publicKey = publicKey;
        this.secretKey = secretKey;
        if (publicKey.length != secretKey.length) {
            throw new IllegalArgumentException("Different keys length");
        }
        countSecret = publicKey.length;
    }

    public int getSecret() {
        int[] f = generateAdditionalArr();
        int secret = 0;
        int multiplyPublic = getMultiplyPublicWithout(-1);
        for (int i = 0; i < countSecret; i++) {
            secret = secret + (secretKey[i] * getMultiplyPublicWithout(i) * f[i]);
        }
        return secret % multiplyPublic;
    }

    private int[] generateAdditionalArr() {
        int[] arr = new int[countSecret];
        for (int i = 0; i < countSecret; i++) {
            arr[i] = multiInverse(getMultiplyPublicWithout(i), publicKey[i]);
        }
        return arr;
    }

    private int getMultiplyPublicWithout(int withoutIndex) {
        int res = 1;
        for (int i = 0; i < countSecret; i++) {
            if (i != withoutIndex) {
                res *= publicKey[i];
            }
        }
        return res;
    }

    private int multiInverse(long aa, long bb) {
        long a = aa;
        long b = bb;
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
        return (int) ((lastX % bb + bb) % bb);
    }
}
