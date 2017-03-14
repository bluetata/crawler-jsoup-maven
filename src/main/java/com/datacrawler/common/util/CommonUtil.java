package com.datacrawler.common.util;

import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import yf.utils.html.BaseInfo;

public class CommonUtil {
    
    
    /**
     *  En:to concatenate string; Jp:文字が連結する; Zh：字符串链接方法。
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
        if (isHalfWidth(target)){
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
        if (isHalfWidth(target)){
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
        if (isHalfWidth(target)){
            Pattern p = Pattern.compile("^[A-Za-z0-9]+$");
            Matcher m = p.matcher(target);
            return m.matches();
        }
        return false;
    }
    
    // --------------------------------------------------------------------------
    
}
