package com.we.vpn.api.util;

/**
 * Created by swn on 2017-09-14.
 */
public class EmojiUtil {

    public static String encode(String source) {
        if (source == null || source.length() == 0) {
            return source;
        }
        StringBuilder buf = new StringBuilder();
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (isNotEmojiCharacter(codePoint)) {
                buf.append(codePoint);
            } else {
                buf.append("\\u" + Integer.toHexString(codePoint));
            }
        }
        return buf.toString();
    }

    public static String decode(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '\\' && str.length() > i+5 && str.charAt(i+1) == 'u') {
                sb.append((char)Integer.parseInt(str.substring(i+2, i+6), 16));
                i += 5;
            } else {
                sb.append(str.charAt(i));
            }
        }
        return sb.toString();
    }

    private static boolean isNotEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) ||
                (codePoint == 0x9) ||
                (codePoint == 0xA) ||
                (codePoint == 0xD) ||
                ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) ||
                ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }
}
