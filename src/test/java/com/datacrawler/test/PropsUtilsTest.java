package com.datacrawler.test;

import java.io.FileNotFoundException;
import java.util.Properties;

import com.datacrawler.common.util.PropertyReader;

public class PropsUtilsTest {

    public static void main(String[] args) {

        try {
            Properties props = PropertyReader.getProperties("com-constants.properties");
            
            System.out.println(props.toString());
            
            String value1 = props.getProperty("maxRetry");
            String value2 = props.getProperty("sleepTime");
            
            System.out.println(value1 + "/" + value2);
            
            System.out.println(PropertyReader.getAll(props).toString());
            
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
