package com.imooc.kenny.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * ClassName: MD5Util
 * Function:  MD5
 * Date:      2019/10/16 13:17
 * @author     Kenny
 * version    V1.0
 */
public class MD5Util {
    private static final String SALE = "1a2b3c4d";

    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    public static String inputPassToFormPass(String inputPass) {
        String str = ""+SALE.charAt(0) + SALE.charAt(2) + inputPass + SALE.charAt(5) + SALE.charAt(4);
        return md5(str);
    }

    public static String formPassToDBPass(String formPass, String sale) {
        String str = sale.charAt(0) + sale.charAt(2) + formPass + sale.charAt(5) + sale.charAt(4);
        return md5(str);
    }

    public static String inputPassToDBPass(String input, String saltDB) {
        String formPass = inputPassToFormPass(input);
        String dbPass = formPassToDBPass(formPass, saltDB);
        return dbPass;
    }

    public static void main(String[] args) {
//        String formPass = inputPassToFormPass("123456");
//        System.out.println(formPass);
//        System.out.println(inputPassToDBPass("d3b1294a61a07da9b49b6e22b2cbd7f9", SALE));
        System.out.println(formPassToDBPass("d3b1294a61a07da9b49b6e22b2cbd7f9",SALE));

    }
}
