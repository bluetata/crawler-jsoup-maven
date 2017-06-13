package com.datacrawler.common.util;

import org.apache.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class GrabUnits {

    private static Logger logger = Logger.getLogger(GrabUnits.class);
    
    /** 最大请求时间 10秒 **/
    private static final int MAX_CONNECT_TIME = 10000;
    
    public static int GRAB_SIZE = 1000;
    public static int GRAB_COUNT = 0;
    
    
    public static void main(String args[]){
        while(true){
            Document doc = GrabUnits.postRemoteURL("http://www.sinbong.com");

            //判断任务结束
            GrabUnits.GRAB_COUNT ++;
            if(GrabUnits.GRAB_SIZE == GrabUnits.GRAB_COUNT){
                logger.info("===================================== 分配任务完成 =====================================");
                System.exit(0);
            }            
            
        }
    }
    
    /***
     * 远程访问URL返回Doc
     * @param remoteUrl
     * @return
     */
    public static Document postRemoteURL(String remoteUrl) {
        Document doc = null;
        int temp = 0;
        try {
            temp = Integer.parseInt(Math.round(Math.random()*(UserAgent.length-1))+"");
            Connection conn = Jsoup.connect(remoteUrl);
            conn.header("User-Agent", UserAgent[temp]);
            conn.cookie("auth", "token");
            doc = conn.timeout(MAX_CONNECT_TIME).post();            
        } catch (Exception ex) {
            logger.error("GrabUnits.class_postRemoteURL 出现异常...");
            logger.error(ex);
        }
        
        try {
            int sleeptime = Integer.parseInt(Math.round(Math.random()*10000)+"");
            logger.info(BrowserType[temp] +" POST请求 " + remoteUrl + " 停留 " + sleeptime + " 毫秒。");
            Thread.sleep(sleeptime);
        } catch (InterruptedException ex) {
            logger.error("GrabUnits.class_postRemoteURL 休眠出现异常...");
            logger.error(ex);
        }
        
        return doc;
    }

    public static Document getRemoteURL(String remoteUrl) {
        Document doc = null;
        int temp = 0;
        try {
            temp = Integer.parseInt(Math.round(Math.random()*(UserAgent.length-1))+"");
            Connection conn = Jsoup.connect(remoteUrl);
            conn.header("User-Agent", UserAgent[temp]);
            conn.cookie("auth", "token");
            doc = conn.timeout(MAX_CONNECT_TIME).get();            
        } catch (Exception ex) {
            logger.error("GrabUnits.class_getRemoteURL 出现异常...");
            logger.error(ex);
        }
        
        try {
            int sleeptime = Integer.parseInt(Math.round(Math.random()*10000)+"");
            logger.info(BrowserType[temp] +" GET请求 " + remoteUrl + " 停留 " + sleeptime + " 毫秒。");
            Thread.sleep(sleeptime);
        } catch (InterruptedException ex) {
            logger.error("GrabUnits.class_getRemoteURL 休眠出现异常...");
            logger.error(ex);
        }        
        
        return doc;
    }    

    
/*    *//***
     * 汉字转换成拼音
     * @param hanzi
     * @param separator
     * @return
     *//*
    @SuppressWarnings("deprecation")
    public static String hanziToPinyin(String hanzi, String separator) {
        // 创建汉语拼音处理类
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        // 输出设置，大小写，音标方式
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

        String pinyingStr = "";
        try {
            pinyingStr = PinyinHelper.toHanyuPinyinString(hanzi, defaultFormat, separator);
        } catch (Exception ex) {
            logger.error("GrabUnits.class_hanziToPinyin 出现异常...");
            logger.error(ex);
        }
        return pinyingStr;
    }*/
    
    
    /**
     * @return 谷歌浏览器
     * @return 火狐浏览器
     * @return 猎豹浏览器
     * @return 360浏览器
     * @return 傲游浏览器
     * @return 腾讯TT浏览器
     * @return Opera浏览器
     * @return IE6、8、9
     * @return 世界之窗浏览器
     * @return 腾讯QQ浏览器
     * @return 苹果浏览器
     */
    public static String[] UserAgent = {
        "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.13 (KHTML, like Gecko) Chrome/24.0.1290.1 Safari/537.13",
        "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:17.0) Gecko/20100101 Firefox/17.0",
        "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0; LBBROWSER)",
        "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)",
        "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.12 (KHTML, like Gecko) Maxthon/3.0 Chrome/18.0.966.0 Safari/535.12",
        "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; WOW64; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E)",
        "Opera/9.80 (Windows NT 6.1; WOW64) Presto/2.12.388 Version/12.12",
        "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 6.1; WOW64; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E)",
        "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; WOW64; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E)",
        "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; WOW64; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E)",
        "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0; qihu theworld)",
        "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0; QQBrowser/7.0.4350.400)",
        "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_8; en-us) AppleWebKit/534.57.2 (KHTML, like Gecko) Version/5.1.6 Safari/534.57.2"
    };
    
    public static String[] BrowserType = {"谷歌浏览器", "火狐浏览器", "猎豹浏览器", "360浏览器", "傲游浏览器", "腾讯TT浏览器", "Opera浏览器", "IE6浏览器", "IE8浏览器", "IE9浏览器", "世界之窗浏览器", "腾讯QQ浏览器", "苹果浏览器"};
    
    
}