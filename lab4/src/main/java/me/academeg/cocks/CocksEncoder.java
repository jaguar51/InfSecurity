package me.academeg.cocks;

import java.io.UnsupportedEncodingException;

/**
 * CocksEncoder Encoder
 *
 * @author Yuriy A. Samsonov <y.samsonov@erpscan.com>
 * @version 1.0
 */
public class CocksEncoder {

    public void encode(String text, int a) {
        String encode = convertText(text);
    }

    public String convertText(String text) {
        StringBuilder builder = new StringBuilder();
        byte[] infoBin = new byte[0];
        try {
            infoBin = text.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        for (byte b : infoBin) {
            System.out.println("c:" + (char) b + "-> "
                    + Integer.toBinaryString(b));
        }
        return builder.toString();
    }

}
