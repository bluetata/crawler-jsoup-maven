/**
 * StringUtil.java
 *
 * Function：Constants class is used by StringUtils 
 *
 *   ver     date           author
 * ──────────────────────────────────
 *   1.0     2017/02/22     bluetata
 *
 * Copyright (c) 2017, [https://github.com/bluetata] All Rights Reserved.
 */
package com.datacrawler.common.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * En: The extension class of String utility<br>
 * Jp: 文字列ユーティリティの拡張クラス<br>
 * Zh: 文字列utility扩展类<br>
 * 
 * in case if parameter is blank and empty method is added.        bluetata 2017/02/14
 * String check method is added.                                   bluetata 2017/03/15
 * 
 * @author bluetata / dietime1943@hotmail.com
 */
public class StringUtil {

    /** En: The constant of NULL; Jp: 空文字列を表す定数です。 Zh: 表空文字的常量 */
    public static final String EMPTY = "";
    
    /** En: The collections are used by generateRandomToken(); Jp: generateRandomToken()で使用する文字集合; Zh被generateRandomToken()使用的文字集合 */
    private static final String RTOKEN_DEFAULT_CHARTABLE = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    /** generateRandomToken() で生成する文字列の長さ */
    private static final int RTOKEN_DEFAULT_LEN = 10;

    /** 全角ひらがなの開始文字。 */
    private static final char HIRAGANA_START = '\u3040';

    private static final char HAN_SPACE = ' ';
    private static final char ZEN_SPACE = '　';
    private static final char HAN_ASCII_START = '!';
    private static final char HAN_ASCII_END = '~';
    private static final char ZEN_ASCII_START = '！';
    private static final char ZEN_ASCII_END = '\uff5e';
    private static final char HAN_YEN_SYMBOL = '\\';
    private static final char ZEN_YEN_SYMBOL = '￥';
    private static final char HAN_DIGIT_START = '0';
    private static final char HAN_DIGIT_END = '9';

    /** 半角カタカナの開始文字。 */
    private static final char HAN_KANA_START = '｡';

    /** 半角カタカナの終了文字。 */
    private static final char HAN_KANA_END = 'ﾟ';

    /** 半角カナ→全角カナ変換用のテーブル。 */
    private static final char[] HAN2ZEN_KANA_TABLE = ("。「」、・ヲ" +
            "ァィゥェォャュョッーアイウエオカキクケコ" +
            "サシスセソタチツテトナニヌネノハヒフヘホ" +
            "マミムメモヤユヨラリルレロワン゛゜").toCharArray();

    /** 全角カナ→半角カナ変換用のテーブル。 */
    private static final char[] ZEN2HAN_KANA_TABLE = (
        "  ｧ ｱ ｨ ｲ ｩ ｳ ｪ ｴ ｫ ｵ ｶ ｶﾞｷ ｷﾞｸ " +
        "ｸﾞｹ ｹﾞｺ ｺﾞｻ ｻﾞｼ ｼﾞｽ ｽﾞｾ ｾﾞｿ ｿﾞﾀ " +
        "ﾀﾞﾁ ﾁﾞｯ ﾂ ﾂﾞﾃ ﾃﾞﾄ ﾄﾞﾅ ﾆ ﾇ ﾈ ﾉ ﾊ " +
        "ﾊﾞﾊﾟﾋ ﾋﾞﾋﾟﾌ ﾌﾞﾌﾟﾍ ﾍﾞﾍﾟﾎ ﾎﾞﾎﾟﾏ ﾐ " +
        "ﾑ ﾒ ﾓ ｬ ﾔ ｭ ﾕ ｮ ﾖ ﾗ ﾘ ﾙ ﾚ ﾛ   ﾜ " +
        "    ｦ ﾝ ｳﾞ            ﾞ ﾟ       " +
        "  ｧ ｱ ｨ ｲ ｩ ｳ ｪ ｴ ｫ ｵ ｶ ｶﾞｷ ｷﾞｸ " +
        "ｸﾞｹ ｹﾞｺ ｺﾞｻ ｻﾞｼ ｼﾞｽ ｽﾞｾ ｾﾞｿ ｿﾞﾀ " +
        "ﾀﾞﾁ ﾁﾞｯ ﾂ ﾂﾞﾃ ﾃﾞﾄ ﾄﾞﾅ ﾆ ﾇ ﾈ ﾉ ﾊ " +
        "ﾊﾞﾊﾟﾋ ﾋﾞﾋﾟﾌ ﾌﾞﾌﾟﾍ ﾍﾞﾍﾟﾎ ﾎﾞﾎﾟﾏ ﾐ " +
        "ﾑ ﾒ ﾓ ｬ ﾔ ｭ ﾕ ｮ ﾖ ﾗ ﾘ ﾙ ﾚ ﾛ   ﾜ " +
        "    ｦ ﾝ ｳﾞ    ﾜﾞ    ｦﾞ･ ｰ       "
        ).toCharArray(); 

    /** 全角カタカナの終了文字。 */
    private static final char WIDE_KATAKANA_END = '\u30ff';
    
    /** デフォルト・エンコーディング Windows-31J */
    public static final String DEFAULT_ENCODING = "Windows-31J";
    
    
    
    /**
     * 字符串是null的情况返回空字符串、以外的情况原参数返回。
     * JP:文字列がnullの場合に空文字を返し、それ以外の場合は引数の値をそのまま返す。
     * 
     * @param str 変換対象文字列
     * @return 文字列がnullの場合に空文字を返し、それ以外の場合は引数の値をそのまま返す
     */
    public static String toEmptyIfNull(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }
    
    
    /**
     * 文字列が空とNUllを検証する。<br>
     * 
     * @param str 処理対象の文字列
     * @return 処理対象の文字列が <code>null</code> または空文字列の場合は <code>true</code>
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
    
    /**
     * 文字列がNUllを検証する。<br>
     * 
     * @param str 処理対象の文字列
     * @return 処理対象の文字列が <code>null</code> の場合は <code>true</code>
     */
    public static boolean isBlank(String str) {
        return str == null;
    }
    
    //  bluetata 2017/03/15 update start---↓--------------------------------------
    
    /**
     * En:to concatenate string; Jp:文字が連結する; Zh：字符串链接方法。
     * 
     * @param strValue
     * @return
     */
    public static String convertStringsToString(String... strValue) {
        StringBuffer stringBuffer = new StringBuffer();
        if (strValue != null) {
            for (String str : strValue) {
                stringBuffer.append(StringUtil.toEmptyIfNull(str));
            }
        }
        return stringBuffer.toString();
    }

    /**
     * 指定文字列が全て半角であることを検証する。
     * 
     * @param target
     *            対象文字列
     * @return 引数のStringを構成する文字が全て半角の場合はtrue、 それ以外の場合はfalseを返す。
     */
    public static boolean isHalfWidth(String target) {
        return (target.length() == target.getBytes().length);
    }

    /**
     * 指定文字列が全て全角であることを検証する。
     * 
     * @param target
     *            対象文字列
     * @return 引数のStringを構成する文字が全て全角の場合はtrue、 それ以外の場合はfalseを返す。
     */
    public static boolean isFullWidth(String target) {
        return (target.length() * 2 == target.getBytes().length);
    }

    /**
     * 指定文字列が半角文字、全角文字が混在していることを検証する。
     * 
     * @param target
     *            対象文字列
     * @return 引数のStringを構成する文字が半角文字、全角文字が混在している場合はtrue、 それ以外の場合はfalseを返す。
     */
    public static boolean isMixWidth(String target) {
        return ((!isHalfWidth(target)) && (!isFullWidth(target)));
    }

    // ----------------------------------------------------------------------------

    /**
     * 指定文字列が半角数字かを検証する。(符号[+/-]を含む)
     * 
     * @param target
     *            対象文字列
     * @return 引数のStringを構成する文字が半角数字の場合はtrue、 それ以外の場合はfalseを返す。
     */
    public static boolean isHalfNumber(String target) {
        if (isHalfWidth(target)) {
            Pattern p = Pattern.compile("^(-|\\+)?\\d+$");
            Matcher m = p.matcher(target);
            return m.matches();
        }
        return false;
    }

    /**
     * 指定文字列が半角数字(0-9)かを検証する。
     * 
     * @param target
     *            対象文字列
     * @return 引数のStringを構成する文字が半角数字の場合はtrue、 それ以外の場合はfalseを返す。
     */
    public static boolean isHalfNumberString(String target) {
        if (isHalfWidth(target)) {
            Pattern p = Pattern.compile("^[0-9]+$");
            Matcher m = p.matcher(target);
            return m.matches();
        }
        return false;
    }

    /**
     * 指定文字列が半角英数字かを検証する。
     * 
     * @param target
     *            対象文字列
     * @return 引数のStringを構成する文字が半角英数字の場合はtrue、 それ以外の場合はfalseを返す。
     */
    public static boolean isHalfAlphabetNumber(String target) {
        if (isHalfWidth(target)) {
            Pattern p = Pattern.compile("^[A-Za-z0-9]+$");
            Matcher m = p.matcher(target);
            return m.matches();
        }
        return false;
    }

    // --------------------------------------------------------------------------
    //  bluetata 2017/03/15 update start--↑--------------------------------------
    
}
