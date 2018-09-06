/**
 * ImageUtil.java
 *
 * Function：operation of images utility class.
 *
 *   ver     date           author
 * ──────────────────────────────────
 *   1.0     2018/03/20     bluetata
 *
 * Copyright (c) 2018, [https://github.com/bluetata] All Rights Reserved.
 */
package com.datacrawler.common.util;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class ImageUtil {

    public static void main(String[] args) throws Exception {
        downImages("http://pic1.kaixin001.com.cn/logo/14/97/50_121149705_1.jpg", "D:\\laozizhucom.gif");
    }

    /**
     * 下载文件到本地
     * 
     * @param urlString
     *            被下载的文件地址
     * @param filename
     *            本地文件名
     * @throws Exception
     *             各种异常
     */
    public static void downImages(String urlString, String filename) throws Exception {
        // 构造URL
        URL url = new URL(urlString);
        // 打开连接
        URLConnection con = url.openConnection();
        // 输入流
        InputStream is = con.getInputStream();
        // 1K的数据缓冲
        byte[] bs = new byte[1024];
        // 读取到的数据长度
        int len;
        // 输出的文件流
        OutputStream os = new FileOutputStream(filename);
        // 开始读取
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        // 完毕，关闭所有链接
        os.close();
        is.close();
    }
}
