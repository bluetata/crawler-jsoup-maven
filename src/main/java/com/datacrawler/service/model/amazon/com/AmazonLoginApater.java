package com.datacrawler.service.model.amazon.com;

import org.jsoup.nodes.Document;

import com.datacrawler.common.util.JsoupUtil;

/**
 * @since crawler(datasnatch) version(1.0)</br>
 * @author bluetata / dietime1943@gmail.com</br>
 * @version 1.0</br>
 *
 */
public class AmazonLoginApater {
    
    public static void main(String[] args) {
        // grab login form page first
        try {
            
            Document doc = JsoupUtil.getDocument("https://www.amazon.com/s/ref=nb_sb_noss?url=search-alias%3Dvideogames&field-keywords=iphone");
            
            System.out.println(doc.toString());
        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
