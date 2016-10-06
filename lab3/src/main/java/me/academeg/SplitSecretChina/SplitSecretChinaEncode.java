package me.academeg.SplitSecretChina;

import java.util.Arrays;

@SuppressWarnings("unused")
public class SplitSecretChinaEncode {

    private int publicKey[];
    private int n;
    private int k;

    private int secretKey[];
    private int secretRange[];
    private int secret;

    public SplitSecretChinaEncode(int[] publicKey) {
        this.publicKey = publicKey;
        this.n = publicKey.length;
        this.k = n / 2;
        generateSecretRange();
    }

    public SplitSecretChinaEncode(int[] publicKey, int k) {
        this.publicKey = publicKey;
        this.n = publicKey.length;
        if (k > n) {
            throw new IllegalArgumentException("K should be greater then N");
        }
        this.k = k;
        generateSecretRange();
    }

    public int[] getSecretKey() {
        if (secretKey == null) {
            generateSecretKey();
        }
        return secretKey;
    }

    private void generateSecretKey() {
        secretKey = new int[n];
        for (int i = 0; i < n; i++) {
            secretKey[i] = secret % publicKey[i];
        }
    }

    private void generateSecretRange() {
        secretRange = new int[]{1, 1};
        for (int i = 0; i < k; i++) {
            secretRange[1] *= publicKey[i];
        }
        for (int i = 0; i < k - 1; i++) {
            secretRange[0] *= publicKey[publicKey.length - 1 - i];
        }
        Arrays.sort(secretRange);
        secret = secretRange[0] + (secretRange[1] - secretRange[0]) / 2;
    }

    public int[] getRangeSecret() {
        return secretRange;
    }

    public int getSecret() {
        return secret;
    }

    public void setSecret(int secret) {
        if (!(secretRange[0] < secret && secret < secretRange[1])) {
            throw new IllegalArgumentException("Wrong secret");
        }
        this.secret = secret;
        generateSecretKey();
    }
}
