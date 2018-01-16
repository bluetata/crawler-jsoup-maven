package com.datacrawler.htmlunit;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;

import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class htmlunitTest {

    public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
        
        java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);  
        java.util.logging.Logger.getLogger("org.apache.http.client").setLevel(Level.OFF);
        
        LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log","org.apache.commons.logging.impl.NoOpLog");
        
        String url = "https://www.douyin.com/share/video/6496703951436516621/?mid=6484356820260686606";
        System.out.println("Loading page now-----------------------------------------------: "+url);
        
        /* HtmlUnit 模拟浏览器 */
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setJavaScriptEnabled(true);              // 启用JS解释器，默认为true  
        webClient.getOptions().setCssEnabled(false);                    // 禁用css支持  
        webClient.getOptions().setThrowExceptionOnScriptError(false);   // js运行错误时，是否抛出异常  
        webClient.getOptions().setTimeout(10 * 1000);                   // 设置连接超时时间
        HtmlPage page = webClient.getPage(url);
        webClient.waitForBackgroundJavaScript(30 * 1000);               // 等待js后台执行30秒

        String pageAsXml = page.asXml();
        
        /* Jsoup解析处理 */
        // Document doc = Jsoup.parse(pageAsXml, "https://bluetata.com/");
        Document doc = Jsoup.parse(pageAsXml);  
        Elements pngs = doc.select("img[src$=.png]");                   // 获取所有图片元素集
        // 其他操作
        System.out.println(doc.toString());
    }
    
    
//    public void testCrawler() throws Exception {  
//        /**HtmlUnit请求web页面*/  
//        WebClient wc = new WebClient();  
//        wc.getOptions().setJavaScriptEnabled(true); //启用JS解释器，默认为true  
//        wc.getOptions().setCssEnabled(false); //禁用css支持  
//        wc.getOptions().setThrowExceptionOnScriptError(false); //js运行错误时，是否抛出异常  
//        wc.getOptions().setTimeout(10000); //设置连接超时时间 ，这里是10S。如果为0，则无限期等待  
//        HtmlPage page = wc.getPage("http://cq.qq.com/baoliao/detail.htm?294064");  
//        String pageXml = page.asXml(); //以xml的形式获取响应文本  
//  
//        /**jsoup解析文档*/  
//        Document doc = Jsoup.parse(pageXml, "http://cq.qq.com");   
//        Element pv = doc.select("#feed_content span").get(1);  
//        System.out.println(pv.text());  
//        // Assert.assertTrue(pv.text().contains("浏览"));  
//  
//        System.out.println("Thank God!");  
//    } 
}
