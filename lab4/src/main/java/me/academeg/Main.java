package me.academeg;

import me.academeg.cocks.CocksPKG;
import me.academeg.cocks.Utils;

public class Main {
    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        int p = 15;
        int q = 19;
        int a = CocksPKG.hashA(103, p * q);
        System.out.println(a);
        System.out.println(CocksPKG.userR(a, p, q));
        System.out.println(Utils.jakobiSymbol(3,7));
    }
}
