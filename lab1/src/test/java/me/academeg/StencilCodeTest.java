package me.academeg;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class StencilCodeTest {

    @Test
    public void test1() throws Exception {
        byte[][] key = {
                {0, 0, 0, 0, 0},
                {0, 0, 1, 1, 0},
                {0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0},
                {0, 1, 0, 0, 0},
        };
        String txt = "Маша ела кашу.!?";

        StencilCode stencilCode = new StencilCode();
        String encode = stencilCode.encode(key, txt);
        String decode = stencilCode.decode(key, encode);
        assertEquals(txt, decode);
    }

    @Test
    public void test2() throws Exception {
        byte[][] key = {
                {1, 1, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
        };
        String txt = "Маша ела кашу.!?";

        StencilCode stencilCode = new StencilCode();
        String encode = stencilCode.encode(key, txt);
        String decode = stencilCode.decode(key, encode);
        assertEquals(txt, decode);
    }

    @Test
    public void test3() throws Exception {
        byte[][] key = {
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
        };
        String txt = "Маша ела кашу.!?";

        StencilCode stencilCode = new StencilCode();
        String encode = stencilCode.encode(key, txt);
        String decode = stencilCode.decode(key, encode);
        assertEquals(txt, decode);
    }
}
