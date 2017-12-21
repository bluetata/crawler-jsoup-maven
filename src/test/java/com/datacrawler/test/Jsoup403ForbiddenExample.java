package com.datacrawler.test;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
 
public class Jsoup403ForbiddenExample {
 
    public static void main(String[] args) {
        
        try{
            
            // connect to the website         '1
            Connection connection = Jsoup.connect("http://www.bluetata.com");
            
            // get the HTML document          '2
            Document doc = connection.get();
            
            // parse text from HTML           '3
            String strHTML = doc.text();
            
            // out put dom                    '4
            System.out.println(strHTML);
            
        }catch(IOException ioex){
            ioex.printStackTrace();
        }
 
    }
}