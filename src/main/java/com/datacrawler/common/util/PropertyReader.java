/**
 * UtilsConstants.java
 *
 * Function：parse properties file utility class / 解析properties文件工具类
 *
 *   ver     date           author
 * ──────────────────────────────────
 *   1.0     2017/02/22     bluetata
 *
 * Copyright (c) 2017, [https://github.com/] All Rights Reserved.
 */
package com.datacrawler.common.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;

import com.datacrawler.consts.SystemConstants;
import com.datacrawler.consts.UtilsConstants;

/**
 * 
 * 功能描述:动态读取配置文件来加载属性
 * 
 * @author bluetata / dietime1943@hotmail.com 2017/03/04
 * @author Name Date(YYYY/MM/dd)
 * @since crawler(datasnatch) version(1.0)
 */
public class PropertyReader {

    // 系统配置文件路径
    final static String configPath = System.getProperty(SystemConstants.CONFIG_PATH_KEY);

    private static Hashtable<String, Properties> pptContainer = new Hashtable<String, Properties>();

    
    
    /**
     * 
     * 方法用途和描述: 获得属性
     * 
     * @param propertyFilePath
     *            属性文件(包括类路径)
     * @param key
     *            属性键
     * @return 属性值
     * @author bluetata / dietime1943@hotmail.com 2017/03/04
     * @author Name Date(YYYY/MM/dd)
     * @since datasnatch(crawler) version(1.0)
     */
    public final static String getValue(String propertyFilePath, String key) {
        Properties ppts = _getProperties(propertyFilePath);
        return ppts == null ? null : ppts.getProperty(key);
    }

    /**
     * 
     * 方法用途和描述: 获得属性文件中Key所对应的值
     * 
     * @param propertyFilePath
     *            属性文件路径(包括类路径或文件系统中文件路径)
     * @param key
     *            属性的键
     * @param isAbsolutePath
     *            是否为绝对路径:true|false〔即是本地文件系统路径，比如：C:/test.propreties〕<br>
     *            <br>
     *            <b>注：</b>不能通过类路径来获取到属性文件，而只知道属性文件的文件系统路径，即文件系统地址则用此方法来获取其中的Key所对应的Value
     * @return key的属性值
     * @author bluetata / dietime1943@hotmail.com 2017/03/04
     * @author Name Date(YYYY/MM/dd)
     * @since datasnatch(crawler) version(1.0)
     */
    public final static String getValue(String propertyFilePath, String key, boolean isAbsolutePath) {
        if (isAbsolutePath) {
            Properties ppts = getPropertiesByFs(propertyFilePath);
            return ppts == null ? null : ppts.getProperty(key);
        }
        return getValue(propertyFilePath, key);
    }

    /**
     * 
     * 方法用途和描述: 获得属性文件的属性
     * 
     * @param propertyFilePath
     *            属性文件(包括类路径)
     * @return 属性
     * @author bluetata / dietime1943@hotmail.com 2017/03/04
     * @author Name Date(YYYY/MM/dd)
     * @since datasnatch(crawler) version(1.0)
     * 
     * @deprecated 新的替代方法{@link} getProperties()      2017/03/15
     * 
     */
    @Deprecated
    public final static Properties _getProperties(String propertyFilePath) {
        if (propertyFilePath == null) {
            Log4jUtil.error("propertyFilePath is null!");
            return null;
        }
        Properties ppts = pptContainer.get(propertyFilePath);
        if (ppts == null) {
            ppts = loadPropertyFile(propertyFilePath);
            if (ppts != null) {
                pptContainer.put(propertyFilePath, ppts);
            }
        }
        return ppts;
    }

    /**
     * 
     * 方法用途和描述: 获得属性文件的属性
     * 
     * @param propertyFilePath
     *            属性文件路径(包括类路径及文件系统路径)
     * @return 属性文件对象 Properties
     * @author bluetata / dietime1943@hotmail.com 2017/03/04
     * @author Name Date(YYYY/MM/dd)
     * @since datasnatch(crawler) version(1.0)
     */
    public final static Properties getPropertiesByFs(String propertyFilePath) {
        if (propertyFilePath == null) {
            Log4jUtil.error("propertyFilePath is null!");
            return null;
        }
        Properties ppts = pptContainer.get(propertyFilePath);
        if (ppts == null) {
            ppts = loadPropertyFileByFileSystem(propertyFilePath);
            if (ppts != null) {
                pptContainer.put(propertyFilePath, ppts);
            }
        }
        return ppts;
    }

    /**
     * 
     * 方法用途和描述: 加载属性
     * 
     * @param propertyFilePath
     *            属性文件(包括类路径)
     * @return 属性
     * @author bluetata / dietime1943@hotmail.com 2017/03/04
     * @author Name Date(YYYY/MM/dd)
     * @since datasnatch(crawler) version(1.0)
     */
    private static Properties loadPropertyFile(String propertyFilePath) {
        java.io.InputStream is = PropertyReader.class.getResourceAsStream(propertyFilePath);
        if (is == null) {
            return loadPropertyFileByFileSystem(propertyFilePath);
        }
        Properties ppts = new Properties();
        try {
            ppts.load(is);
            return ppts;
        } catch (Exception e) {
            Log4jUtil.debug("加载属性文件出错:" + propertyFilePath, e);
            return null;
        }
    }

    /**
     * 
     * 方法用途和描述: 从文件系统加载属性文件
     * 
     * @param propertyFilePath
     *            属性文件(文件系统的文件路径)
     * @return 属性
     * @author bluetata / dietime1943@hotmail.com 2017/03/04
     * @author Name Date(YYYY/MM/dd)
     * @since datasnatch(crawler) version(1.0)
     */
    private static Properties loadPropertyFileByFileSystem(final String propertyFilePath) {
        try {
            Properties ppts = new Properties();
            ppts.load(new java.io.FileInputStream(propertyFilePath));
            return ppts;
        } catch (java.io.FileNotFoundException e) {
            Log4jUtil.error("FileInputStream(\"" + propertyFilePath + "\")! FileNotFoundException: " + e);
            return null;
        } catch (java.io.IOException e) {
            Log4jUtil.error("Properties.load(InputStream)! IOException: " + e);
            return null;
        }
    }

    /**
     * 
     * 方法用途和描述: 对存在的属性文件中添加键值对并保存
     * 
     * @param propertyFilePath
     *            属性文件的路径(包括类路径及文件系统路径)
     * @param htKeyValue
     *            键值对Hashtable
     * @return
     * @author bluetata / dietime1943@hotmail.com 2017/03/04
     * @author Name Date(YYYY/MM/dd)
     * @since datasnatch(crawler) version(1.0)
     */
    public final static boolean setValueAndStore(String propertyFilePath,
            java.util.Hashtable<String, String> htKeyValue) {
        return setValueAndStore(propertyFilePath, htKeyValue, null);
    }

    /**
     * 
     * 方法用途和描述: 对存在的属性文件中添加键值对并保存
     * 
     * @param propertyFilePath
     *            属性文件的路径(包括类路径及文件系统路径)
     * @param htKeyValue
     *            键值对Hashtable
     * @param storeMsg
     *            保存时添加的附加信息（注释）
     * @return
     * @author bluetata / dietime1943@hotmail.com 2017/03/04
     * @author Name Date(YYYY/MM/dd)
     * @since datasnatch(crawler) version(1.0)
     */
    public final static boolean setValueAndStore(String propertyFilePath,
            java.util.Hashtable<String, String> htKeyValue, String storeMsg) {
        Properties ppts = _getProperties(propertyFilePath);

        if (ppts == null || htKeyValue == null) {
            return false;
        }
        ppts.putAll(htKeyValue);
        java.io.OutputStream stream = null;
        try {
            stream = new java.io.FileOutputStream(propertyFilePath);
        } catch (FileNotFoundException e) {
            Log4jUtil.debug("propertyFilePath = " + propertyFilePath);
            String path = PropertyReader.class.getResource(propertyFilePath).getPath();
            Log4jUtil.debug("~~~~~~~~path~~~XXX~~~~~" + path);
            try {
                stream = new java.io.FileOutputStream(path);
            } catch (FileNotFoundException e1) {
                Log4jUtil.error("FileNotFoundException! path=" + propertyFilePath);
                return false;
            }
        }

        if (stream == null) {
            return false;
        }

        try {
            ppts.store(stream, storeMsg != null ? storeMsg : "set value and store.");
            return true;
        } catch (java.io.IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 
     * 方法用途和描述: 创建属性文件
     * 
     * @param propertyFilePath
     *            要存储属性文件的路径
     * @param htKeyValue
     *            属性文件中的键值对Hashtable
     * @return
     * @author bluetata / dietime1943@hotmail.com 2017/03/04
     * @author Name Date(YYYY/MM/dd)
     * @since datasnatch(crawler) version(1.0)
     */
    public final static boolean createPropertiesFile(String propertyFilePath,
            java.util.Hashtable<String, String> htKeyValue) {
        java.io.File file = new java.io.File(propertyFilePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        }
        return setValueAndStore(propertyFilePath, htKeyValue, "create properties file:" + file.getName());
    }

    /**
     * 
     * 方法用途和描述:设置属性值
     * 
     * @param propertyFilePath
     *            属性文件(包括类路径)
     * @param key
     *            属性键
     * @param value
     *            属性值
     * @return
     * @author bluetata / dietime1943@hotmail.com 2017/03/04
     * @author Name Date(YYYY/MM/dd)
     * @since datasnatch(crawler) version(1.0)
     */
    public final static boolean setValue(String propertyFilePath, String key, String value) {
        Properties ppts = _getProperties(propertyFilePath);
        if (ppts == null) {
            return false;
        }
        ppts.put(key, value);
        return true;
    }

    /**
     * 
     * 方法用途和描述: 保存属性文件对象
     * 
     * @param properties
     *            属性文件对象
     * @param propertyFilePath
     *            要保存的路径
     * @param msg
     *            保存时添加的附加信息（注释）
     * @author bluetata / dietime1943@hotmail.com 2017/03/04
     * @author Name Date(YYYY/MM/dd)
     * @since datasnatch(crawler) version(1.0)
     */
    public final static void store(Properties properties, String propertyFilePath, String msg) {
        try {
            java.io.OutputStream stream = new java.io.FileOutputStream(propertyFilePath);
            properties.store(stream, msg);
        } catch (java.io.FileNotFoundException e) {
            Log4jUtil.error("FileOutputStream(" + propertyFilePath + ")! FileNotFoundException: " + e);
        } catch (java.io.IOException e) {
            Log4jUtil.error("store(stream, msg)! IOException: " + e);
            e.printStackTrace();
        }
    }

    /**
     * 
     * 方法用途和描述: 删除属性值
     * 
     * @param propertyFilePath
     *            属性文件(包括类路径)
     * @param key
     *            属性键
     * @return
     * @author bluetata / dietime1943@hotmail.com 2017/03/04
     * @author Name Date(YYYY/MM/dd)
     * @since datasnatch(crawler) version(1.0)
     */
    public final static String removeValue(String propertyFilePath, String key) {

        Properties ppts = _getProperties(propertyFilePath);
        if (ppts == null) {
            return null;
        }
        return (String) ppts.remove(key);
    }

    /**
     * 
     * 方法用途和描述: 删除属性文件中的Key数组所对应的键值对
     * 
     * @param propertyFilePath
     *            属性文件路径(包括类路径及文件系统路径)
     * @param key
     *            key数组
     * @return 属性文件对象
     * @author bluetata / dietime1943@hotmail.com 2017/03/04
     * @author Name Date(YYYY/MM/dd)
     * @since datasnatch(crawler) version(1.0)
     */
    public final static Properties removeValue(String propertyFilePath, String[] key) {
        if (key == null) {
            Log4jUtil.error("key[] is null!");
            return null;
        }
        Properties ppts = _getProperties(propertyFilePath);
        if (ppts == null) {
            return null;
        }
        for (String strKey : key) {
            ppts.remove(strKey);
        }
        return ppts;
    }

    /**
     * 
     * 方法用途和描述:删除属性文件中的Key数组所对应的键值对，并将属性文件对象持久化（即保存）
     * 
     * 
     * @param propertyFilePath
     *            属性文件路径(包括类路径及文件系统路径)
     * @param key
     *            属性文件中的key数组
     * @return 成功与否（true|false）
     * @author bluetata / dietime1943@hotmail.com 2017/03/04
     * @author Name Date(YYYY/MM/dd)
     * @since datasnatch(crawler) version(1.0)
     */
    public final static boolean removeValueAndStore(String propertyFilePath, String[] key) {
        Properties ppts = removeValue(propertyFilePath, key);
        if (ppts == null) {
            return false;
        }
        store(ppts, propertyFilePath, "batch remove key value!");
        return true;
    }

    /**
     * 
     * 方法用途和描述: 更新指定路径的属性文件中的键所对应的值
     * 
     * @param propertyFilePath
     *            属性文件路径(包括类路径及文件系统路径)
     * @param key
     *            属性文件中的key
     * @param newValue
     *            要更新的新值
     * @return 成功与否（true|false）
     * @author bluetata / dietime1943@hotmail.com 2017/03/04
     * @author Name Date(YYYY/MM/dd)
     * @since datasnatch(crawler) version(1.0)
     */
    public final static boolean updateValue(String propertyFilePath, String key, String newValue) {
        if (key == null || newValue == null) {
            Log4jUtil.error("key or newValue is null!");
            return false;
        }
        java.util.Hashtable<String, String> ht = new java.util.Hashtable<String, String>();
        ht.put(key, newValue);
        return setValueAndStore(propertyFilePath, ht, "update " + key + "'s value!");
    }

    /**
     * 
     * 方法用途和描述: 批量更新指定路径的属性文件中的键所对应的值
     * 
     * @param propertyFilePath
     *            属性文件路径(包括类路径及文件系统路径)
     * @param htKeyValue
     *            要更新的键值对Hashtable
     * @return 成功与否（true|false）
     * @author bluetata / dietime1943@hotmail.com 2017/03/04
     * @author Name Date(YYYY/MM/dd)
     * @since datasnatch(crawler) version(1.0)
     */
    public final static boolean batchUpdateValue(String propertyFilePath,
            java.util.Hashtable<String, String> htKeyValue) {
        if (propertyFilePath == null || htKeyValue == null) {
            return false;
        }
        return setValueAndStore(propertyFilePath, htKeyValue, "batch update key value!");
    }

    /**
     * 
     * 方法用途和描述: 移除加载的属性文件
     * 
     * @param propertyFilePath
     *            属性文件(包括类路径)
     * @return
     * @author bluetata / dietime1943@hotmail.com 2017/03/04
     * @author Name Date(YYYY/MM/dd)
     * @since datasnatch(crawler) version(1.0)
     */
    public final static Properties removePropertyFile(String propertyFilePath) {

        return pptContainer.remove(propertyFilePath);
    }

    /**
     * 
     * 方法用途和描述: 重新加载某个Property文件
     * 
     * @param propertyFilePath
     *            要重新加载的Property文件，如果当前内存中没有的话则加载，否则替换
     * @author bluetata / dietime1943@hotmail.com 2017/03/04
     * @author Name Date(YYYY/MM/dd)
     * @since datasnatch(crawler) version(1.0)
     */
    public final static void reloadPropertyFile(String propertyFilePath) {
        pptContainer.remove(propertyFilePath);
        loadPropertyFile(propertyFilePath);
    }

    /**
     * 
     * 方法用途和描述: 获得属性文件的路径
     * 
     * @param pkg
     *            包名
     * @param propertyFileName
     *            属性文件名
     * @return
     * @author bluetata / dietime1943@hotmail.com 2017/03/04
     * @author Name Date(YYYY/MM/dd)
     * @since datasnatch(crawler) version(1.0)
     */
    public final static String getPpropertyFilePath(String pkg, String propertyFileName) {

        pkg = pkg == null ? "" : pkg.replaceAll("\\.", "/");
        propertyFileName = propertyFileName.endsWith(".properties") ? propertyFileName
                : (propertyFileName + ".properties");
        return "/" + pkg + "/" + propertyFileName;
    }

    public static void main(String[] args) {
        String path = "/config/jdbc.properties";
        String v = PropertyReader.getValue(path, "jdbc.driverClassName");
        Log4jUtil.info("value0 = " + v);

        Hashtable<String, String> ht = new Hashtable<String, String>();
        ht.put("name", "dengcd");
        PropertyReader.setValueAndStore(path, ht);
        String v_ = PropertyReader.getValue(path, "name");
        Log4jUtil.info("value1 = " + v_);
        PropertyReader.reloadPropertyFile(path);
        String v2_ = PropertyReader.getValue(path, "name");
        Log4jUtil.info("value2 = " + v2_);
    }

    /**
     * 
     * The method <code> getProperties </code> Loads the given property file by
     * searching the CLASSPATH or java.class.path system property value and
     * returns the Properties object.
     * 
     * @param propertyFileName
     *            Name of the property file.
     * @return Returns Properties object containing the contents of the
     *         specified Properties file.
     * @throws java.io.FileNotFoundException
     *             Thrown if the given property file could not found in the
     *             CLASSPATH.
     * 
     * @author bluetata / dietime1943@hotmail.com 2017/03/14
     * @author Name Date(YYYY/MM/dd)
     * @since datasnatch(crawler) version(1.0)
     */
    public static Properties getProperties(String propertyFileName) throws java.io.FileNotFoundException {

        InputStream is = null;
        Properties props = null;

        try {
            if (StringUtil.isEmpty(propertyFileName)) {
                throw new IllegalArgumentException(propertyFileName + " not found");
            }
            
            // PROPS_SUFFIX :.properties
            String suffix = UtilsConstants.PROPS_SUFFIX;
            if (propertyFileName.lastIndexOf(suffix) == -1) {
                propertyFileName += suffix;
            }
            
            // String configPath = System.getProperty("configPath");
            // File file = new File(configPath + SystemConstants.FILE_SEPARATOR + propertyFileName);
            // is = new FileInputStream(FileHelpers.getFile(propertyFileName));
            // is = this.getClass().getClassLoader().getResourceAsStream(configPath + File.separator + propertyFileName);
            is = Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream(File.separator + propertyFileName);
            
            // load properties
            if (is != null) {
                props = new Properties();
                props.load(new InputStreamReader(is, UtilsConstants.DEFAULT_CHARSET));
            }
        } catch (Exception ignore) {
            ignore.printStackTrace();
            throw new java.io.FileNotFoundException(propertyFileName + " not found");
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return props;
    }
    
    /**
     * 方法用途和描述: 得到所有的配置信息
     * @param properties
     *          属性文件对象
     * @return map 含有属性文件中的key和value的map
     * @author bluetata / dietime1943@hotmail.com 2017/03/14
     * @author Name Date(YYYY/MM/dd)
     * @since datasnatch(crawler) version(1.0)
     */
    public static Map<?, ?> getAll(Properties properties) {
        Map<String, String> map = new HashMap<String, String>();
        Enumeration<?> enu = properties.propertyNames();
        while (enu.hasMoreElements()) {
            String key = (String) enu.nextElement();
            String value = properties.getProperty(key);
            map.put(key, value);
        }
        return map;
    }

}