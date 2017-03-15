/**
 * SystemConstants.java

 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2009-11-30 		Zhuxr
 *
 * Copyright (c) 2009, TNT All Rights Reserved.
 */
package com.datacrawler.consts;

/**
 * 
 * @Type: SystemConstants
 * @Description: 系统配置常量类
 * @author bluetata
 * @date 2011/11/30
 * @version 1.0
 *
 */
public class SystemConstants {

    /**
     * 配置文件路径的 键名，通常为 configPath，整个程序不用更改。 java -DconfigPath
     */
    public static final String CONFIG_PATH_KEY = "configPath";

    /**
     * directory path for language reousrce and freemarker templates java
     * -DresourcePath
     */
    public static final String DIRECTORY_PATH_FOR_RESOURCE = "resourcePath";

    /**
     * file upload root path
     */
    public static final String FILE_UPLOAD_ROOTPATH = "file.upload.rootPath";

    /**
     * file upload tempDir
     */
    public static final String FILE_UPLOAD_TEMPDIR = "file.upload.tempDir";

    /**
     * file upload maxUploadSize
     */
    public static final String FILE_UPLOAD_MAXUPLOADSIZE = "file.upload.maxUploadSize";

    /**
     * system default language
     */
    public static final String SYS_DEFAULT_LANGUAGE = "system.default.language";

    /**
     * 系统支持的语言
     */
    public static final String SYS_SUPPORT_LANGUAGE = "system.support.language";

    /**
     * system default DateService(db/server)
     */
    public static final String SYS_DEFAULT_DATE = "system.default.dateService";

    /**
     * 系统默认的一览显示数量
     */
    public static final String SYS_DEFAULT_LIST_NUM = "system.default.listnumber";
    /**
     * 系统一览最大显示数量
     */
    public static final String SYS_DEFAULT_MAX_LIST_NUM = "system.default.maxlistnumber";
    /**
     * 系统默认的显示风格
     */
    public static final String SYS_DEFAULT_THEME = "system.default.theme";

    /**
     * 系统默认context - 当请求未指明method参数时，默认访问的context对象
     */
    public static final String SYS_DEFAULT_CONTEXT = "system.default.context";

    /**
     * 
     * 系统默认HOME画面 - 登录后，默认显示哪个模块的画面
     */
    public static final String SYS_DEFAULT_HOMEPAGE = "system.default.homepage";

    /**
     * 
     * 系统默认时区
     */
    public static final String SYS_DEFAULT_TIMEZONE = "system.default.timezone";

    /**
     * 系统默认姓名显示顺序
     */
    public static final String SYS_DEFAULT_NAMEDISPLAY = "system.default.nameDisplay";

    /**
     * 
     * web画面默认Title
     */
    public static final String SYS_DEFAULT_WEBTITLE = "system.default.webtitle";

    /**
     * 系统默认日期时间格式：yyyy-MM-dd HH:mm:ss
     */
    public static final String SYS_DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 系统默认日期时间格式：yyyy-MM-dd
     */
    public static final String SYS_DEFAULT_DATE_FORMAT_YMD = "yyyy-MM-dd";

    /**
     * 系统当前的CSS版本号
     */
    public static final String SYS_CSS_VER = "system.css.version";

    /**
     * 系统当前的JS版本号
     */
    public static final String SYS_JS_VER = "system.js.version";
    
    /**
     *  En:the name of common properties. Jp:共同の属性ファイル名。  Zh:共通配置文件
     */
    public static final String COM_CONSTANTS = "com-constants.properties";
    
}
