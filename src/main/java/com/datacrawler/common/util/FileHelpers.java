/**
 * UtilsConstants.java
 *
 * Function：file utility class / ファイルユティリティークラス / file工具类
 *
 *   ver     date           author
 * ──────────────────────────────────
 *   1.0     2017/02/22     bluetata
 *
 * Copyright (c) 2017, [https://github.com/] All Rights Reserved.
 */
package com.datacrawler.common.util;

import java.io.File;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.UUID;

import com.datacrawler.consts.SystemConstants;

/**
 * Helpers class, which contains static methods helper methods like loading the
 * given property file etc.
 */
public final class FileHelpers {

    // 系统配置文件路径
    final static String configPath = System.getProperty(SystemConstants.CONFIG_PATH_KEY);
    // final static String configPath ="C://crawler-jsoup-maven//lib//conf//";

    // class path
    private static final String SYSTEM_CLASS_PATH_PROPERTY_NAME = "java.class.path";

    // path separator
    private static final String SYSTEM_PATH_SEP_PROPERTY_NAME = "path.separator";

    // 检索目录
    private static String[] m_classPathSearchDirs = null;

    // singleton
    private FileHelpers() {
    }

    
//     bluetata 2017/03/14 del start ---------------------------------------------------------------------
//     that methods is moved to PropertyReader class
//    /**
//     * 
//     * The method <code> getProperties </code> . Loads the given property file
//     * by searching the CLASSPATH or java.class.path system property value and
//     * returns the Properties object.
//     * 
//     * @param propertyFileName
//     *            Name of the property file.
//     * @return Returns Properties object containing the contents of the
//     *         specified Properties file.
//     * @throws java.io.FileNotFoundException
//     *             Thrown if the given property file could not found in the
//     *             CLASSPATH.
//     * 
//     */
//    public static Properties getProperties(String propertyFileName) throws java.io.FileNotFoundException {
//
//        InputStream is = null;
//        try {
//            Log4jUtil.info("configPath=" + configPath);
//            is = new FileInputStream(FileHelpers.getFile(propertyFileName));
//
//            if (is == null) {
//                // 文件不存在
//                throw new FileNotFoundException(propertyFileName + " not found");
//            }
//
//            // load properties
//            Properties props = new Properties();
//            // 从文件流中加载properties属性值
//            props.load(is);
//            return props;
//
//        } catch (Exception ignore) {
//            ignore.printStackTrace();
//            throw new java.io.FileNotFoundException(propertyFileName + " not found");
//        } finally {
//            if (is != null) {
//                try {
//                    // 关闭文件流
//                    is.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    /**
//     * 
//     * The method <code> getProperties </code> . Loads the given property file
//     * by searching the CLASSPATH or java.class.path system property value and
//     * returns the Properties object.
//     * 
//     * @param propertyFileName
//     *            Name of the property file.
//     * @param onNotFound
//     *            Properties to return if the named properties file is not
//     *            found.
//     * @return Returns Properties object containing the contents of the
//     *         specified Properties file if found, or the onNotFound value if
//     *         not found.
//     * 
//     */
//    public static Properties getProperties(String propertyFileName, Properties onNotFound) {
//        try {
//            return getProperties(propertyFileName);
//        } catch (java.io.FileNotFoundException fe) {
//            Log4jUtil.warn("Properties file not found: " + propertyFileName);
//            return onNotFound;
//        }
//    }
//  bluetata 2017/03/14 del end ------------------------------------------------------------------------

    /**
     * 
     * The method <code> getIntegerProperty </code> .
     * 从配置文件中取得指定键名的属性值的整数型，不存在的场合，返回默认值
     * 
     * @param p
     *            配置文件properties资源
     * @param name
     *            键名
     * @param defaultValue
     *            默认值
     * @return int 返回属性值的整数型
     */
    public static int getIntegerProperty(Properties p, String name, int defaultValue) {
        String l = p.getProperty(name);
        return l == null ? defaultValue : Integer.valueOf(l).intValue();
    }

    /**
     * 
     * The method <code> getIntegerProperty </code> .
     * 从配置文件中取得指定键名的属性值的字符串型，不存在的场合，返回默认值
     * 
     * @param p
     *            配置文件properties资源
     * @param name
     *            键名
     * @param defaultValue
     *            默认值
     * @return String 返回属性值的字符串型
     */
    public static String getStringProperty(Properties p, String name, String defaultValue) {
        String propertyValue = p.getProperty(name);
        return propertyValue == null ? defaultValue : propertyValue;
    }

    /**
     * 
     * The method <code> getBooleanProperty </code> .
     * 从配置文件中取得指定键名的属性值的布尔类型，不存在的场合，返回默认值
     * 
     * @param p
     *            配置文件properties资源
     * @param name
     *            键名
     * @param defaultValue
     *            默认值
     * @return boolean 返回属性值的布尔类型
     */
    public static boolean getBooleanProperty(Properties p, String name, boolean defaultValue) {
        String propertyValue = p.getProperty(name);
        return propertyValue == null ? defaultValue : new Boolean(propertyValue).booleanValue();
    }

    /**
     * 
     * The method <code> getFile </code> . 在系统配置文件路径中查找指定文件
     * 
     * @param fileName
     *            待查找的文件名
     * @return File 返回指定文件名的文件句柄
     */
    public static File getFile(String fileName) {
        File file = new File(configPath + File.separator + fileName); // separator 与系统有关的默认名称分隔符,占一个字符
        System.out.println(configPath + File.separator + fileName);
        return file;
    }

    /**
     * 
     * The method <code> getPath </code> .
     * 在检索目录中查找指定文件名的文件，如果存在可读取的指定文件，则返回文件的绝对路径
     * 
     * @param fileName
     *            文件名
     * @return String 返回指定文件的相对路径
     * @throws java.io.FileNotFoundException
     *             指定文件不存在的场合
     */
    public static String getPath(String fileName) throws java.io.FileNotFoundException {
        return getPath(fileName, true);
    }

    /**
     * 
     * The method <code> getPath </code> . 在检索目录中查找指定文件名的文件，如果存在文件，则返回文件的绝对路径
     * 
     * @param fileName
     *            文件名
     * @param isReadOnly
     *            是否只读
     * @return String 返回指定文件的相对路径
     * @throws java.io.FileNotFoundException
     */
    public static String getPath(String fileName, boolean isReadOnly) throws java.io.FileNotFoundException {
        createClassPathSearchDirs();

        try {
            // 遍历检索目录
            for (int i = 0; i < m_classPathSearchDirs.length; i++) {

                File file = null;
                try {
                    // 在当前检索目录中查找指定文件名的文件
                    file = new File(m_classPathSearchDirs[i], fileName);
                    if (isReadOnly) {
                        // 可读的场合
                        if (file.canRead()) {
                            // 返回文件的相对路径
                            return file.getAbsolutePath();
                        }
                    } else {
                        // 可读可写的场合
                        if ((file.canRead()) && (file.canWrite())) {
                            return file.getAbsolutePath();
                        }
                    }
                } catch (Exception ignore) {
                    ;
                }
            }
        } catch (Exception ignore) {
            ;
        }

        // 在所有检索目录中都不存在指定文件的场合
        throw new java.io.FileNotFoundException(fileName + " not found");
    }

    /**
     * 
     * The method <code> createClassPathSearchDirs </code> . 构造文件检索目录
     * 
     * @return void
     */
    private static void createClassPathSearchDirs() {
        if (m_classPathSearchDirs == null) {
            synchronized (FileHelpers.class) {

                if (m_classPathSearchDirs == null) { // double check
                    // class path路径下的检索目录
                    String cp = System.getProperty(SYSTEM_CLASS_PATH_PROPERTY_NAME);
                    String delim = System.getProperty(SYSTEM_PATH_SEP_PROPERTY_NAME);
                    if (delim == null) {
                        delim = ";:";
                    }

                    // divide to tokens
                    StringTokenizer st = new StringTokenizer(cp, delim);

                    m_classPathSearchDirs = new String[st.countTokens()];
                    for (int i = 0; i < m_classPathSearchDirs.length; i++) {
                        m_classPathSearchDirs[i] = st.nextToken();
                    }
                }
            }
        }
    }

    /**
     * 
     * The method <code> createUUIDFileName </code> . 生成具有唯一性的UUID文件名称（文件名 =
     * UUID）
     * 
     * @return String
     */
    public static String createUUIDFileName() {
        // 生成通用惟一标识符 (UUID)
        UUID uuid = UUID.randomUUID();

        return uuid.toString();
    }

}
