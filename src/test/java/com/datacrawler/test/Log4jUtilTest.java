package com.datacrawler.test;

import java.lang.reflect.Array;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.datacrawler.common.util.Log4jUtil;

/** * 记录日志的类 主要输出字符串 * * @author Administrator * */
public class Log4jUtilTest {

    public static void main(String[] args) {

        testString();
    }

    public static void testString() {
        // Log4jUtil log4jUtil = Log4jUtil.getLog4jUtil();
        String debug = "debug信息XXXXXXXXXXXX";
        Log4jUtil.debug(debug);

    }

    public static void testArray(Object[] array) {
        Log4jUtil log4jUtil = Log4jUtil.getInstance();
        int length = array.length;
        for (int i = 0; i < length; i++) {
            // System.out.println("第"+i+"号元素的值："+Array.get(array, i));
            log4jUtil.debug("第" + i + "号元素的值：" + Array.get(array, i));
        }
    }

    public static void test2() {
        PropertyConfigurator.configure("config/log4j.properties");
        // Logger logger = Logger.getLogger(Log4jTest1.class);
        Logger logger = Logger.getRootLogger();
        // for (int i = 0; i < 1000; i++) {
        logger.debug("debug");
        logger.info("info");
        logger.error("error");
        // }
    }
}
