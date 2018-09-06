package com.datacrawler.test;

import com.datacrawler.common.util.JsoupUtil;

public class HelloWorld {

	public static void main(String[] args) throws Exception {
	    
	    String returnVal = JsoupUtil.getHtml("https://www.y139.net:31321/Gzinter/orderuser?method=CheckIsOrderJthb&upmsg=PhmzZnCAs%2FmwHgttBRJ2iumYM0%2BOsjGaHvXB%2B1QYJ1bAqdhfocExQALWaLKB13Uu");
	    
	    System.out.println(returnVal);
	}

}
