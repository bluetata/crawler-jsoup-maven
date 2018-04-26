/**
 * UtilsConstants.java
 *
 * Function：logj4 utility class / log4j工具类
 *
 *   ver     date           author
 * ──────────────────────────────────
 *   1.0     2017/02/22     bluetata
 *
 * Copyright (c) 2017, [https://github.com/] All Rights Reserved.
 */
package com.datacrawler.common.util;

import org.apache.log4j.Logger;

/** 
 * 记录日志的工具类 主要输出字符串 </br>
 * 
 * @version 1.0
 * @since crawler(datasnatch) version(1.0)</br>
 * @author Name  Version(x.0)</br>
 * @author bluetata / dietime1943@gmail.com 2017/03/08
 */
public class Log4jUtil {
    // log4j配置文件
    // private static final String PROPERTIES_FILE_NAME = "log4j.properties";
    private static Log4jUtil instance = null;
    private static Logger logger = null;
    // 利用JCL(commons-logging)组件
    // private static Log jclLogger = null;

    static {
        // PropertyConfigurator.configure(PROPERTIES_FILE_NAME);
        // logger = Logger.getRootLogger();
        // jclLogger = LogFactory.getLog(Log4jUtil.class); // 创建JCL的Log实例
        logger = Logger.getLogger(Log4jUtil.class);
    }

    private Log4jUtil() {
    }

    public static Log4jUtil getInstance() {
        synchronized (Log4jUtil.class) {
            if (instance == null) {
                instance = new Log4jUtil();
            }
        }
        return instance;
    }

    /**
     *      
     * The method <code> debug </code>
     * Log a text debug message.
     * 
     * @param  message Message to log.
     */
    public static void debug(String message) {
        logger.debug(message);
     // jclLogger.debug(message);
    }

    /**
     * 
     * The method <code> debug </code>
     * Log a text debug message with a stack trace. 
     * 
     * @param  message Message to log.
     * @param t  Throwable instance to log.
     */
    public static void debug(String message, Throwable t) {
        logger.debug(message, t);
    }
    
    /**
     * 
     * The method <code> info </code>
     * Log a text info message.
     * 
     * @param  message  Message to log.
     */
    public static void info(String message) {
        logger.info(message);
     // jclLogger.info(message);
    }
    
    /**
     * 
     * The method <code> info </code>
     * Log a text info message with a stack trace.
     * 
     * @param  message Message to log.
     * @param t  Throwable instance to log. 
     */
    public static void info(String message, Throwable t) {
        logger.info(message, t);
    }

    /**
     * 
     * The method <code> warn </code>
     * Log a text warn message.
     * 
     * @param  message  Message to log.
     */
    public static void warn(String message) {
        logger.warn(message);
     // jclLogger.warn(message);
    }
    
    /**
     * 
     * The method <code> warn </code>
     * Log a text warn message with a stack trace.
     * 
     * @param message Message to log.
     * @param t  Throwable instance to log.
     */
    public static void warn(String message, Throwable t) {
        logger.warn(message, t);
    }

    /**
     * 
     * The method <code> error </code>
     * Log a text error message.
     * 
     * @param message  Message to log.
     */
    public static void error(String message) {
        logger.error(message);
     // jclLogger.error(message);
    }

    /**
     * 
     * The method <code> error </code>
     * Log a text error message with a stack trace.
     * 
     * @param  message Message to log.
     * @param t  Throwable instance to log.
     */
    public static void error(String message, Throwable t) {
        logger.error(message, t);
    }
    
    /**
     * 
     * The method <code> fatal </code>
     * Log a text error message.
     * 
     * @param message  Message to log.
     */
    public static void fatal(String message) {
        logger.fatal(message);
     // jclLogger.fatal(message);
    }
    
    /**
     * 
     * The method <code> fatal </code>
     * Log a text fatal message with a stack trace.
     * 
     * @param  message Message to log.
     * @param t  Throwable instance to log.
     */
    public static void fatal(String message, Throwable t) {
        logger.fatal(message, t);
    }
}
